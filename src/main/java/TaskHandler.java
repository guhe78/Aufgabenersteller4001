import java.io.IOException;
import java.util.ArrayList;

public class TaskHandler {
    CreatePDF pdf = new CreatePDF();
    CalculateNumbers calculateNumbers = new CalculateNumbers();
    String[] strings = new String[] { "+", "-" };
    ArrayList<String> numberList = calculateNumbers.printTasks(50, 10, strings);
    ArrayList<String> list = calculateNumbers.printHiddenTasks(numberList, 2);

    public void createPdfTask() throws IOException {
        /*
         * try {
         * pdf.create(list);
         * } catch (DocumentException e) {
         * // TODO Auto-generated catch block
         * e.printStackTrace();
         * } catch (IOException e) {
         * // TODO Auto-generated catch block
         * e.printStackTrace();
         * }
         */
    }
}
