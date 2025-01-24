import java.util.ArrayList;
import java.util.Random;

public class CalculateNumbers implements ICalculateNumbers {
    private final RandomNumbers number = new RandomNumbers();

    @Override
    public int addition(int a, int b) {
        return a + b;
    }

    @Override
    public int subtraction(int a, int b) {
        return a - b;
    }

    @Override
    public int multiplication(int a, int b) {
        return a * b;
    }

    @Override
    public ArrayList<String> printTasks(int highestNum1, int highestNum2, String[] symbols) {
        int quantity = 100;
        ArrayList<String> list = new ArrayList<>();
        int[] numbers01 = number.random(highestNum1, quantity);
        int[] numbers02 = number.random(highestNum2, quantity);

        for (int i = 0; i < quantity; i++) {
            String symbol = symbols[new Random().nextInt(0, symbols.length)];
            switch (symbol) {
                case "+" -> {
                    list.add(String.format("%2d + %2d = %2d", numbers01[i], numbers02[i],
                            addition(numbers01[i], numbers02[i])));
                }
                case "-" -> {
                    if (subtraction(numbers01[i], numbers02[i]) < 0) {
                        list.add(String.format("%2d - %2d = %2d", numbers02[i], numbers01[i],
                                subtraction(numbers02[i], numbers01[i])));
                    } else {
                        list.add(String.format("%2d - %2d = %2d", numbers01[i], numbers02[i],
                                subtraction(numbers01[i], numbers02[i])));
                    }
                }
                case "*" ->
                    list.add(String.format("%2d • %2d = %2d", numbers01[i], numbers02[i],
                            multiplication(numbers01[i], numbers02[i])));
            }
        }
        return list;
    }

    @Override
    public ArrayList<String> printHiddenTasks(ArrayList<String> list, int pos) {
        ArrayList<String> hiddenNumbers = new ArrayList<>();

        for (String s : list) {
            // int rdmPos = new Random().nextInt(0, 3);
            char sign = '_';
            StringBuilder hiddenTask = new StringBuilder(s);
            switch (pos) {
                case 0 -> {
                    hiddenTask.setCharAt(0, sign);
                    hiddenTask.setCharAt(1, sign);
                    hiddenNumbers.add(String.valueOf(hiddenTask));
                }
                case 1 -> {
                    hiddenTask.setCharAt(5, sign);
                    hiddenTask.setCharAt(6, sign);
                    hiddenNumbers.add(String.valueOf(hiddenTask));
                }
                case 2 -> {
                    hiddenTask.setCharAt(10, sign);
                    hiddenTask.setCharAt(11, sign);
                    hiddenNumbers.add(String.valueOf(hiddenTask));
                }
            }
        }
        return hiddenNumbers;
    }
}
