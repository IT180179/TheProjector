import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.TableCell;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.VerticalAlignment;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xslf.usermodel.*;

import java.awt.*;
import java.io.*;

public class DetailFolie {

    public static void main(String[] args) {

        XMLSlideShow ppt = new XMLSlideShow();
        File output = new File("/Users/anabikic/Documents/SCHULE/5BHITM/Diplomarbeit/apachePOI_try/src/main/output/Detailfolie.pptx");

        try (FileOutputStream os = new FileOutputStream(output)) {


            XSLFSlideMaster defaultMaster = ppt.getSlideMasters().get(0);
            XSLFSlideLayout blank = defaultMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);
            XSLFSlide detailFolie = ppt.createSlide(blank);

            XSLFTextShape detailFolie_title = detailFolie.getPlaceholder(0);
            detailFolie_title.clearText();
            XSLFTextParagraph detailFolie_title_p = detailFolie_title.addNewTextParagraph();
            XSLFTextRun detailFolie_title_r = detailFolie_title_p.addNewTextRun();

            byte[] pictureData = IOUtils.toByteArray(new FileInputStream("/Users/anabikic/Documents/SCHULE/5BHITM/Diplomarbeit/apachePOI_try/src/main/img/logo_sparkasse.png"));
            XSLFPictureData pd = ppt.addPicture(pictureData, PictureData.PictureType.PNG);
            XSLFPictureShape pic = detailFolie.createPicture(pd);
            pic.setAnchor(new Rectangle(540, 20, 150, 50));

            detailFolie_title_r.setText("Detailinformationen");
            detailFolie_title_r.setFontColor(new Color(0, 82, 129));
            detailFolie_title_r.setFontSize(24.0);
            detailFolie_title_r.setBold(true);
            detailFolie_title.setAnchor(new Rectangle(20, 20, 500, 50));

            //Body
            XSLFTextShape body_detailFolie = detailFolie.getPlaceholder(1);
            body_detailFolie.clearText();

            XSLFTable table_detailFolie = body_detailFolie.getSheet().createTable();
            table_detailFolie.setAnchor(new Rectangle(10, 80, 0, 0));

            int numColumns_detailFolie = 2;
            XSLFTableRow headerRow_detailFolie = table_detailFolie.addRow();
            headerRow_detailFolie.setHeight(5.0);


            //Header für Tabelle
            for (int i = 0; i < numColumns_detailFolie; i++) {

                XSLFTableCell th_detailFolie = headerRow_detailFolie.addCell();
                XSLFTextParagraph p_th_detailFolie = th_detailFolie.addNewTextParagraph();
                p_th_detailFolie.setTextAlign(TextParagraph.TextAlign.CENTER);
                XSLFTextRun r_th_detailFolie = p_th_detailFolie.addNewTextRun();

                if (i == 0) {
                    r_th_detailFolie.setText(".");
                    r_th_detailFolie.setFontSize(2.0);
                    table_detailFolie.setColumnWidth(i, 350);
                    th_detailFolie.setFillColor(new Color(0, 82, 129));
                    r_th_detailFolie.setFontColor(new Color(0, 82, 129));
                }
                if (i == 1) {
                    r_th_detailFolie.setText(".");
                    r_th_detailFolie.setFontSize(2.0);
                    table_detailFolie.setColumnWidth(i, 350);
                    th_detailFolie.setFillColor(new Color(0, 82, 129));
                    r_th_detailFolie.setFontColor(new Color(0, 82, 129));
                }
            }

            //Reihe Projektname
            XSLFTableRow detailFoli_tr_projektTitel;
            detailFoli_tr_projektTitel = table_detailFolie.addRow();
            detailFoli_tr_projektTitel.setHeight(10.0);

            //----------------------------------------------Projektname
            XSLFTableCell cell_detailFolie_projektname = detailFoli_tr_projektTitel.addCell();
            XSLFTextParagraph cell_detailFolie_projektname_p = cell_detailFolie_projektname.addNewTextParagraph();
            XSLFTextRun cell_detailFolie_projektname_r = cell_detailFolie_projektname_p.addNewTextRun();

            cell_detailFolie_projektname.setFillColor(new Color(255, 255, 255));
            cell_detailFolie_projektname.setVerticalAlignment(VerticalAlignment.MIDDLE);

            cell_detailFolie_projektname.setBorderColor(TableCell.BorderEdge.top, Color.white);
            cell_detailFolie_projektname.setBorderColor(TableCell.BorderEdge.right, Color.white);
            cell_detailFolie_projektname.setBorderColor(TableCell.BorderEdge.left, Color.white);
            cell_detailFolie_projektname.setBorderColor(TableCell.BorderEdge.bottom, new Color(211, 211, 211));
            cell_detailFolie_projektname.setBorderWidth(TableCell.BorderEdge.bottom, 1);

            cell_detailFolie_projektname_r.setText("Projekttitel:");
            cell_detailFolie_projektname_r.setFontColor(new Color(0, 82, 129));
            cell_detailFolie_projektname_r.setFontSize(10.0);
            cell_detailFolie_projektname_r.setBold(true);

            //----------------------------------------------eingegebener Projektname
            XSLFTableCell cell_detailFolie_projektname_eingegeben = detailFoli_tr_projektTitel.addCell();
            XSLFTextParagraph cell_detailFolie_projektname_eingegeben_p = cell_detailFolie_projektname_eingegeben.addNewTextParagraph();
            XSLFTextRun cell_detailFolie_projektname_eingegeben_r = cell_detailFolie_projektname_eingegeben_p.addNewTextRun();


            cell_detailFolie_projektname_eingegeben.setFillColor(new Color(255, 255, 255));
            cell_detailFolie_projektname_eingegeben.setVerticalAlignment(VerticalAlignment.MIDDLE);


            cell_detailFolie_projektname_eingegeben.setBorderColor(TableCell.BorderEdge.top, Color.white);
            cell_detailFolie_projektname_eingegeben.setBorderColor(TableCell.BorderEdge.right, Color.white);
            cell_detailFolie_projektname_eingegeben.setBorderColor(TableCell.BorderEdge.left, Color.white);
            cell_detailFolie_projektname_eingegeben.setBorderColor(TableCell.BorderEdge.bottom, new Color(211, 211, 211));
            cell_detailFolie_projektname_eingegeben.setBorderWidth(TableCell.BorderEdge.bottom, 1);

            cell_detailFolie_projektname_eingegeben_r.setText("The Projector");
            cell_detailFolie_projektname_eingegeben_r.setFontColor(new Color(100, 100, 100));
            cell_detailFolie_projektname_eingegeben_r.setFontSize(10.0);

            //Reihe Projektmanager
            XSLFTableRow detailFolie_tr_projektStatus;
            detailFolie_tr_projektStatus = table_detailFolie.addRow();
            detailFolie_tr_projektStatus.setHeight(20.0);

            //----------------------------------------------Projektmanager
            XSLFTableCell cell_detailFolie_projektStatus = detailFolie_tr_projektStatus.addCell();
            XSLFTextParagraph cell_detailFolie_projektStatus_p = cell_detailFolie_projektStatus.addNewTextParagraph();
            XSLFTextRun cell_detailFolie_projektStatus_r = cell_detailFolie_projektStatus_p.addNewTextRun();

            cell_detailFolie_projektStatus.setFillColor(new Color(255, 255, 255));
            cell_detailFolie_projektStatus.setVerticalAlignment(VerticalAlignment.MIDDLE);

            cell_detailFolie_projektStatus.setBorderColor(TableCell.BorderEdge.top, Color.white);
            cell_detailFolie_projektStatus.setBorderColor(TableCell.BorderEdge.right, Color.white);
            cell_detailFolie_projektStatus.setBorderColor(TableCell.BorderEdge.left, Color.white);
            cell_detailFolie_projektStatus.setBorderColor(TableCell.BorderEdge.bottom, new Color(211, 211, 211));
            cell_detailFolie_projektStatus.setBorderWidth(TableCell.BorderEdge.bottom, 1);

            cell_detailFolie_projektStatus_r.setText("Projektmanager:");
            cell_detailFolie_projektStatus_r.setFontColor(new Color(0, 82, 129));
            cell_detailFolie_projektStatus_r.setFontSize(10.0);
            cell_detailFolie_projektStatus_r.setBold(true);

            //----------------------------------------------eingegebener Projektmanager
            XSLFTableCell cell_detailFolie_projektStatus_eingegeben = detailFolie_tr_projektStatus.addCell();
            XSLFTextParagraph cell_detailFolie_projektStatus_eingegeben_p = cell_detailFolie_projektStatus_eingegeben.addNewTextParagraph();
            XSLFTextRun cell_detailFolie_projektStatus_eingegeben_r = cell_detailFolie_projektStatus_eingegeben_p.addNewTextRun();


            cell_detailFolie_projektStatus_eingegeben.setFillColor(new Color(255, 255, 255));
            cell_detailFolie_projektStatus_eingegeben.setVerticalAlignment(VerticalAlignment.MIDDLE);


            cell_detailFolie_projektStatus_eingegeben.setBorderColor(TableCell.BorderEdge.top, Color.white);
            cell_detailFolie_projektStatus_eingegeben.setBorderColor(TableCell.BorderEdge.right, Color.white);
            cell_detailFolie_projektStatus_eingegeben.setBorderColor(TableCell.BorderEdge.left, Color.white);
            cell_detailFolie_projektStatus_eingegeben.setBorderColor(TableCell.BorderEdge.bottom, new Color(211, 211, 211));
            cell_detailFolie_projektStatus_eingegeben.setBorderWidth(TableCell.BorderEdge.bottom, 1);

            cell_detailFolie_projektStatus_eingegeben_r.setText("Simon Walchshofer");
            cell_detailFolie_projektStatus_eingegeben_r.setFontColor(new Color(100, 100, 100));
            cell_detailFolie_projektStatus_eingegeben_r.setFontSize(10.0);

            //Reihe Status
            XSLFTableRow detailFolie_tr_status;
            detailFolie_tr_status = table_detailFolie.addRow();
            detailFolie_tr_status.setHeight(20.0);

            //----------------------------------------------Status
            XSLFTableCell cell_detailFolie_status = detailFolie_tr_status.addCell();
            XSLFTextParagraph cell_detailFolie_status_p = cell_detailFolie_status.addNewTextParagraph();
            XSLFTextRun cell_detailFolie_status_r = cell_detailFolie_status_p.addNewTextRun();

            cell_detailFolie_status.setFillColor(new Color(255, 255, 255));
            cell_detailFolie_status.setVerticalAlignment(VerticalAlignment.MIDDLE);

            cell_detailFolie_status.setBorderColor(TableCell.BorderEdge.top, Color.white);
            cell_detailFolie_status.setBorderColor(TableCell.BorderEdge.right, Color.white);
            cell_detailFolie_status.setBorderColor(TableCell.BorderEdge.left, Color.white);
            cell_detailFolie_status.setBorderColor(TableCell.BorderEdge.bottom, new Color(211, 211, 211));
            cell_detailFolie_status.setBorderWidth(TableCell.BorderEdge.bottom, 1);

            cell_detailFolie_status_r.setText("Status:");
            cell_detailFolie_status_r.setFontColor(new Color(0, 82, 129));
            cell_detailFolie_status_r.setFontSize(10.0);
            cell_detailFolie_status_r.setBold(true);

            //----------------------------------------------eingegebener Status
            XSLFTableCell cell_detailFolie_status_eingegeben = detailFolie_tr_status.addCell();
            XSLFTextParagraph cell_detailFolie_status_eingegeben_p = cell_detailFolie_status_eingegeben.addNewTextParagraph();
            XSLFTextRun cell_detailFolie_status_eingegeben_r = cell_detailFolie_status_eingegeben_p.addNewTextRun();


            cell_detailFolie_status_eingegeben.setFillColor(new Color(255, 255, 255));
            cell_detailFolie_status_eingegeben.setVerticalAlignment(VerticalAlignment.MIDDLE);


            cell_detailFolie_status_eingegeben.setBorderColor(TableCell.BorderEdge.top, Color.white);
            cell_detailFolie_status_eingegeben.setBorderColor(TableCell.BorderEdge.right, Color.white);
            cell_detailFolie_status_eingegeben.setBorderColor(TableCell.BorderEdge.left, Color.white);
            cell_detailFolie_status_eingegeben.setBorderColor(TableCell.BorderEdge.bottom, new Color(211, 211, 211));
            cell_detailFolie_status_eingegeben.setBorderWidth(TableCell.BorderEdge.bottom, 1);

            cell_detailFolie_status_eingegeben_r.setText("10.06.2023");
            cell_detailFolie_status_eingegeben_r.setFontColor(new Color(100, 100, 100));
            cell_detailFolie_status_eingegeben_r.setFontSize(10.0);


            //Reihe Startdatum
            XSLFTableRow detailFolie_tr_startDatum;
            detailFolie_tr_startDatum = table_detailFolie.addRow();
            detailFolie_tr_startDatum.setHeight(20.0);

            //----------------------------------------------Startdatum
            XSLFTableCell cell_detailFolie_startDatum = detailFolie_tr_startDatum.addCell();
            XSLFTextParagraph cell_detailFolie_startDatum_p = cell_detailFolie_startDatum.addNewTextParagraph();
            XSLFTextRun cell_detailFolie_startDatum_r = cell_detailFolie_startDatum_p.addNewTextRun();

            cell_detailFolie_startDatum.setFillColor(new Color(255, 255, 255));
            cell_detailFolie_startDatum.setVerticalAlignment(VerticalAlignment.MIDDLE);

            cell_detailFolie_startDatum.setBorderColor(TableCell.BorderEdge.top, Color.white);
            cell_detailFolie_startDatum.setBorderColor(TableCell.BorderEdge.right, Color.white);
            cell_detailFolie_startDatum.setBorderColor(TableCell.BorderEdge.left, Color.white);
            cell_detailFolie_startDatum.setBorderColor(TableCell.BorderEdge.bottom, new Color(211, 211, 211));
            cell_detailFolie_startDatum.setBorderWidth(TableCell.BorderEdge.bottom, 1);

            cell_detailFolie_startDatum_r.setText("Start-Datum:");
            cell_detailFolie_startDatum_r.setFontColor(new Color(0, 82, 129));
            cell_detailFolie_startDatum_r.setFontSize(10.0);
            cell_detailFolie_startDatum_r.setBold(true);

            //----------------------------------------------eingegebenes Startdatum
            XSLFTableCell cell_detailFolie_startDatum_eingegeben = detailFolie_tr_startDatum.addCell();
            XSLFTextParagraph cell_detailFolie_startDatum_eingegeben_p = cell_detailFolie_startDatum_eingegeben.addNewTextParagraph();
            XSLFTextRun cell_detailFolie_startDatum_eingegeben_r = cell_detailFolie_startDatum_eingegeben_p.addNewTextRun();


            cell_detailFolie_startDatum_eingegeben.setFillColor(new Color(255, 255, 255));
            cell_detailFolie_startDatum_eingegeben.setVerticalAlignment(VerticalAlignment.MIDDLE);


            cell_detailFolie_startDatum_eingegeben.setBorderColor(TableCell.BorderEdge.top, Color.white);
            cell_detailFolie_startDatum_eingegeben.setBorderColor(TableCell.BorderEdge.right, Color.white);
            cell_detailFolie_startDatum_eingegeben.setBorderColor(TableCell.BorderEdge.left, Color.white);
            cell_detailFolie_startDatum_eingegeben.setBorderColor(TableCell.BorderEdge.bottom, new Color(211, 211, 211));
            cell_detailFolie_startDatum_eingegeben.setBorderWidth(TableCell.BorderEdge.bottom, 1);

            cell_detailFolie_startDatum_eingegeben_r.setText("22.10.2022");
            cell_detailFolie_startDatum_eingegeben_r.setFontColor(new Color(100, 100, 100));
            cell_detailFolie_startDatum_eingegeben_r.setFontSize(10.0);

            //Reihe End-Datum
            XSLFTableRow detailFolie_tr_endDatum;
            detailFolie_tr_endDatum = table_detailFolie.addRow();
            detailFolie_tr_endDatum.setHeight(20.0);

            //----------------------------------------------End-Datum
            XSLFTableCell cell_detailFolie_endDatum = detailFolie_tr_endDatum.addCell();
            XSLFTextParagraph cell_detailFolie_endDatum_p = cell_detailFolie_endDatum.addNewTextParagraph();
            XSLFTextRun cell_detailFolie_endDatum_r = cell_detailFolie_endDatum_p.addNewTextRun();

            cell_detailFolie_endDatum.setFillColor(new Color(255, 255, 255));
            cell_detailFolie_endDatum.setVerticalAlignment(VerticalAlignment.MIDDLE);

            cell_detailFolie_endDatum.setBorderColor(TableCell.BorderEdge.top, Color.white);
            cell_detailFolie_endDatum.setBorderColor(TableCell.BorderEdge.right, Color.white);
            cell_detailFolie_endDatum.setBorderColor(TableCell.BorderEdge.left, Color.white);
            cell_detailFolie_endDatum.setBorderColor(TableCell.BorderEdge.bottom, new Color(255, 255, 255));
            cell_detailFolie_endDatum.setBorderWidth(TableCell.BorderEdge.bottom, 1);

            cell_detailFolie_endDatum_r.setText("End-Datum:");
            cell_detailFolie_endDatum_r.setFontColor(new Color(0, 82, 129));
            cell_detailFolie_endDatum_r.setFontSize(10.0);
            cell_detailFolie_endDatum_r.setBold(true);

            //----------------------------------------------eingegebenes End-Datum
            XSLFTableCell cell_detailFolie_endDatum_eingegeben = detailFolie_tr_endDatum.addCell();
            XSLFTextParagraph cell_detailFolie_endDatum_eingegeben_p = cell_detailFolie_endDatum_eingegeben.addNewTextParagraph();
            XSLFTextRun cell_detailFolie_endDatum_eingegeben_r = cell_detailFolie_endDatum_eingegeben_p.addNewTextRun();


            cell_detailFolie_endDatum_eingegeben.setFillColor(new Color(255, 255, 255));
            cell_detailFolie_endDatum_eingegeben.setVerticalAlignment(VerticalAlignment.MIDDLE);


            cell_detailFolie_endDatum_eingegeben.setBorderColor(TableCell.BorderEdge.top, Color.white);
            cell_detailFolie_endDatum_eingegeben.setBorderColor(TableCell.BorderEdge.right, Color.white);
            cell_detailFolie_endDatum_eingegeben.setBorderColor(TableCell.BorderEdge.left, Color.white);
            cell_detailFolie_endDatum_eingegeben.setBorderColor(TableCell.BorderEdge.bottom, new Color(255, 255, 255));
            cell_detailFolie_endDatum_eingegeben.setBorderWidth(TableCell.BorderEdge.bottom, 1);

            cell_detailFolie_endDatum_eingegeben_r.setText("10.06.2023");
            cell_detailFolie_endDatum_eingegeben_r.setFontColor(new Color(100, 100, 100));
            cell_detailFolie_endDatum_eingegeben_r.setFontSize(10.0);

            //------------------------------------------------------------------------------------------------------------

            //Tabelle anlegen
            XSLFTable table_detailFolie_meilensteine = body_detailFolie.getSheet().createTable();
            table_detailFolie_meilensteine.setAnchor(new Rectangle(10, 230, 0, 0));

            int numColumns_detailFolie_meilensteine = 5;
            XSLFTableRow headerRow_detailFolie_meilensteine = table_detailFolie_meilensteine.addRow();

            for (int i = 0; i < 5; i++) {
                XSLFTableCell th_detailFolie_meilensteine = headerRow_detailFolie_meilensteine.addCell();
                XSLFTextParagraph p_th_detailFolie_meilensteine = th_detailFolie_meilensteine.addNewTextParagraph();
                p_th_detailFolie_meilensteine.setTextAlign(TextParagraph.TextAlign.LEFT);
                XSLFTextRun r_th_detailFolie_meilensteine = p_th_detailFolie_meilensteine.addNewTextRun();
                r_th_detailFolie_meilensteine.setBold(true);

                if (i == 0) {

                    r_th_detailFolie_meilensteine.setText("Titel");
                    table_detailFolie_meilensteine.setColumnWidth(i, 144);
                    table_detailFolie_meilensteine.setRowHeight(i, 20.0);
                    th_detailFolie_meilensteine.setFillColor(new Color(0, 82, 129));

                    th_detailFolie_meilensteine.setVerticalAlignment(VerticalAlignment.MIDDLE);
                    th_detailFolie_meilensteine.setBorderColor(TableCell.BorderEdge.bottom, new Color(0, 82, 129));
                    th_detailFolie_meilensteine.setBorderWidth(TableCell.BorderEdge.bottom, 1);

                    r_th_detailFolie_meilensteine.setFontColor(new Color(255, 255, 255));
                    r_th_detailFolie_meilensteine.setFontSize(10.0);

                }
                if (i == 1) {

                    r_th_detailFolie_meilensteine.setText("Beschreibung");
                    table_detailFolie_meilensteine.setColumnWidth(i, 170);
                    th_detailFolie_meilensteine.setFillColor(new Color(0, 82, 129));

                    th_detailFolie_meilensteine.setVerticalAlignment(VerticalAlignment.MIDDLE);
                    th_detailFolie_meilensteine.setBorderColor(TableCell.BorderEdge.bottom, new Color(0, 82, 129));
                    th_detailFolie_meilensteine.setBorderWidth(TableCell.BorderEdge.bottom, 1);

                    r_th_detailFolie_meilensteine.setFontColor(new Color(255, 255, 255));
                    r_th_detailFolie_meilensteine.setFontSize(10.0);

                }
                if (i == 2) {

                    r_th_detailFolie_meilensteine.setText("Status");
                    table_detailFolie_meilensteine.setColumnWidth(i, 144);
                    th_detailFolie_meilensteine.setFillColor(new Color(0, 82, 129));

                    th_detailFolie_meilensteine.setVerticalAlignment(VerticalAlignment.MIDDLE);
                    th_detailFolie_meilensteine.setBorderColor(TableCell.BorderEdge.bottom, new Color(0, 82, 129));
                    th_detailFolie_meilensteine.setBorderWidth(TableCell.BorderEdge.bottom, 1);

                    r_th_detailFolie_meilensteine.setFontColor(new Color(255, 255, 255));
                    r_th_detailFolie_meilensteine.setFontSize(10.0);

                }
                if (i == 3) {

                    r_th_detailFolie_meilensteine.setText("Start-Datum");
                    table_detailFolie_meilensteine.setColumnWidth(i, 120);
                    th_detailFolie_meilensteine.setFillColor(new Color(0, 82, 129));

                    th_detailFolie_meilensteine.setVerticalAlignment(VerticalAlignment.MIDDLE);
                    th_detailFolie_meilensteine.setBorderColor(TableCell.BorderEdge.bottom, new Color(0, 82, 129));
                    th_detailFolie_meilensteine.setBorderWidth(TableCell.BorderEdge.bottom, 1);

                    r_th_detailFolie_meilensteine.setFontColor(new Color(255, 255, 255));
                    r_th_detailFolie_meilensteine.setFontSize(10.0);

                }
                if (i == 4) {

                    r_th_detailFolie_meilensteine.setText("End-Datum");
                    table_detailFolie_meilensteine.setColumnWidth(i, 120);
                    th_detailFolie_meilensteine.setFillColor(new Color(0, 82, 129));

                    th_detailFolie_meilensteine.setVerticalAlignment(VerticalAlignment.MIDDLE);
                    th_detailFolie_meilensteine.setBorderColor(TableCell.BorderEdge.bottom, new Color(0, 82, 129));
                    th_detailFolie_meilensteine.setBorderWidth(TableCell.BorderEdge.bottom, 1);

                    r_th_detailFolie_meilensteine.setFontColor(new Color(255, 255, 255));
                    r_th_detailFolie_meilensteine.setFontSize(10.0);

                }
            }

            int anzahlMeilensteine = 5; //--> holen aus der Datenbank

            for (int a = 0; a < anzahlMeilensteine; a++) {

                //Erstes Projekt
                XSLFTableRow detailFolie_tr_meilensteinTitle;
                detailFolie_tr_meilensteinTitle = table_detailFolie_meilensteine.addRow();
                detailFolie_tr_meilensteinTitle.setHeight(25.0);

                //----------------------------------------------Meilensteintitel
                XSLFTableCell cell_detailFolie_meielnsteinTitel = detailFolie_tr_meilensteinTitle.addCell();
                XSLFTextParagraph cell_detailFolie_meielnsteinTitel_p = cell_detailFolie_meielnsteinTitel.addNewTextParagraph();
                XSLFTextRun cell_detailFolie_meielnsteinTitel_r = cell_detailFolie_meielnsteinTitel_p.addNewTextRun();

                cell_detailFolie_meielnsteinTitel.setFillColor(new Color(255, 255, 255));
                cell_detailFolie_meielnsteinTitel.setVerticalAlignment(VerticalAlignment.MIDDLE);

                cell_detailFolie_meielnsteinTitel.setBorderColor(TableCell.BorderEdge.top, Color.white);
                cell_detailFolie_meielnsteinTitel.setBorderColor(TableCell.BorderEdge.right, Color.white);
                cell_detailFolie_meielnsteinTitel.setBorderColor(TableCell.BorderEdge.left, Color.white);
                cell_detailFolie_meielnsteinTitel.setBorderColor(TableCell.BorderEdge.bottom, new Color(211, 211, 211));
                cell_detailFolie_meielnsteinTitel.setBorderWidth(TableCell.BorderEdge.bottom, 1);

                cell_detailFolie_meielnsteinTitel_r.setText("Projektstart erfolgt");
                cell_detailFolie_meielnsteinTitel_r.setFontColor(new Color(0, 82, 129));
                cell_detailFolie_meielnsteinTitel_r.setFontSize(10.0);

                //----------------------------------------------Beschreibung
                XSLFTableCell cell_detailFolie_meielnsteinBeschreibung = detailFolie_tr_meilensteinTitle.addCell();
                XSLFTextParagraph cell_detailFolie_meielnsteinBeschreibung_p = cell_detailFolie_meielnsteinBeschreibung.addNewTextParagraph();
                XSLFTextRun cell_detailFolie_meielnsteinBeschreibung_r = cell_detailFolie_meielnsteinBeschreibung_p.addNewTextRun();


                cell_detailFolie_meielnsteinBeschreibung.setFillColor(new Color(255, 255, 255));
                cell_detailFolie_meielnsteinBeschreibung.setVerticalAlignment(VerticalAlignment.MIDDLE);


                cell_detailFolie_meielnsteinBeschreibung.setBorderColor(TableCell.BorderEdge.top, Color.white);
                cell_detailFolie_meielnsteinBeschreibung.setBorderColor(TableCell.BorderEdge.right, Color.white);
                cell_detailFolie_meielnsteinBeschreibung.setBorderColor(TableCell.BorderEdge.left, Color.white);
                cell_detailFolie_meielnsteinBeschreibung.setBorderColor(TableCell.BorderEdge.bottom, new Color(211, 211, 211));
                cell_detailFolie_meielnsteinBeschreibung.setBorderWidth(TableCell.BorderEdge.bottom, 1);

                cell_detailFolie_meielnsteinBeschreibung_r.setText("Projekstart erfolgt");
                cell_detailFolie_meielnsteinBeschreibung_r.setFontColor(new Color(100, 100, 100));
                cell_detailFolie_meielnsteinBeschreibung_r.setFontSize(10.0);


                //----------------------------------------------Meilensteinstatus
                XSLFTableCell cell_detailFolie_meielnsteinStatus = detailFolie_tr_meilensteinTitle.addCell();
                XSLFTextParagraph cell_detailFolie_meielnsteinStatus_p = cell_detailFolie_meielnsteinStatus.addNewTextParagraph();
                XSLFTextRun cell_detailFolie_meielnsteinStatus_r = cell_detailFolie_meielnsteinStatus_p.addNewTextRun();


                cell_detailFolie_meielnsteinStatus.setFillColor(new Color(255, 255, 255));
                cell_detailFolie_meielnsteinStatus.setVerticalAlignment(VerticalAlignment.MIDDLE);


                cell_detailFolie_meielnsteinStatus.setBorderColor(TableCell.BorderEdge.top, Color.white);
                cell_detailFolie_meielnsteinStatus.setBorderColor(TableCell.BorderEdge.right, Color.white);
                cell_detailFolie_meielnsteinStatus.setBorderColor(TableCell.BorderEdge.left, Color.white);
                cell_detailFolie_meielnsteinStatus.setBorderColor(TableCell.BorderEdge.bottom, new Color(211, 211, 211));
                cell_detailFolie_meielnsteinStatus.setBorderWidth(TableCell.BorderEdge.bottom, 1);

                cell_detailFolie_meielnsteinStatus_r.setText("1");
                cell_detailFolie_meielnsteinStatus_r.setFontColor(new Color(100, 100, 100));
                cell_detailFolie_meielnsteinStatus_r.setFontSize(10.0);

                //----------------------------------------------StartDatum
                XSLFTableCell cell_detailFolie_meielnsteinStartDatum = detailFolie_tr_meilensteinTitle.addCell();
                XSLFTextParagraph cell_detailFolie_meielnsteinStartDatum_p = cell_detailFolie_meielnsteinStartDatum.addNewTextParagraph();
                XSLFTextRun cell_detailFolie_meielnsteinStartDatum_r = cell_detailFolie_meielnsteinStartDatum_p.addNewTextRun();


                cell_detailFolie_meielnsteinStartDatum.setFillColor(new Color(255, 255, 255));
                cell_detailFolie_meielnsteinStartDatum.setVerticalAlignment(VerticalAlignment.MIDDLE);


                cell_detailFolie_meielnsteinStartDatum.setBorderColor(TableCell.BorderEdge.top, Color.white);
                cell_detailFolie_meielnsteinStartDatum.setBorderColor(TableCell.BorderEdge.right, Color.white);
                cell_detailFolie_meielnsteinStartDatum.setBorderColor(TableCell.BorderEdge.left, Color.white);
                cell_detailFolie_meielnsteinStartDatum.setBorderColor(TableCell.BorderEdge.bottom, new Color(211, 211, 211));
                cell_detailFolie_meielnsteinStartDatum.setBorderWidth(TableCell.BorderEdge.bottom, 1);

                cell_detailFolie_meielnsteinStartDatum_r.setText("2022-07-04");
                cell_detailFolie_meielnsteinStartDatum_r.setFontColor(new Color(100, 100, 100));
                cell_detailFolie_meielnsteinStartDatum_r.setFontSize(10.0);

                //----------------------------------------------EndDatum
                XSLFTableCell cell_detailFolie_meielnsteinEndDatum = detailFolie_tr_meilensteinTitle.addCell();
                XSLFTextParagraph cell_detailFolie_meielnsteinEndDatum_p = cell_detailFolie_meielnsteinEndDatum.addNewTextParagraph();
                XSLFTextRun cell_detailFolie_meielnsteinEndDatum_r = cell_detailFolie_meielnsteinEndDatum_p.addNewTextRun();


                cell_detailFolie_meielnsteinEndDatum.setFillColor(new Color(255, 255, 255));
                cell_detailFolie_meielnsteinEndDatum.setVerticalAlignment(VerticalAlignment.MIDDLE);


                cell_detailFolie_meielnsteinEndDatum.setBorderColor(TableCell.BorderEdge.top, Color.white);
                cell_detailFolie_meielnsteinEndDatum.setBorderColor(TableCell.BorderEdge.right, Color.white);
                cell_detailFolie_meielnsteinEndDatum.setBorderColor(TableCell.BorderEdge.left, Color.white);
                cell_detailFolie_meielnsteinEndDatum.setBorderColor(TableCell.BorderEdge.bottom, new Color(211, 211, 211));
                cell_detailFolie_meielnsteinEndDatum.setBorderWidth(TableCell.BorderEdge.bottom, 1);

                cell_detailFolie_meielnsteinEndDatum_r.setText("2022-07-04");
                cell_detailFolie_meielnsteinEndDatum_r.setFontColor(new Color(100, 100, 100));
                cell_detailFolie_meielnsteinEndDatum_r.setFontSize(10.0);

            }

            //----------------------------------------------------------------------------------------------------------FOOTER
            XSLFTextBox detailffolie_footer = detailFolie.createTextBox();
            XSLFTextParagraph detailffolie_footer_p = detailffolie_footer.addNewTextParagraph();
            XSLFTextRun detailffolie_footer_r = detailffolie_footer_p.addNewTextRun();
            detailffolie_footer_r.setText("Projekt Portfolio Komitee (PPK) – 19.07.2022");
            detailffolie_footer_r.setFontColor(new Color(0, 82, 129));
            detailffolie_footer_r.setFontSize(12.);
            detailffolie_footer.setAnchor(new Rectangle(440, 500, 270, 50));



            ppt.write(os);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}