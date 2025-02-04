import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.DocumentException;

public class MainFrame extends JFrame {
    private JCheckBox plusCheckBox;
    private JCheckBox minusCheckBox;
    private JCheckBox multiCheckBox;
    private final JSpinner firstNumber = new JSpinner();
    private final JSpinner secondNumber = new JSpinner();
    private CalculateNumbers calculateNumbers;
    private CreatePDF createPDF;
    private String dir;

    public MainFrame() {
        setTitle("Matheaufgabenersteller4001");
        setLocationRelativeTo(null);
        setSize(400, 300);

        add(chooseOperators(), BorderLayout.WEST);
        JPanel numbersPanel = createPanel("Zahlen wählen");
        numbersPanel.add(createNumbersPanel());
        add(numbersPanel, BorderLayout.EAST);
        add(createSaveLocationPanel(), BorderLayout.NORTH);
        add(createButton("Erstellen"), BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        pack();
    }

    private JPanel chooseOperators() {
        JPanel operatorsPanel = createPanel("Rechenart");
        operatorsPanel.setLayout(new GridLayout(3, 1));
        plusCheckBox = createCheckBox("Addition");
        minusCheckBox = createCheckBox("Subtraktion");
        multiCheckBox = createCheckBox("Multiplikation");

        operatorsPanel.add(plusCheckBox);
        operatorsPanel.add(minusCheckBox);
        operatorsPanel.add(multiCheckBox);

        return operatorsPanel;
    }

    private JPanel createNumbersPanel() {
        JPanel numbersPanel = createPanel("");
        JPanel firstNumberPanel = new JPanel();
        JPanel secondNumberPanel = new JPanel();

        numbersPanel.setLayout(new GridLayout(2, 1));
        numbersPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        firstNumberPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        secondNumberPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        final int num_MIN = 10;
        final int num_MAX = 100;
        final int num_INIT = 10;

        firstNumberPanel.add(new JLabel("Erste Zahl:"));
        firstNumber.setModel(new SpinnerNumberModel(num_INIT, num_MIN, num_MAX, 10));
        firstNumberPanel.add(firstNumber);

        secondNumberPanel.add(new JLabel("Zweite Zahl:"));
        secondNumber.setModel(new SpinnerNumberModel(num_INIT, num_MIN, num_MAX, 10));
        secondNumberPanel.add(secondNumber);

        numbersPanel.add(firstNumberPanel, BorderLayout.WEST);
        numbersPanel.add(secondNumberPanel, BorderLayout.EAST);

        return numbersPanel;
    }

    private JPanel createSaveLocationPanel() {
        createPDF = new CreatePDF();
        JPanel jPanel = createPanel("Speicherort wählen");
        JButton jButton = new JButton("Speichern unter...");

        jButton.addActionListener((ActionEvent e) -> {
            dir = createPDF.setSaveLocation();
        });

        jPanel.add(jButton);
        return jPanel;
    }

    private JPanel createButton(String title) {
        JPanel buttonPanel = createPanel("Button");
        JButton button = new JButton(title);
        calculateNumbers = new CalculateNumbers();

        button.addActionListener((ActionEvent e) -> {
            ArrayList<String> checkBoxs = new ArrayList<>();
            createPDF = new CreatePDF();
            int n1 = (Integer) firstNumber.getValue();
            int n2 = (Integer) secondNumber.getValue();

            if (plusCheckBox.isSelected()) {
                checkBoxs.add("+");
            }
            if (minusCheckBox.isSelected()) {
                checkBoxs.add("-");
            }
            if (multiCheckBox.isSelected()) {
                checkBoxs.add("*");
            }

            if (checkBoxs.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Rechenart auswählen");
            }

            String[] symbols = new String[checkBoxs.size()];

            for (int i = 0; i < symbols.length; i++) {
                symbols[i] = checkBoxs.get(i);
            }

            ArrayList<String> list = calculateNumbers.printTasks(n1, n2, symbols);
            ArrayList<String> hiddenList = calculateNumbers.printHiddenTasks(list, 2);

            try {
                try {
                    createPDF.create(hiddenList, dir);
                } catch (IOException e1) {
                    System.out.println("Error: " + e1);
                }
            } catch (DocumentException de) {
                System.out.println("Error: " + de);
            }

        });
        buttonPanel.add(button);

        return buttonPanel;
    }

    private JPanel createPanel(String title) {
        final JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());
        jPanel.setBorder(new CompoundBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5),
                new CompoundBorder(
                        BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), title),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10))));

        return jPanel;
    }

    private JCheckBox createCheckBox(String title) {
        JCheckBox jCheckBox = new JCheckBox(title);
        jCheckBox.setMnemonic(KeyEvent.VK_C);

        return jCheckBox;
    }
}