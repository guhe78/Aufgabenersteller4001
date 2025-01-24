import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFileChooser;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class CreatePDF {
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
    private final Date currentTime = new Date();
    private final Font mainFont = new Font(Font.FontFamily.COURIER, 16, Font.NORMAL);

    public void create(ArrayList<String> list, String dir) throws DocumentException, IOException {
        for (String string : list) {
            System.out.println(string);
        }

        String fileName = "aufgaben" + sdf.format(currentTime) + ".pdf";

        Path filePath = Paths.get(dir, fileName);
        File folder = new File(dir);

        if (!folder.exists()) {
            try {
                Files.createDirectories(filePath.getParent());
            } catch (IOException e) {
                System.out.println("Fehler " + e);
            }
        }

        String file = String.valueOf(Files.createFile(filePath));
        Document document = new Document(PageSize.A4, 0, 0, 25, 25);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));

        document.open();
        document.add(createTable(list));
        document.close();
    }

    private PdfPTable createTable(ArrayList<String> list) {
        PdfPTable table = new PdfPTable(3);
        PdfPCell pdfCell;

        for (String s : list) {
            pdfCell = new PdfPCell(new Paragraph(s, mainFont));
            pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfCell.setBorder(Rectangle.NO_BORDER);
            pdfCell.setPadding(4);
            table.addCell(pdfCell);
        }

        return table;
    }

    public String setSaveLocation() {
        String dir;
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = jFileChooser.showDialog(null, "Datei speichern...");

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            dir = jFileChooser.getSelectedFile().getAbsolutePath();
        } else {
            dir = "";
        }

        return dir;
    }
}
