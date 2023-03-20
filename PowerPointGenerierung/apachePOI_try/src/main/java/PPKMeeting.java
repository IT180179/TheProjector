import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.ShapeType;
import org.apache.poi.sl.usermodel.TableCell;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xslf.usermodel.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.*;

public class PPKMeeting {

    public static void main(String[] args) {

        //PowerPoint-Präsentation
        XMLSlideShow generiertePowerPointPraesentation = new XMLSlideShow();
        File output = new File("/Users/anabikic/Documents/SCHULE/5BHITM/Diplomarbeit/apachePOI_try/src/main/output/PPKMeeting.pptx");

        try(FileOutputStream os = new FileOutputStream(output)){

            //<editor-fold desc="Folien mit einzelnen Layout">
            //Layout der Folien
            XSLFSlideMaster defaultMaster = generiertePowerPointPraesentation.getSlideMasters().get(0);
            XSLFSlideLayout title_content = defaultMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);
            XSLFSlideLayout title = defaultMaster.getLayout(SlideLayout.TITLE);
            XSLFSlideLayout blank = defaultMaster.getLayout(SlideLayout.BLANK);

            //Einzelnen Folien
            XSLFSlide personalaufwandsFolie = generiertePowerPointPraesentation.createSlide(title_content);
            XSLFSlide projektuebersichtsFolie = generiertePowerPointPraesentation.createSlide(title_content);
            XSLFSlide entscheidungsFolie = generiertePowerPointPraesentation.createSlide(title);
            XSLFSlide softwareanforderungsFolie = generiertePowerPointPraesentation.createSlide();
            XSLFSlide freieFolie = generiertePowerPointPraesentation.createSlide();
            XSLFSlide meilensteinFolie = generiertePowerPointPraesentation.createSlide();
            //</editor-fold>

            //<editor-fold desc="Logo">
            //Logo erstellen
            byte[] pictureData = IOUtils.toByteArray(new FileInputStream("/Users/anabikic/Documents/SCHULE/5BHITM/Diplomarbeit/apachePOI_try/src/main/img/logo_sparkasse.png"));
            XSLFPictureData pd = generiertePowerPointPraesentation.addPicture(pictureData, PictureData.PictureType.PNG);
            //</editor-fold>

            //Titel erstellen
            XSLFTextShape entscheidungsFolie_title = entscheidungsFolie.getPlaceholder(0);
            entscheidungsFolie_title.clearText();
            XSLFTextParagraph entscheidungsFolie_title_p = entscheidungsFolie_title.addNewTextParagraph();
            XSLFTextRun entscheidungsFolie_title_r = entscheidungsFolie_title_p.addNewTextRun();
            entscheidungsFolie_title_r.setText("EWS-Early Warning System");
            entscheidungsFolie_title_r.setFontColor(Color.red);
            entscheidungsFolie_title_r.setFontSize(24.0);
            entscheidungsFolie_title_r.setBold(true);
            entscheidungsFolie_title.setAnchor(new Rectangle(20, 20, 380, 50));

            //Untertitel
            XSLFTextShape entscheidungsFolie_subtitle = entscheidungsFolie.getPlaceholder(1);
            entscheidungsFolie_subtitle.clearText();
            XSLFTextParagraph entscheidungsFolie_subtitle_p = entscheidungsFolie_subtitle.addNewTextParagraph();
            XSLFTextRun entscheidungsFolie_subtitle_r = entscheidungsFolie_subtitle_p.addNewTextRun();
            entscheidungsFolie_subtitle_r.setText("ENTSCHEIDUNG");
            entscheidungsFolie_subtitle_r.setFontColor(Color.red);
            entscheidungsFolie_subtitle_r.setFontSize(20.0);
            entscheidungsFolie_subtitle.setAnchor(new Rectangle(20, 60, 245, 50));

            //LOGO
            XSLFPictureShape pic_entscheidungsFolie = entscheidungsFolie.createPicture(pd);
            pic_entscheidungsFolie.setAnchor(new Rectangle(540, 20, 150, 50));

            //pic1
            byte[] aenderungsVorschlag = IOUtils.toByteArray(new FileInputStream("/Users/anabikic/Documents/Diplomarbeit/apachePOI_try/src/main/img/aenderung.png"));
            XSLFPictureData aenderungsVorschlag_pd = generiertePowerPointPraesentation.addPicture(aenderungsVorschlag, PictureData.PictureType.PNG);
            XSLFPictureShape aenderungsVorschlag_pic = entscheidungsFolie.createPicture(aenderungsVorschlag_pd);
            aenderungsVorschlag_pic.setAnchor(new Rectangle(70, 120, 50, 65));

            //pic2
            byte[] empfehlung = IOUtils.toByteArray(new FileInputStream("/Users/anabikic/Documents/Diplomarbeit/apachePOI_try/src/main/img/play.png"));
            XSLFPictureData empfehlung_pd = generiertePowerPointPraesentation.addPicture(empfehlung, PictureData.PictureType.PNG);
            XSLFPictureShape empfehlung_pic = entscheidungsFolie.createPicture(empfehlung_pd);
            empfehlung_pic.setAnchor(new Rectangle(65, 370, 60, 50));

            //text for aenderungdVorschlag
            XSLFTextBox aenderungsText = entscheidungsFolie.createTextBox();
            XSLFTextParagraph aenderungs_p = aenderungsText.addNewTextParagraph();
            XSLFTextRun aenderungs_r = aenderungs_p.addNewTextRun();
            aenderungs_r.setText("Um die Inhalte aus dem sGruppen-Projekt Digitized Lending in der Spk OÖ bestmöglich verankern und umsetzen zu können soll es auch in der Sparkasse OÖ dazu ein inhouse-Projekt geben (Projektmanager Brandstetter/Fachkoordinatorin Sandra Wallner).\n" +
                    "\nAktuell befindet sich das Projekt in der „Pipeline“ und damit die Arbeit beginnen kann sollte das „Go“ für das Projekt erfolgen.\n" +
                    "\nAufgrund einer Erkrankung könnten noch nicht alle Details/Inhalte fixiert werden.\n");
            aenderungs_r.setFontColor(new Color(0, 82, 129));
            aenderungs_r.setFontSize(16.);
            aenderungsText.setAnchor(new Rectangle(150, 100,550, 260 ));

            //text for empfehlung des PPK
            XSLFTextBox empfehlungsText = entscheidungsFolie.createTextBox();
            XSLFTextParagraph empfehlung_p = empfehlungsText.addNewTextParagraph();
            XSLFTextRun empfehlung_r = empfehlung_p.addNewTextRun();
            empfehlung_r.setText("Das PPK empfiehlt die oben beschriebene Vorgehensweise.");
            empfehlung_r.setFontColor(new Color(0, 82, 129));
            empfehlung_r.setFontSize(16.);
            empfehlungsText.setAnchor(new Rectangle(150, 370,550, 50 ));

            //Rectangle for decision --> yes
            XSLFAutoShape rectangle_yes = entscheidungsFolie.createAutoShape();
            rectangle_yes.setShapeType(ShapeType.RECT);
            rectangle_yes.setAnchor(new Rectangle2D.Double(100,460,25,25));
            rectangle_yes.setLineColor(new Color(0, 82, 129));
            rectangle_yes.setLineWidth(2.5);
            rectangle_yes.setFillColor(Color.white);

            //description for decisionbox --> yes
            XSLFTextBox yes = entscheidungsFolie.createTextBox();
            XSLFTextParagraph yes_p = yes.addNewTextParagraph();
            XSLFTextRun yes_r = yes_p.addNewTextRun();
            yes.setFillColor(new Color(0, 82, 129));
            yes_r.setText("JA");
            yes_p.setLineSpacing(0.5);
            yes_p.setTextAlign(TextParagraph.TextAlign.CENTER);
            yes_r.setFontColor(Color.white);
            yes_r.setFontSize(14.);
            yes.setAnchor(new Rectangle(130, 460,70, 25 ));

            //Rectangle for decision --> yes, with notes
            XSLFAutoShape rectangle_no = entscheidungsFolie.createAutoShape();
            rectangle_no.setShapeType(ShapeType.RECT);
            rectangle_no.setAnchor(new Rectangle2D.Double(250,460,25,25));
            rectangle_no.setLineColor(new Color(0, 82, 129));
            rectangle_no.setLineWidth(2.5);
            rectangle_no.setFillColor(Color.white);

            //description for decisionbox --> yes, with notes
            XSLFTextBox no_note = entscheidungsFolie.createTextBox();
            XSLFTextParagraph no_note_p = no_note.addNewTextParagraph();
            XSLFTextRun no_note_r = no_note_p.addNewTextRun();
            no_note.setFillColor(new Color(0, 82, 129));
            no_note_r.setText("JA, MIT ANMERKUNGEN");
            no_note_p.setLineSpacing(0.5);
            no_note_p.setTextAlign(TextParagraph.TextAlign.CENTER);
            no_note_r.setFontColor(Color.white);
            no_note_r.setFontSize(14.);
            no_note.setAnchor(new Rectangle(280, 460,200, 25 ));

            //Rectangle for decision --> no
            XSLFAutoShape rectangle_no2 = entscheidungsFolie.createAutoShape();
            rectangle_no2.setShapeType(ShapeType.RECT);
            rectangle_no2.setAnchor(new Rectangle2D.Double(540,460,25,25));
            rectangle_no2.setLineColor(new Color(0, 82, 129));
            rectangle_no2.setLineWidth(2.5);
            rectangle_no2.setFillColor(Color.white);

            //description for decisionbox --> no
            XSLFTextBox no = entscheidungsFolie.createTextBox();
            XSLFTextParagraph no_p = no.addNewTextParagraph();
            XSLFTextRun no_r = no_p.addNewTextRun();
            no.setFillColor(new Color(0, 82, 129));
            no_r.setText("NEIN");
            no_p.setLineSpacing(0.5);
            no_p.setTextAlign(TextParagraph.TextAlign.CENTER);
            no_r.setFontColor(Color.white);
            no_r.setFontSize(14.);
            no.setAnchor(new Rectangle(570, 460,70, 25 ));

            //FOOTER
            XSLFTextBox entscheidungsFolie_footer = entscheidungsFolie.createTextBox();
            XSLFTextParagraph entscheidungsFolie_footer_footer_p = entscheidungsFolie_footer.addNewTextParagraph();
            XSLFTextRun entscheidungsFolie_footer_r = entscheidungsFolie_footer_footer_p.addNewTextRun();
            entscheidungsFolie_footer_r.setText("Projekt Portfolio Komitee (PPK) – 19.07.2022     1");
            entscheidungsFolie_footer_r.setFontColor(new Color(0, 82, 129));
            entscheidungsFolie_footer_r.setFontSize(12.);
            entscheidungsFolie_footer.setAnchor(new Rectangle(440, 500, 270, 50));

            generiertePowerPointPraesentation.write(os);


        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
