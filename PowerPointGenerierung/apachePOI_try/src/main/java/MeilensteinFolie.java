
import org.apache.poi.sl.usermodel.*;
import org.apache.poi.ss.usermodel.ShapeTypes;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xslf.usermodel.*;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.apache.poi.ss.usermodel.ShapeTypes.*;

public class MeilensteinFolie {

    public static void main(String[] args) {

        XMLSlideShow ppt = new XMLSlideShow();

        try(FileOutputStream output = new FileOutputStream("/Users/anabikic/Documents/SCHULE/5BHITM/Diplomarbeit/apachePOI_try/src/main/output/Meilenstein.pptx")){

            XSLFSlideMaster defaultMaster = ppt.getSlideMasters().get(0);
            XSLFSlideLayout t = defaultMaster.getLayout(SlideLayout.TITLE);
            XSLFSlide meilensteinFolie = ppt.createSlide(t);

            //Titel erstellen
            XSLFTextShape meilensteinFolie_title = meilensteinFolie.getPlaceholder(0);
            meilensteinFolie_title.clearText();
            XSLFTextParagraph meilensteinFolie_title_p = meilensteinFolie_title.addNewTextParagraph();
            XSLFTextRun meilensteinFolie_title_r = meilensteinFolie_title_p.addNewTextRun();
            meilensteinFolie_title_r.setText("The Projector");
            meilensteinFolie_title_r.setFontColor(new Color(0, 82, 129));
            meilensteinFolie_title_r.setFontSize(24.0);
            meilensteinFolie_title_r.setBold(true);
            meilensteinFolie_title.setAnchor(new Rectangle(5, 20, 380, 50));

            //Untertitel erstellen
            XSLFTextShape meilensteinFolie_subtitle = meilensteinFolie.getPlaceholder(1);
            meilensteinFolie_subtitle.clearText();
            XSLFTextParagraph meilensteinFolie_subtitle_p = meilensteinFolie_subtitle.addNewTextParagraph();
            XSLFTextRun meilensteinFolie_subtitle_r = meilensteinFolie_subtitle_p.addNewTextRun();
            meilensteinFolie_subtitle_r.setText("Meilensteinplan");
            meilensteinFolie_subtitle_r.setFontColor(new Color(0, 82, 129));
            meilensteinFolie_subtitle_r.setFontSize(18.0);
            meilensteinFolie_subtitle.setAnchor(new Rectangle(5, 60, 380, 50));

            //Logo einfügen
            byte[] pictureData = IOUtils.toByteArray(new FileInputStream("/Users/anabikic/Documents/SCHULE/5BHITM/Diplomarbeit/apachePOI_try/src/main/img/logo_sparkasse.png"));
            XSLFPictureData pd = ppt.addPicture(pictureData, PictureData.PictureType.PNG);
            XSLFPictureShape pic = meilensteinFolie.createPicture(pd);
            pic.setAnchor(new Rectangle(540, 20, 150, 50));


            //----------------------------------------------------------------------------------------------------------MEILENSTEINPFEIL
            XSLFAutoShape meilensteinPfeil = meilensteinFolie.createAutoShape();
            meilensteinPfeil.setShapeType(ShapeType.RIGHT_ARROW);
            meilensteinPfeil.setAnchor(new java.awt.Rectangle(0, 205, 720, 100));
            meilensteinPfeil.setFillColor(new Color(195,227,248));

            //----------------------------------------------------------------------------------------------------------ERSTER MEILENSTEIN
            byte[] abgenommen_pic = IOUtils.toByteArray(new FileInputStream("/Users/anabikic/Documents/SCHULE/5BHITM/Diplomarbeit/apachePOI_try/src/main/img/abgenommen.png"));
            XSLFPictureData abgenommen_pic_pd = ppt.addPicture(abgenommen_pic, PictureData.PictureType.PNG);
            XSLFPictureShape abgenommen_pic_shape = meilensteinFolie.createPicture(abgenommen_pic_pd);
            abgenommen_pic_shape.setAnchor(new Rectangle(38, 180, 40, 40));

            XSLFTextShape ersterMeilenstein = meilensteinFolie.createTextBox();
            ersterMeilenstein.clearText();
            XSLFTextParagraph ersterMeilenstein_p = ersterMeilenstein.addNewTextParagraph();
            XSLFTextRun ersterMeilenstein_r = ersterMeilenstein_p.addNewTextRun();
            ersterMeilenstein_p.setTextAlign(TextParagraph.TextAlign.CENTER);
            ersterMeilenstein_r.setText("Projektstart erfolgt");
            ersterMeilenstein_r.setFontColor(Color.BLACK);
            ersterMeilenstein_r.setFontFamily("Cavolini");
            ersterMeilenstein_r.setFontSize(10.);
            ersterMeilenstein.setAnchor(new Rectangle(15, 350, 90, 40));

            //----------------------------------------------------------------------------------------------------------ZWEITER MEILENSTEIN
            byte[] abgenommen_pic02 = IOUtils.toByteArray(new FileInputStream("/Users/anabikic/Documents/SCHULE/5BHITM/Diplomarbeit/apachePOI_try/src/main/img/abgenommen.png"));
            XSLFPictureData abgenommen_pic_pd02 = ppt.addPicture(abgenommen_pic02, PictureData.PictureType.PNG);
            XSLFPictureShape abgenommen_pic_shape02 = meilensteinFolie.createPicture(abgenommen_pic_pd02);
            abgenommen_pic_shape02.setAnchor(new Rectangle(250, 180, 40, 40));

            XSLFTextShape zweiterMeilenstein = meilensteinFolie.createTextBox();
            zweiterMeilenstein.clearText();
            XSLFTextParagraph zweiterMeilenstein_p = zweiterMeilenstein.addNewTextParagraph();
            XSLFTextRun zweiterMeilenstein_r = zweiterMeilenstein_p.addNewTextRun();
            zweiterMeilenstein_p.setTextAlign(TextParagraph.TextAlign.CENTER);
            zweiterMeilenstein_r.setText("Backend mit DB erledigt");
            zweiterMeilenstein_r.setFontColor(Color.BLACK);
            zweiterMeilenstein_r.setFontFamily("Cavolini");
            zweiterMeilenstein_r.setFontSize(10.);
            zweiterMeilenstein.setAnchor(new Rectangle(220, 400, 90, 40));

            //----------------------------------------------------------------------------------------------------------DRITTER MEILENSTEIN
            byte[] nichtAbgenommen_pic01 = IOUtils.toByteArray(new FileInputStream("/Users/anabikic/Documents/SCHULE/5BHITM/Diplomarbeit/apachePOI_try/src/main/img/nicht_abgenommen.png"));
            XSLFPictureData nichtAbgenommen_pic_pd01 = ppt.addPicture(nichtAbgenommen_pic01, PictureData.PictureType.PNG);
            XSLFPictureShape nichtAbgenommen_pic_shape01 = meilensteinFolie.createPicture(nichtAbgenommen_pic_pd01);
            nichtAbgenommen_pic_shape01.setAnchor(new Rectangle(340, 190, 30, 29));

            XSLFTextShape dritterMeilenstein = meilensteinFolie.createTextBox();
            dritterMeilenstein.clearText();
            XSLFTextParagraph dritterMeilenstein_p = dritterMeilenstein.addNewTextParagraph();
            XSLFTextRun dritterMeilenstein_r = dritterMeilenstein_p.addNewTextRun();
            dritterMeilenstein_p.setTextAlign(TextParagraph.TextAlign.CENTER);
            dritterMeilenstein_r.setText("Frontend und Generierung der PPT");
            dritterMeilenstein_r.setFontColor(Color.BLACK);
            dritterMeilenstein_r.setFontFamily("Cavolini");
            dritterMeilenstein_r.setFontSize(10.);
            dritterMeilenstein.setAnchor(new Rectangle(310, 320, 90, 40));

            //----------------------------------------------------------------------------------------------------------VIERTER MEILENSTEIN
            byte[] nichtAbgenommen_pic02 = IOUtils.toByteArray(new FileInputStream("/Users/anabikic/Documents/SCHULE/5BHITM/Diplomarbeit/apachePOI_try/src/main/img/nicht_abgenommen.png"));
            XSLFPictureData nichtAbgenommen_pic_pd02 = ppt.addPicture(nichtAbgenommen_pic02, PictureData.PictureType.PNG);
            XSLFPictureShape nichtAbgenommen_pic_shape02 = meilensteinFolie.createPicture(nichtAbgenommen_pic_pd02);
            nichtAbgenommen_pic_shape02.setAnchor(new Rectangle(440, 190, 30, 29));

            XSLFTextShape vierterMeilenstein = meilensteinFolie.createTextBox();
            XSLFTextParagraph vierterMeilenstein_p = vierterMeilenstein.addNewTextParagraph();
            XSLFTextRun vierterMeilenstein_r = vierterMeilenstein_p.addNewTextRun();
            vierterMeilenstein_p.setTextAlign(TextParagraph.TextAlign.CENTER);
            vierterMeilenstein_r.setText("Testen und Qualitäts\nsicherung");
            vierterMeilenstein_r.setFontColor(Color.BLACK);
            vierterMeilenstein_r.setFontFamily("Cavolini");
            vierterMeilenstein_r.setFontSize(10.);
            vierterMeilenstein.setAnchor(new Rectangle(405, 400, 90, 40));

            //----------------------------------------------------------------------------------------------------------FÜNFTER MEILENSTEIN
            byte[] nichtAbgenommen_pic03 = IOUtils.toByteArray(new FileInputStream("/Users/anabikic/Documents/SCHULE/5BHITM/Diplomarbeit/apachePOI_try/src/main/img/nicht_abgenommen.png"));
            XSLFPictureData nichtAbgenommen_pic_pd03 = ppt.addPicture(nichtAbgenommen_pic03, PictureData.PictureType.PNG);
            XSLFPictureShape nichtAbgenommen_pic_shape03 = meilensteinFolie.createPicture(nichtAbgenommen_pic_pd03);
            nichtAbgenommen_pic_shape03.setAnchor(new Rectangle(600, 190, 30, 29));

            XSLFTextShape fuenfterMeilenstein = meilensteinFolie.createTextBox();
            fuenfterMeilenstein.clearText();
            XSLFTextParagraph fuenfterMeilenstein_p = fuenfterMeilenstein.addNewTextParagraph();
            XSLFTextRun fuenfterMeilenstein_r = fuenfterMeilenstein_p.addNewTextRun();
            fuenfterMeilenstein_p.setTextAlign(TextParagraph.TextAlign.CENTER);
            fuenfterMeilenstein_r.setText("Projekt\nabnahme");
            fuenfterMeilenstein_r.setFontColor(Color.BLACK);
            fuenfterMeilenstein_r.setFontFamily("Cavolini");
            fuenfterMeilenstein_r.setFontSize(10.);
            fuenfterMeilenstein.setAnchor(new Rectangle(570, 350, 90, 40));


            //----------------------------------------------------------------------------------------------------------Abgenomme-Pic einfügen
            byte[] abgenommen_pic01 = IOUtils.toByteArray(new FileInputStream("/Users/anabikic/Documents/SCHULE/5BHITM/Diplomarbeit/apachePOI_try/src/main/img/abgenommen.png"));
            XSLFPictureData abgenommen_pic_pd01 = ppt.addPicture(abgenommen_pic01, PictureData.PictureType.PNG);
            XSLFPictureShape abgenommen_pic_shape01 = meilensteinFolie.createPicture(abgenommen_pic_pd01);
            abgenommen_pic_shape01.setAnchor(new Rectangle(20, 440, 40, 40));

            XSLFTextBox abgenommen_meilensteinfole = meilensteinFolie.createTextBox();
            XSLFTextParagraph abgenommen_meilensteinfole_p = abgenommen_meilensteinfole.addNewTextParagraph();
            XSLFTextRun abgenommen_meilensteinfole_r = abgenommen_meilensteinfole_p.addNewTextRun();
            abgenommen_meilensteinfole_r.setText("abgenommen");
            abgenommen_meilensteinfole_r.setFontColor(Color.BLACK);
            abgenommen_meilensteinfole_r.setFontFamily("Cavolini");
            abgenommen_meilensteinfole_r.setFontSize(10.);
            abgenommen_meilensteinfole.setAnchor(new Rectangle(50, 440, 95, 30));

            //----------------------------------------------------------------------------------------------------------Nicht-Abgenomme-Pic einfügen
            byte[] nichtAbgenommen_pic = IOUtils.toByteArray(new FileInputStream("/Users/anabikic/Documents/SCHULE/5BHITM/Diplomarbeit/apachePOI_try/src/main/img/nicht_abgenommen.png"));
            XSLFPictureData nichtAbgenommen_pic_pd = ppt.addPicture(nichtAbgenommen_pic, PictureData.PictureType.PNG);
            XSLFPictureShape nichtAbgenommen_pic_shape = meilensteinFolie.createPicture(nichtAbgenommen_pic_pd);
            nichtAbgenommen_pic_shape.setAnchor(new Rectangle(23, 480, 30, 30));

            XSLFTextBox nichtAbgenommen_meilensteinfole = meilensteinFolie.createTextBox();
            XSLFTextParagraph nichtAbgenommen_meilensteinfole_p = nichtAbgenommen_meilensteinfole.addNewTextParagraph();
            XSLFTextRun nichtAbgenommen_meilensteinfole_r = nichtAbgenommen_meilensteinfole_p.addNewTextRun();
            nichtAbgenommen_meilensteinfole_r.setText("noch nicht abgenommen");
            nichtAbgenommen_meilensteinfole_r.setFontColor(Color.BLACK);
            nichtAbgenommen_meilensteinfole_r.setFontFamily("Cavolini");
            nichtAbgenommen_meilensteinfole_r.setFontSize(10.);
            nichtAbgenommen_meilensteinfole.setAnchor(new Rectangle(50, 470, 95, 30));

            //----------------------------------------------------------------------------------------------------------FOOTER
            XSLFTextBox meilensteinfolie_footer = meilensteinFolie.createTextBox();
            XSLFTextParagraph meilensteinfolie_footer_p = meilensteinfolie_footer.addNewTextParagraph();
            XSLFTextRun meilensteinfolie_footer_r = meilensteinfolie_footer_p.addNewTextRun();
            meilensteinfolie_footer_r.setText("Projekt Portfolio Komitee (PPK) – 19.07.2022");
            meilensteinfolie_footer_r.setFontColor(new Color(0, 82, 129));
            meilensteinfolie_footer_r.setFontSize(12.);
            meilensteinfolie_footer.setAnchor(new Rectangle(440, 500, 270, 50));


            //----------------------------------------------------------------------------------------------------------Größenangaben der Folie
            java.awt.Dimension pgsize = ppt.getPageSize();
            int width = pgsize.width;
            int height = pgsize.height;
            System.out.println("width" + width);
            System.out.println("height" + height);


            ppt.write(output);



        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
