import java.util.Random;

public class RandomNumbers {

    public int[] random(int number, int quantity) {
        int[] randomNumber = new int[quantity];

        for (int i = 0; i < quantity; i++) {
            randomNumber[i] = new Random().nextInt(1, number + 1);
        }

        return randomNumber;
    }
}
