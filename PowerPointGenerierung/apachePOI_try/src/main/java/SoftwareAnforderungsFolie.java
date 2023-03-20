import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.ShapeType;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xslf.usermodel.*;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class SoftwareAnforderungsFolie {
    public static void main(String[] args) {

        //PowerPoint-Präsentation anlegen
        XMLSlideShow ppt = new XMLSlideShow();

        try(FileOutputStream output = new FileOutputStream("/Users/anabikic/Documents/SCHULE/5BHITM/Diplomarbeit/apachePOI_try/src/main/output/Softwareanforderungsfolie.pptx")){

            //SlideMaster, SlideLayout, Slide erstellen
            XSLFSlideMaster defaultMaster = ppt.getSlideMasters().get(0);
            XSLFSlideLayout t = defaultMaster.getLayout(SlideLayout.TITLE);
            XSLFSlide softwareAnforderungsFolie = ppt.createSlide(t);

            //Titel erstellen
            XSLFTextShape title = softwareAnforderungsFolie.getPlaceholder(0);
            title.clearText();
            XSLFTextParagraph pt = title.addNewTextParagraph();
            XSLFTextRun rt = pt.addNewTextRun();
            rt.setText("Softwareanforderungen");
            rt.setFontColor(new Color(0, 82, 129));
            rt.setFontSize(26.0);
            rt.setBold(true);
            title.setAnchor(new Rectangle(10, 20, 380, 50));

            //Untertitel erstellen
            XSLFTextShape subtitle = softwareAnforderungsFolie.getPlaceholder(1);
            subtitle.clearText();
            XSLFTextParagraph pst = subtitle.addNewTextParagraph();
            XSLFTextRun rst = pst.addNewTextRun();
            //aus Datenbank Projektname holen
            rst.setText("Digitaler Vorstandsantrag");
            rst.setFontColor(new Color(0, 82, 129));
            rst.setFontSize(22.0);
            subtitle.setAnchor(new Rectangle(60, 60, 245, 50));

            //Logo einfügen
            byte[] pictureData = IOUtils.toByteArray(new FileInputStream("/Users/anabikic/Documents/SCHULE/5BHITM/Diplomarbeit/apachePOI_try/src/main/img/logo_sparkasse.png"));
            XSLFPictureData pd = ppt.addPicture(pictureData, PictureData.PictureType.PNG);
            XSLFPictureShape pic = softwareAnforderungsFolie.createPicture(pd);
            pic.setAnchor(new Rectangle(540, 20, 150, 50));

            //Textbox --> Auftrag von = bleibt immer fix
            XSLFTextBox auftragVon = softwareAnforderungsFolie.createTextBox();
            XSLFTextParagraph auftragVon_p = auftragVon.addNewTextParagraph();
            XSLFTextRun auftragVon_r = auftragVon_p.addNewTextRun();
            auftragVon_r.setText("Auftrag von:");
            auftragVon_r.setFontColor(new Color(0, 82, 129));
            auftragVon_r.setFontSize(16.0);
            auftragVon_r.setBold(true);
            auftragVon.setAnchor(new Rectangle(65, 100,245, 59 ));

            //Textbox --> Auftraggeber = ändert sich durch Abfrage auf die Datenbank
            XSLFTextBox auftraggeber = softwareAnforderungsFolie.createTextBox();
            XSLFTextParagraph auftraggeber_p = auftraggeber.addNewTextParagraph();
            XSLFTextRun auftraggeber_r = auftraggeber_p.addNewTextRun();
            auftraggeber_r.setText("Pointner Maximilian");
            auftraggeber_r.setFontColor(new Color(0, 82, 129));
            auftraggeber_r.setFontSize(16.0);
            auftraggeber.setAnchor(new Rectangle(155, 100,245, 59 ));

            //Textbox --> geplanter Einsatzpunkt = bleibt immer fix
            XSLFTextBox geplanter_Einsatzpunkt = softwareAnforderungsFolie.createTextBox();
            XSLFTextParagraph geplanter_Einsatzpunkt_p = geplanter_Einsatzpunkt.addNewTextParagraph();
            XSLFTextRun geplanter_Einsatzpunkt_r = geplanter_Einsatzpunkt_p.addNewTextRun();
            geplanter_Einsatzpunkt_r.setText("Geplanter Einsatzpunkt:");
            geplanter_Einsatzpunkt_r.setFontColor(new Color(0, 82, 129));
            geplanter_Einsatzpunkt_r.setFontSize(16.0);
            geplanter_Einsatzpunkt_r.setBold(true);
            geplanter_Einsatzpunkt.setAnchor(new Rectangle(65, 120,245, 59 ));

            //Textbox --> Einsatzpunkt = ändert sich durch Abfrage auf die Datenbak
            XSLFTextBox einsatzpunkt = softwareAnforderungsFolie.createTextBox();
            XSLFTextParagraph einsatzpunkt_p = einsatzpunkt.addNewTextParagraph();
            XSLFTextRun einsatzpunkt_r = einsatzpunkt_p.addNewTextRun();
            einsatzpunkt_r.setText("Q2 2022");
            einsatzpunkt_r.setFontColor(new Color(0, 82, 129));
            einsatzpunkt_r.setFontSize(16.0);
            einsatzpunkt.setAnchor(new Rectangle(230, 120,245, 59 ));

            //Textbox --> Beschreibung = bleibt immer fix
            XSLFTextBox beschreibung = softwareAnforderungsFolie.createTextBox();
            XSLFTextParagraph beschreibung_p = beschreibung.addNewTextParagraph();
            XSLFTextRun beschreibung_r = beschreibung_p.addNewTextRun();
            beschreibung_r.setText("Beschreibung");
            beschreibung_r.setFontColor(new Color(0, 82, 129));
            beschreibung_r.setFontSize(20.0);
            beschreibung_r.setBold(true);
            beschreibung.setAnchor(new Rectangle(65, 180,130, 59 ));

            //Textbox --> Beschreibung = ändert sich durch Abfrage auf die Datenbank
            XSLFTextBox beschreibungsText = softwareAnforderungsFolie.createTextBox();
            XSLFTextParagraph beschreibungsText_p = beschreibungsText.addNewTextParagraph();
            XSLFTextRun beschreibungsText_r = beschreibungsText_p.addNewTextRun();
            beschreibungsText_r.setText("Die Software dient zur digitalen Übermittlung von Vorstandsanträge. Die Vorstandsanträge sollen weiters in einer digitalen Form durch den Vorstand bearbeitet werden können.\n");
            beschreibungsText_r.setFontColor(new Color(0, 82, 129));
            beschreibungsText_r.setFontSize(16.0);
            beschreibungsText.setAnchor(new Rectangle(65, 210,410, 100 ));

            //Textbox --> Status Anforderungsprozess = bleibt immer fix
            XSLFTextBox anforderungsProzess = softwareAnforderungsFolie.createTextBox();
            XSLFTextParagraph anforderungsProzess_p = anforderungsProzess.addNewTextParagraph();
            XSLFTextRun anforderungsProzess_r = anforderungsProzess_p.addNewTextRun();
            anforderungsProzess_r.setText("Status Anforderungsprozess");
            anforderungsProzess_r.setFontColor(new Color(0, 82, 129));
            anforderungsProzess_r.setFontSize(20.0);
            anforderungsProzess_r.setBold(true);
            anforderungsProzess.setAnchor(new Rectangle(65, 330,250, 59 ));

            //Textbox --> Status Anforderungsprozess = ändert sich durch Abfrage auf die Datenbank
            XSLFTextBox anforderungsProzessText = softwareAnforderungsFolie.createTextBox();
            XSLFTextParagraph anforderungsProzessText_p = anforderungsProzessText.addNewTextParagraph();
            XSLFTextRun anforderungsProzessText_r = anforderungsProzessText_p.addNewTextRun();
            anforderungsProzessText_r.setText("Umsetzung via PowerApps / PowerAutomate wurde durchgeführt. Interne Tests des Entwurfs wurden durchgeführt und dabei aufgetretene Fehler behoben.\n" +
                    "Aufgrund der Reevaluierung der Vertraulichkeitsstufen der Datenklassifikation muss die Datenablage gesondert betrachtet werden.");
            anforderungsProzessText_r.setFontColor(new Color(0, 82, 129));
            anforderungsProzessText_r.setFontSize(16.0);
            anforderungsProzessText.setAnchor(new Rectangle(65, 360,410, 100 ));

            //Diagramm
            /*
            XSLFAutoShape rect = s1.createAutoShape();
            rect.setShapeType(ShapeType.RECT);
            rect.setAnchor(new Rectangle2D.Double(startH, calc.getShapeY(0, timeline.getLineHeight(), timeline.getLineHeight(), timeline.getLineSpace(), timeline.getStartY()), shapeWidth, timeline.getLineHeight()));
            rect.setFillColor(parseStringtoColor(timeline.getTlMonthColor()));
            rect.setLineWidth(0.5);
            rect.setLineColor(java.awt.Color.BLACK);
            rect.setHorizontalCentered(true);

            rect.setText(monthNames[currentDate.plusMonths(i - 1).getMonthOfYear() - 1]);
            XSLFTextRun tr = rect.getTextParagraphs().get(0).getTextRuns().get(0);
            tr.setFontSize(timeline.getTlFontSize());
            tr.setFontFamily(timeline.getTlFont());

            rect.setVerticalAlignment(org.apache.poi.sl.usermodel.VerticalAlignment.MIDDLE);
             */

            //----------------------------------------------------------------------------------------------------------ABLAUF-DIAGRAMM

            //Ablaufdiagramm
            XSLFAutoShape anforderungserhebung = softwareAnforderungsFolie.createAutoShape();
            anforderungserhebung.setShapeType(ShapeType.RECT);
            anforderungserhebung.setAnchor(new Rectangle(490, 110,210, 35));

            anforderungserhebung.setFillColor(new Color(172, 192, 219));
            anforderungserhebung.setLineWidth(0.5);
            anforderungserhebung.setLineColor(new Color(172, 192, 219));
            anforderungserhebung.setHorizontalCentered(true);

            anforderungserhebung.setText("Anforderungserhebung");
            XSLFTextRun tr_anforderungserhebung = anforderungserhebung.getTextParagraphs().get(0).getTextRuns().get(0);
            tr_anforderungserhebung.setFontSize(15.0);
            tr_anforderungserhebung.setBold(true);
            tr_anforderungserhebung.setFontColor(Color.white);
            anforderungserhebung.setVerticalAlignment(org.apache.poi.sl.usermodel.VerticalAlignment.MIDDLE);

            //Softwarelösung evaluieren
            XSLFAutoShape softwareloesung_evaluieren = softwareAnforderungsFolie.createAutoShape();
            softwareloesung_evaluieren.setShapeType(ShapeType.RECT);
            softwareloesung_evaluieren.setAnchor(new Rectangle(490, 160,210, 35));

            softwareloesung_evaluieren.setFillColor(new Color(172, 192, 219));
            softwareloesung_evaluieren.setLineWidth(0.5);
            softwareloesung_evaluieren.setLineColor(new Color(172, 192, 219));
            softwareloesung_evaluieren.setHorizontalCentered(true);

            softwareloesung_evaluieren.setText("Softwarelösung evaluieren");
            XSLFTextRun tr_softwareloesung_evaluieren = softwareloesung_evaluieren.getTextParagraphs().get(0).getTextRuns().get(0);
            tr_softwareloesung_evaluieren.setFontSize(15.0);
            tr_softwareloesung_evaluieren.setBold(true);
            tr_softwareloesung_evaluieren.setFontColor(Color.white);
            softwareloesung_evaluieren.setVerticalAlignment(org.apache.poi.sl.usermodel.VerticalAlignment.MIDDLE);

            //RFI erstellen
            XSLFAutoShape rfi = softwareAnforderungsFolie.createAutoShape();
            rfi.setShapeType(ShapeType.RECT);
            rfi.setAnchor(new Rectangle(490, 210,210, 35));

            rfi.setFillColor(new Color(172, 192, 219));
            rfi.setLineWidth(0.5);
            rfi.setLineColor(new Color(172, 192, 219));
            rfi.setHorizontalCentered(true);

            rfi.setText("RFI erstellen");
            XSLFTextRun tr_rfi = rfi.getTextParagraphs().get(0).getTextRuns().get(0);
            tr_rfi.setFontSize(15.0);
            tr_rfi.setBold(true);
            tr_rfi.setFontColor(Color.white);
            rfi.setVerticalAlignment(org.apache.poi.sl.usermodel.VerticalAlignment.MIDDLE);

            //Produktpräsentation
            XSLFAutoShape produktpraesentation = softwareAnforderungsFolie.createAutoShape();
            produktpraesentation.setShapeType(ShapeType.RECT);
            produktpraesentation.setAnchor(new Rectangle(490, 260,210, 35));

            produktpraesentation.setFillColor(new Color(172, 192, 219));
            produktpraesentation.setLineWidth(0.5);
            produktpraesentation.setLineColor(new Color(172, 192, 219));
            produktpraesentation.setHorizontalCentered(true);

            produktpraesentation.setText("Produktpräsentation");
            XSLFTextRun tr_produktpraesentation = produktpraesentation.getTextParagraphs().get(0).getTextRuns().get(0);
            tr_produktpraesentation.setFontSize(15.0);
            tr_produktpraesentation.setBold(true);
            tr_produktpraesentation.setFontColor(Color.white);
            produktpraesentation.setVerticalAlignment(org.apache.poi.sl.usermodel.VerticalAlignment.MIDDLE);

            //Softwarelösung bestimmen
            XSLFAutoShape softwareloesung_erstellen = softwareAnforderungsFolie.createAutoShape();
            softwareloesung_erstellen.setShapeType(ShapeType.RECT);
            softwareloesung_erstellen.setAnchor(new Rectangle(490, 310,210, 35));
            softwareloesung_erstellen.setFillColor(new Color(172, 192, 219));
            softwareloesung_erstellen.setLineWidth(0.5);
            softwareloesung_erstellen.setLineColor(new Color(172, 192, 219));
            softwareloesung_erstellen.setHorizontalCentered(true);

            softwareloesung_erstellen.setText("Softwarelösung bestimmen");
            XSLFTextRun tr_softwareloesung_erstellen = softwareloesung_erstellen.getTextParagraphs().get(0).getTextRuns().get(0);
            tr_softwareloesung_erstellen.setFontSize(15.0);
            tr_softwareloesung_erstellen.setBold(true);
            tr_softwareloesung_erstellen.setFontColor(Color.white);
            softwareloesung_erstellen.setVerticalAlignment(org.apache.poi.sl.usermodel.VerticalAlignment.MIDDLE);

            //IDE erstellen
            XSLFAutoShape ide = softwareAnforderungsFolie.createAutoShape();
            ide.setShapeType(ShapeType.RECT);
            ide.setAnchor(new Rectangle(490, 360,210, 35));
            ide.setFillColor(new Color(210, 210,210));
            ide.setLineWidth(0.5);
            ide.setLineColor(new Color(210, 210,210));
            ide.setHorizontalCentered(true);

            ide.setText("ISD erstellen");
            XSLFTextRun tr_ide = ide.getTextParagraphs().get(0).getTextRuns().get(0);
            tr_ide.setFontSize(15.0);
            tr_ide.setBold(true);
            tr_ide.setFontColor(Color.white);
            ide.setVerticalAlignment(org.apache.poi.sl.usermodel.VerticalAlignment.MIDDLE);

            //Software implementieren
            XSLFAutoShape software_implementieren = softwareAnforderungsFolie.createAutoShape();
            software_implementieren.setShapeType(ShapeType.RECT);
            software_implementieren.setAnchor(new Rectangle(490, 410,210, 35));
            software_implementieren.setFillColor(new Color(217, 171, 168));
            software_implementieren.setLineWidth(0.5);
            software_implementieren.setLineColor(new Color(217, 171, 168));
            software_implementieren.setHorizontalCentered(true);

            software_implementieren.setText("Software implementieren");
            XSLFTextRun tr_software_implementieren = software_implementieren.getTextParagraphs().get(0).getTextRuns().get(0);
            tr_software_implementieren.setFontSize(15.0);
            tr_software_implementieren.setBold(true);
            tr_software_implementieren.setFontColor(Color.white);
            software_implementieren.setVerticalAlignment(org.apache.poi.sl.usermodel.VerticalAlignment.MIDDLE);

            //----------------------------------------------------------------------------------------------------------FOOTER
            XSLFTextBox softwareanforderungsfolie_footer = softwareAnforderungsFolie.createTextBox();
            XSLFTextParagraph softwareanforderungsfolie_footer_p = softwareanforderungsfolie_footer.addNewTextParagraph();
            XSLFTextRun softwareanforderungsfolie_footer_r = softwareanforderungsfolie_footer_p.addNewTextRun();
            softwareanforderungsfolie_footer_r.setText("Projekt Portfolio Komitee (PPK) – 19.07.2022");
            softwareanforderungsfolie_footer_r.setFontColor(new Color(0, 82, 129));
            softwareanforderungsfolie_footer_r.setFontSize(12.);
            softwareanforderungsfolie_footer.setAnchor(new Rectangle(440, 500, 270, 50));

            //Größenangaben der Folie
            java.awt.Dimension pgsize = ppt.getPageSize();
            int width = pgsize.width;
            int height = pgsize.height;
            System.out.println("width" + width);
            System.out.println("height" + height);


            ppt.write(output);

        }catch(Exception e){
            System.out.println(e);
        }

    }
}
