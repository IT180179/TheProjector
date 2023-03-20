import org.apache.poi.sl.usermodel.*;
import org.apache.poi.ss.usermodel.Table;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xslf.usermodel.*;
import org.apache.xmlbeans.StringEnumAbstractBase;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ProjektUebersichtsFolie {

    public static void main(String[] args) {

        //PowerPoint erstellen
        XMLSlideShow ppt = new XMLSlideShow();

        try (FileOutputStream os = new FileOutputStream("/Users/anabikic/Documents/SCHULE/5BHITM/Diplomarbeit/apachePOI_try/src/main/output/Projektuebersichtsfolie.pptx")) {

            XSLFSlideMaster defaultMaster = ppt.getSlideMasters().get(0);
            XSLFSlideLayout tc = defaultMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);
            XSLFSlide projektUebersichtsFolie = ppt.createSlide(tc);

            //Logo erstellen
            byte[] pictureData = IOUtils.toByteArray(new FileInputStream("/Users/anabikic/Documents/SCHULE/5BHITM/Diplomarbeit/apachePOI_try/src/main/img/logo_sparkasse.png"));
            XSLFPictureData pd = ppt.addPicture(pictureData, PictureData.PictureType.PNG);
            XSLFPictureShape pic = projektUebersichtsFolie.createPicture(pd);
            pic.setAnchor(new Rectangle(540, 20, 150, 50));

            //Titel erstellen
            XSLFTextShape title = projektUebersichtsFolie.getPlaceholder(0);
            title.clearText();
            XSLFTextParagraph p1 = title.addNewTextParagraph();
            XSLFTextRun r1 = p1.addNewTextRun();
            r1.setText("Beratungszentrum (Betreuerteam Spk OÖ)");
            r1.setFontColor(new Color(0, 82, 129));
            r1.setFontSize(24.0);
            r1.setBold(true);
            title.setAnchor(new Rectangle(20, 20, 500, 50));

            XSLFTextShape body = projektUebersichtsFolie.getPlaceholder(1);
            body.clearText();

            XSLFTable tbl = body.getSheet().createTable();
            tbl.setAnchor(new Rectangle(10, 80, 0, 0));

            int numColumns = 3;
            XSLFTableRow headerRow = tbl.addRow();
            headerRow.setHeight(20);

            //Header für Tabelle
            for (int i = 0; i < numColumns; i++) {
                XSLFTableCell th = headerRow.addCell();
                XSLFTextParagraph p = th.addNewTextParagraph();
                p.setTextAlign(TextParagraph.TextAlign.CENTER);
                XSLFTextRun r = p.addNewTextRun();
                if (i == 0) {
                    r.setText("Nenning/\nBrandstetter");
                    tbl.setColumnWidth(i, 110);
                    th.setFillColor(new Color(0, 82, 129));

                    th.setVerticalAlignment(org.apache.poi.sl.usermodel.VerticalAlignment.MIDDLE);

                    th.setBorderColor(TableCell.BorderEdge.right, Color.white);
                    th.setBorderColor(TableCell.BorderEdge.bottom, Color.white);
                    th.setBorderWidth(TableCell.BorderEdge.bottom, 3);

                    r.setFontSize(12.0);
                    r.setFontColor(Color.white);
                } else if (i == 1) {
                    r.setText("Status");
                    tbl.setColumnWidth(i, 110);
                    th.setFillColor(new Color(0, 82, 129));

                    th.setVerticalAlignment(org.apache.poi.sl.usermodel.VerticalAlignment.MIDDLE);

                    th.setBorderColor(TableCell.BorderEdge.right, Color.white);
                    th.setBorderColor(TableCell.BorderEdge.bottom, Color.white);
                    th.setBorderWidth(TableCell.BorderEdge.bottom, 3);

                    r.setFontSize(13.0);
                    r.setFontColor(Color.white);
                    r.setBold(true);
                } else {
                    r.setText("Kommentar");
                    tbl.setColumnWidth(i, 480);
                    th.setFillColor(new Color(0, 82, 129));

                    th.setVerticalAlignment(org.apache.poi.sl.usermodel.VerticalAlignment.MIDDLE);

                    th.setBorderColor(TableCell.BorderEdge.right, Color.white);
                    th.setBorderColor(TableCell.BorderEdge.bottom, Color.white);
                    th.setBorderWidth(TableCell.BorderEdge.bottom, 3);

                    r.setFontSize(13.0);
                    r.setFontColor(Color.white);
                    r.setBold(true);
                }

            }

            //Zeile INHALT des Projektes
            XSLFTableRow tr_inhalt;
            tr_inhalt=tbl.addRow();
            tr_inhalt.setHeight(240);

                //----------------------------------------------INHALT selbts --> bleibt immer fix
                XSLFTableCell cell_inhalt = tr_inhalt.addCell();
                XSLFTextParagraph cell_inhalt_p = cell_inhalt.addNewTextParagraph();
                XSLFTextRun cell_inhalt_r = cell_inhalt_p.addNewTextRun();

                cell_inhalt.setFillColor(new Color(0, 82, 129));
                cell_inhalt.setVerticalAlignment(org.apache.poi.sl.usermodel.VerticalAlignment.MIDDLE);

                cell_inhalt.setBorderColor(TableCell.BorderEdge.bottom,Color.white);
                cell_inhalt.setBorderColor(TableCell.BorderEdge.right,Color.white);
                cell_inhalt.setBorderWidth(TableCell.BorderEdge.right,1);

                cell_inhalt_r.setText("Inhalt");
                cell_inhalt_r.setFontColor(Color.white);
                cell_inhalt_r.setFontSize(12.0);
                cell_inhalt_r.setBold(true);

                //----------------------------------------------Status --> kommt aus der Datenbank
                XSLFTableCell cell_inhalt_status = tr_inhalt.addCell();

                cell_inhalt_status.setFillColor(new Color(195,227,248));
                cell_inhalt_status.setVerticalAlignment(org.apache.poi.sl.usermodel.VerticalAlignment.MIDDLE);


                cell_inhalt_status.setBorderColor(TableCell.BorderEdge.bottom,Color.white);
                cell_inhalt_status.setBorderColor(TableCell.BorderEdge.right,Color.white);
                cell_inhalt_status.setBorderWidth(TableCell.BorderEdge.right,1);

                //----------------------------------------------Kommentar --> kommt aus der Datenbank
                XSLFTableCell cell_inhalt_kommentar = tr_inhalt.addCell();

                cell_inhalt_kommentar.setFillColor(new Color(195,227,248));
                cell_inhalt_kommentar.setVerticalAlignment(org.apache.poi.sl.usermodel.VerticalAlignment.MIDDLE);

                cell_inhalt_kommentar.setBorderColor(TableCell.BorderEdge.bottom, Color.white);


            //Zeile BUdget des Projektes
            XSLFTableRow tr_budget;
            tr_budget=tbl.addRow();
            tr_budget.setHeight(70);

                //----------------------------------------------Budget selbts --> bleibt immer fix
                XSLFTableCell cell_budget = tr_budget.addCell();
                XSLFTextParagraph cell_budget_p = cell_budget.addNewTextParagraph();
                XSLFTextRun cell_budget_r = cell_budget_p.addNewTextRun();

                cell_budget.setFillColor(new Color(0, 82, 129));
                cell_budget.setVerticalAlignment(org.apache.poi.sl.usermodel.VerticalAlignment.MIDDLE);

                cell_budget.setBorderColor(TableCell.BorderEdge.bottom,Color.white);
                cell_budget.setBorderColor(TableCell.BorderEdge.right,Color.white);
                cell_budget.setBorderWidth(TableCell.BorderEdge.right,1);

                cell_budget_r.setText("Budget/Kosten");
                cell_budget_r.setFontColor(Color.white);
                cell_budget_r.setFontSize(12.0);
                cell_budget_r.setBold(true);

                //----------------------------------------------Status --> kommt aus der Datenbank
                XSLFTableCell cell_budget_status = tr_budget.addCell();

                cell_budget_status.setFillColor(new Color(226,242,252));
                cell_budget_status.setVerticalAlignment(org.apache.poi.sl.usermodel.VerticalAlignment.MIDDLE);

                cell_budget_status.setBorderColor(TableCell.BorderEdge.bottom,Color.white);
                cell_budget_status.setBorderColor(TableCell.BorderEdge.right,Color.white);
                cell_budget_status.setBorderWidth(TableCell.BorderEdge.right,1);

                //----------------------------------------------Kommentar --> kommt aus der Datenbank
                XSLFTableCell cell_budget_kommentar = tr_budget.addCell();

                cell_budget_kommentar.setFillColor(new Color(226,242,252));
                cell_budget_kommentar.setVerticalAlignment(org.apache.poi.sl.usermodel.VerticalAlignment.MIDDLE);

                cell_budget_kommentar.setBorderColor(TableCell.BorderEdge.bottom, Color.white);

            //Zeile Termine des Projektes
            XSLFTableRow tr_termine;
            tr_termine=tbl.addRow();
            tr_termine.setHeight(70);

                //----------------------------------------------Termin selbts --> bleibt immer fix
                XSLFTableCell cell_termine = tr_termine.addCell();
                XSLFTextParagraph cell_termine_p = cell_termine.addNewTextParagraph();
                XSLFTextRun cell_termine_r = cell_termine_p.addNewTextRun();

                cell_termine.setFillColor(new Color(0, 82, 129));
                cell_termine.setVerticalAlignment(org.apache.poi.sl.usermodel.VerticalAlignment.MIDDLE);

                cell_termine.setBorderColor(TableCell.BorderEdge.bottom,Color.white);
                cell_termine.setBorderColor(TableCell.BorderEdge.right,Color.white);
                cell_termine.setBorderWidth(TableCell.BorderEdge.right,1);

                cell_termine_r.setText("Termine");
                cell_termine_r.setFontColor(Color.white);
                cell_termine_r.setFontSize(12.0);
                cell_termine_r.setBold(true);

                //----------------------------------------------Status --> kommt aus der Datenbank
                XSLFTableCell cell_termine_status = tr_termine.addCell();

                cell_termine_status.setFillColor(new Color(195,227,248));
                cell_termine_status.setVerticalAlignment(org.apache.poi.sl.usermodel.VerticalAlignment.MIDDLE);

                cell_termine_status.setBorderColor(TableCell.BorderEdge.bottom,Color.white);
                cell_termine_status.setBorderColor(TableCell.BorderEdge.right,Color.white);
                cell_termine_status.setBorderWidth(TableCell.BorderEdge.right,1);

                //----------------------------------------------Kommentar --> kommt aus der Datenbank
                XSLFTableCell cell_termine_kommentar = tr_termine.addCell();

                cell_termine_kommentar.setFillColor(new Color(195,227,248));
                cell_termine_kommentar.setVerticalAlignment(org.apache.poi.sl.usermodel.VerticalAlignment.MIDDLE);

                cell_termine_kommentar.setBorderColor(TableCell.BorderEdge.bottom, Color.white);


            //----------------------------------------------------------------------------------------------------------FOOTER
            XSLFTextBox projektuebersichtsfolie_footer = projektUebersichtsFolie.createTextBox();
            XSLFTextParagraph projektuebersichtsfolie_footer_p = projektuebersichtsfolie_footer.addNewTextParagraph();
            XSLFTextRun projektuebersichtsfolie_footer_r = projektuebersichtsfolie_footer_p.addNewTextRun();
            projektuebersichtsfolie_footer_r.setText("Projekt Portfolio Komitee (PPK) – 19.07.2022");
            projektuebersichtsfolie_footer_r.setFontColor(new Color(0, 82, 129));
            projektuebersichtsfolie_footer_r.setFontSize(12.);
            projektuebersichtsfolie_footer.setAnchor(new Rectangle(440, 500, 270, 50));

            //Größenangaben der Folie
            java.awt.Dimension pgsize = ppt.getPageSize();
            int width = pgsize.width;
            int height = pgsize.height;
            System.out.println("width" + width);
            System.out.println("height" + height);

            ppt.write(os);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
