import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xslf.usermodel.*;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FreieFolie {
    public static void main(String[] args) {

        //CREATE
        XMLSlideShow ppt = new XMLSlideShow();

        //OUTPUT-DEFINITION
        try (FileOutputStream output = new FileOutputStream("/Users/anabikic/Documents/SCHULE/5BHITM/Diplomarbeit/apachePOI_try/src/main/output/Freiefolie.pptx")) {

            //LAYOUT
            XSLFSlideMaster defaultMaster = ppt.getSlideMasters().get(0);
            XSLFSlideLayout tc = defaultMaster.getLayout(SlideLayout.TITLE);
            XSLFSlide freieFolie = ppt.createSlide(tc);

            //TITLE
            XSLFTextShape title = freieFolie.getPlaceholder(0);
            title.clearText();
            XSLFTextParagraph title_p = title.addNewTextParagraph();
            XSLFTextRun title_r = title_p.addNewTextRun();
            //-------------------------------------------------------------TITEL AUS DER DATENBAK
            title_r.setText("Freie Folie");
            title_r.setFontColor(new Color(0, 82, 129));
            title_r.setFontSize(24.0);
            title_r.setBold(true);
            title.setAnchor(new Rectangle(20, 20, 500, 50));


            //SUBTITLE
            XSLFTextShape subtitle = freieFolie.getPlaceholder(1);
            XSLFTextParagraph subtitleParagraph = subtitle.addNewTextParagraph();
            XSLFTextRun subtitleRun = subtitleParagraph.addNewTextRun();
            //-------------------------------------------------------------UNTERTITEL AUS DER DATENBAK
            subtitleRun.setText("optionaler Freitext");
            subtitleRun.setFontColor(new Color(0, 82, 129));
            subtitleRun.setFontSize(18.0);
            subtitle.setAnchor(new Rectangle(20, 55, 500, 50));
            subtitle.clearText();


            //LOGO
            byte[] logo = IOUtils.toByteArray(new FileInputStream("/Users/anabikic/Documents/SCHULE/5BHITM/Diplomarbeit/apachePOI_try/src/main/img/logo_sparkasse.png"));
            XSLFPictureData pd = ppt.addPicture(logo, PictureData.PictureType.PNG);
            XSLFPictureShape pic = freieFolie.createPicture(pd);
            pic.setAnchor(new Rectangle(540, 20, 150, 50));

            //BODY
            XSLFTextShape body = freieFolie.getPlaceholder(1);
            body.clearText();

            //BESCHREIBUNGSTEXT
            XSLFAutoShape freierText = body.getSheet().createTextBox();
            freierText.setAnchor(new Rectangle(40, 100,640, 140 ));
            XSLFTextParagraph freierTextParagraph = freierText.addNewTextParagraph();
            XSLFTextRun freierTextRun = freierTextParagraph.addNewTextRun();
            //-------------------------------------------------------------BESCHREIBUNGSTEXT AUS DER DATENBAK
            freierTextRun.setText("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.");
            freierTextRun.setFontColor(new Color(0, 82, 129));
            freierTextRun.setFontSize(14.0);

            //UPLOAD
            /*
            byte[] uploadedPicture = IOUtils.toByteArray(new FileInputStream("/Users/anabikic/Documents/SCHULE/5BHITM/Diplomarbeit/apachePOI_try/src/main/img/upload.png"));
            XSLFPictureData uploadedPicture_pd = ppt.addPicture(uploadedPicture, PictureData.PictureType.PNG);
            XSLFPictureShape uploadedPicture_pic = freieFolie.createPicture(uploadedPicture_pd);
            uploadedPicture_pic.setAnchor(new Rectangle(70, 120, 500, 100));

             */

            //----------------------------------------------------------------------------------------------------------FOOTER
            XSLFTextBox freiefolie_footer = freieFolie.createTextBox();
            XSLFTextParagraph freiefolie_footer_p = freiefolie_footer.addNewTextParagraph();
            XSLFTextRun freiefolie_footer_r = freiefolie_footer_p.addNewTextRun();
            freiefolie_footer_r.setText("Projekt Portfolio Komitee (PPK) – 19.07.2022");
            freiefolie_footer_r.setFontColor(new Color(0, 82, 129));
            freiefolie_footer_r.setFontSize(12.);
            freiefolie_footer.setAnchor(new Rectangle(440, 500, 270, 50));

            //GRÖßENANGABE
            java.awt.Dimension pgsize = ppt.getPageSize();
            int width = pgsize.width;
            int height = pgsize.height;
            System.out.println("width" + width);
            System.out.println("height" + height);

            //OUTPUT
            ppt.write(output);


        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
