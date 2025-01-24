import java.util.ArrayList;

public interface ICalculateNumbers {
    int addition(int a, int b);

    int subtraction(int a, int b);

    int multiplication(int a, int b);

    ArrayList<String> printTasks(int a, int b, String[] symbols);

    ArrayList<String> printHiddenTasks(ArrayList<String> list, int pos);
}
