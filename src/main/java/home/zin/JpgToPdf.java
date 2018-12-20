package home.zin;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

/**
 * Created by zinlim on 1/23/17.
 */
public class JpgToPdf {
    public static void main (String[] args){
        Document document = new Document();
        String output = "/Users/zinlim/ebook/PawThut-YauPyar";

        PdfWriter writer = null;
        try {

            String coverUrl = "http://shwevideos.net/images/e_center/e_cartoons/PawThut_YaukPyar/cover.jpg";
            String baseUrl = "http://shwevideos.net/images/e_center/e_cartoons/PawThut_YaukPyar/";
            int startPage = 1;
            int endPage = 112;

            FileOutputStream fos = new FileOutputStream(output);
            writer = PdfWriter.getInstance(document, fos);
            writer.open();

            document.open();
            Image imgc = Image.getInstance
                    (new java.net.URL(coverUrl));
            imgc.scalePercent(71);
            document.add(imgc);
            for (int i = startPage; i <= endPage ; i++) {
                try {
                    System.out.println("Processing page: " + i);
                    Image img = Image.getInstance
                            (new java.net.URL(baseUrl + i + ".jpg"));
                    img.scalePercent(71);
                    document.add(img);
                }catch (Exception e){
                    e.printStackTrace(System.out);
                }
            }

            document.close();
            writer.flush();
            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            writer.close();
        }
    }
}
