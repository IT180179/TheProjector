import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.TableCell;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.VerticalAlignment;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xslf.usermodel.*;

import java.awt.*;
import java.io.*;

public class Projektportfolio {

    public static void main(String[] args) {

        XMLSlideShow ppt = new XMLSlideShow();
        File output = new File("/Users/anabikic/Documents/SCHULE/5BHITM/Diplomarbeit/apachePOI_try/src/main/output/Projektportfolio.pptx");

        try(FileOutputStream os = new FileOutputStream(output)) {

            XSLFSlideMaster defaultMaster = ppt.getSlideMasters().get(0);
            XSLFSlideLayout tc = defaultMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);

            XSLFSlide projektportfolio = ppt.createSlide(tc);

            byte[] pictureData = IOUtils.toByteArray(new FileInputStream("/Users/anabikic/Documents/SCHULE/5BHITM/Diplomarbeit/apachePOI_try/src/main/img/logo_sparkasse.png"));
            XSLFPictureData pd = ppt.addPicture(pictureData, PictureData.PictureType.PNG);
            XSLFPictureShape pic = projektportfolio.createPicture(pd);
            pic.setAnchor(new Rectangle(540, 20, 150, 50));

            //Titel erstellen
            XSLFTextShape title_projektportfolio = projektportfolio.getPlaceholder(0);
            title_projektportfolio.clearText();
            //----Text anlegen
            XSLFTextParagraph pTitle_projektportfolio = title_projektportfolio.addNewTextParagraph();
            XSLFTextRun rTitle_projektportfolio = pTitle_projektportfolio.addNewTextRun();
            title_projektportfolio.setVerticalAlignment(VerticalAlignment.MIDDLE);
            rTitle_projektportfolio.setText("Projektportfolio");
            //----Text stylen
            rTitle_projektportfolio.setFontColor(new Color(0, 82, 129));
            rTitle_projektportfolio.setFontSize(24.0);
            rTitle_projektportfolio.setBold(true);
            title_projektportfolio.setAnchor(new Rectangle(20, 20, 500, 50));

            //Body
            XSLFTextShape body_projektportfolio = projektportfolio.getPlaceholder(1);
            body_projektportfolio.clearText();

            //Tabelle anlegen
            XSLFTable table_projektportfolio = body_projektportfolio.getSheet().createTable();
            table_projektportfolio.setAnchor(new Rectangle(10, 80, 0, 0));
            //----Header für die Tabelle
            int numColumns_projektportfolio = 6;
            XSLFTableRow headerRow_projektportfolio = table_projektportfolio.addRow();

            for(int i = 0; i < numColumns_projektportfolio; i++){
                XSLFTableCell th_projektportfolio = headerRow_projektportfolio.addCell();
                XSLFTextParagraph p_th_projektportfolio = th_projektportfolio.addNewTextParagraph();
                p_th_projektportfolio.setTextAlign(TextParagraph.TextAlign.LEFT);
                XSLFTextRun r_th_projektportfolio = p_th_projektportfolio.addNewTextRun();
                if(i==0) {
                    r_th_projektportfolio.setText("Projekt");
                    table_projektportfolio.setColumnWidth(i, 200);
                    table_projektportfolio.setRowHeight(i, 30.0);
                    th_projektportfolio.setFillColor(new Color(255,255,255));

                    th_projektportfolio.setVerticalAlignment(VerticalAlignment.MIDDLE);
                    th_projektportfolio.setBorderColor(TableCell.BorderEdge.bottom, new Color(211,211,211));
                    th_projektportfolio.setBorderWidth(TableCell.BorderEdge.bottom, 1);

                    r_th_projektportfolio.setFontSize(10.0);
                    r_th_projektportfolio.setFontColor(new Color(160,160,160));
                }if(i == 1){
                    r_th_projektportfolio.setText("Status");
                    table_projektportfolio.setColumnWidth(i,100);
                    th_projektportfolio.setFillColor(new Color(255,255,255));

                    th_projektportfolio.setVerticalAlignment(VerticalAlignment.MIDDLE);
                    th_projektportfolio.setBorderColor(TableCell.BorderEdge.bottom, new Color(211,211,211));
                    th_projektportfolio.setBorderWidth(TableCell.BorderEdge.bottom, 1);

                    r_th_projektportfolio.setFontSize(10.0);
                    r_th_projektportfolio.setFontColor(new Color(160,160,160));

                }if(i==2){
                    r_th_projektportfolio.setText("Projektmanager:in");
                    table_projektportfolio.setColumnWidth(i,100);
                    th_projektportfolio.setFillColor(new Color(255,255,255));

                    th_projektportfolio.setVerticalAlignment(VerticalAlignment.MIDDLE);
                    th_projektportfolio.setBorderColor(TableCell.BorderEdge.bottom, new Color(211,211,211));
                    th_projektportfolio.setBorderWidth(TableCell.BorderEdge.bottom, 1);

                    r_th_projektportfolio.setFontSize(10.0);
                    r_th_projektportfolio.setFontColor(new Color(160,160,160));
                }if(i==3){
                    r_th_projektportfolio.setText("Fachkoordinator:in");
                    table_projektportfolio.setColumnWidth(i,100);
                    th_projektportfolio.setFillColor(new Color(255,255,255));

                    th_projektportfolio.setVerticalAlignment(VerticalAlignment.MIDDLE);
                    th_projektportfolio.setBorderColor(TableCell.BorderEdge.bottom, new Color(211,211,211));
                    th_projektportfolio.setBorderWidth(TableCell.BorderEdge.bottom, 1);

                    r_th_projektportfolio.setFontSize(10.0);
                    r_th_projektportfolio.setFontColor(new Color(160,160,160));
                }
                if(i==4){
                    r_th_projektportfolio.setText("Startdatum");
                    table_projektportfolio.setColumnWidth(i,100);
                    th_projektportfolio.setFillColor(new Color(255,255,255));

                    th_projektportfolio.setVerticalAlignment(VerticalAlignment.MIDDLE);
                    th_projektportfolio.setBorderColor(TableCell.BorderEdge.bottom, new Color(211,211,211));
                    th_projektportfolio.setBorderWidth(TableCell.BorderEdge.bottom, 1);

                    r_th_projektportfolio.setFontSize(10.0);
                    r_th_projektportfolio.setFontColor(new Color(160,160,160));
                }if(i==5){
                    r_th_projektportfolio.setText("Planfertigstellung");
                    table_projektportfolio.setColumnWidth(i,100);
                    th_projektportfolio.setFillColor(new Color(255,255,255));

                    th_projektportfolio.setVerticalAlignment(VerticalAlignment.MIDDLE);
                    th_projektportfolio.setBorderColor(TableCell.BorderEdge.bottom, new Color(211,211,211));
                    th_projektportfolio.setBorderWidth(TableCell.BorderEdge.bottom, 1);

                    r_th_projektportfolio.setFontSize(10.0);
                    r_th_projektportfolio.setFontColor(new Color(160,160,160));
                }
            }


            int anzahlProjekte = 5; //--> holen aus der Datenbank

            for(int generate = 0; generate < 5; generate++){

            //Erstes Projekt
            XSLFTableRow projektportfolio_tr_projekt1;
            projektportfolio_tr_projekt1=table_projektportfolio.addRow();
            projektportfolio_tr_projekt1.setHeight(45.0);

            //----------------------------------------------Projektname
            XSLFTableCell cell_projektprotfolio_projektname = projektportfolio_tr_projekt1.addCell();
            XSLFTextParagraph cell_projektprotfolio_projektname_p = cell_projektprotfolio_projektname.addNewTextParagraph();
            XSLFTextRun cell_projektprotfolio_projektname_r = cell_projektprotfolio_projektname_p.addNewTextRun();

            cell_projektprotfolio_projektname.setFillColor(new Color(255,255,255));
            cell_projektprotfolio_projektname.setVerticalAlignment(VerticalAlignment.MIDDLE);

            cell_projektprotfolio_projektname.setBorderColor(TableCell.BorderEdge.top,Color.white);
            cell_projektprotfolio_projektname.setBorderColor(TableCell.BorderEdge.right,Color.white);
            cell_projektprotfolio_projektname.setBorderColor(TableCell.BorderEdge.left,Color.white);
            cell_projektprotfolio_projektname.setBorderColor(TableCell.BorderEdge.bottom,new Color(211,211,211));
            cell_projektprotfolio_projektname.setBorderWidth(TableCell.BorderEdge.bottom,1);

            cell_projektprotfolio_projektname_r.setText("Konzentriertes (qualitatives) Organisationdesign");
            cell_projektprotfolio_projektname_r.setFontColor(new Color(0, 82, 129));
            cell_projektprotfolio_projektname_r.setFontSize(10.0);

            //----------------------------------------------Projektstatus
            XSLFTableCell cell_projektprotfolio_status = projektportfolio_tr_projekt1.addCell();
            XSLFTextParagraph cell_projektprotfolio_status_p = cell_projektprotfolio_status.addNewTextParagraph();
            XSLFTextRun cell_projektprotfolio_status_r = cell_projektprotfolio_status_p.addNewTextRun();


            cell_projektprotfolio_status.setFillColor(new Color(255,255,255));
            cell_projektprotfolio_status.setVerticalAlignment(VerticalAlignment.MIDDLE);


            cell_projektprotfolio_status.setBorderColor(TableCell.BorderEdge.top,Color.white);
            cell_projektprotfolio_status.setBorderColor(TableCell.BorderEdge.right,Color.white);
            cell_projektprotfolio_status.setBorderColor(TableCell.BorderEdge.left,Color.white);
            cell_projektprotfolio_status.setBorderColor(TableCell.BorderEdge.bottom,new Color(211,211,211));
            cell_projektprotfolio_status.setBorderWidth(TableCell.BorderEdge.bottom,1);

            cell_projektprotfolio_status_r.setText("Plangemäß");
            cell_projektprotfolio_status_r.setFontColor(new Color(100,100,100));
            cell_projektprotfolio_status_r.setFontSize(10.0);
            //----------------------------------------------Projektmanager
            XSLFTableCell cell_projektprotfolio_projektmanager = projektportfolio_tr_projekt1.addCell();
            XSLFTextParagraph cell_projektprotfolio_projektmanager_p = cell_projektprotfolio_projektmanager.addNewTextParagraph();
            XSLFTextRun cell_projektprotfolio_projektmanager_r = cell_projektprotfolio_projektmanager_p.addNewTextRun();


            cell_projektprotfolio_projektmanager.setFillColor(new Color(255,255,255));
            cell_projektprotfolio_projektmanager.setVerticalAlignment(VerticalAlignment.MIDDLE);


            cell_projektprotfolio_projektmanager.setBorderColor(TableCell.BorderEdge.top,Color.white);
            cell_projektprotfolio_projektmanager.setBorderColor(TableCell.BorderEdge.right,Color.white);
            cell_projektprotfolio_projektmanager.setBorderColor(TableCell.BorderEdge.left,Color.white);
            cell_projektprotfolio_projektmanager.setBorderColor(TableCell.BorderEdge.bottom,new Color(211,211,211));
            cell_projektprotfolio_projektmanager.setBorderWidth(TableCell.BorderEdge.bottom,1);

            cell_projektprotfolio_projektmanager_r.setText("Wagner Michael");
            cell_projektprotfolio_projektmanager_r.setFontColor(new Color(100,100,100));
            cell_projektprotfolio_projektmanager_r.setFontSize(10.0);
            //----------------------------------------------Fachkoordinator
            XSLFTableCell cell_projektprotfolio_fachkoordinator = projektportfolio_tr_projekt1.addCell();
            XSLFTextParagraph cell_projektprotfolio_fachkoordinator_p = cell_projektprotfolio_fachkoordinator.addNewTextParagraph();
            XSLFTextRun cell_projektprotfolio_fachkoordinator_r = cell_projektprotfolio_fachkoordinator_p.addNewTextRun();


            cell_projektprotfolio_fachkoordinator.setFillColor(new Color(255,255,255));
            cell_projektprotfolio_fachkoordinator.setVerticalAlignment(VerticalAlignment.MIDDLE);


            cell_projektprotfolio_fachkoordinator.setBorderColor(TableCell.BorderEdge.top,Color.white);
            cell_projektprotfolio_fachkoordinator.setBorderColor(TableCell.BorderEdge.right,Color.white);
            cell_projektprotfolio_fachkoordinator.setBorderColor(TableCell.BorderEdge.left,Color.white);
            cell_projektprotfolio_fachkoordinator.setBorderColor(TableCell.BorderEdge.bottom,new Color(211,211,211));
            cell_projektprotfolio_fachkoordinator.setBorderWidth(TableCell.BorderEdge.bottom,1);

            cell_projektprotfolio_fachkoordinator_r.setText("Manfred Stadlinger, Bernhard Wöss");
            cell_projektprotfolio_fachkoordinator_r.setFontColor(new Color(100,100,100));
            cell_projektprotfolio_fachkoordinator_r.setFontSize(10.0);
            //----------------------------------------------Startdatum
            XSLFTableCell cell_projektprotfolio_startdatum = projektportfolio_tr_projekt1.addCell();
            XSLFTextParagraph cell_projektprotfolio_startdatum_p = cell_projektprotfolio_startdatum.addNewTextParagraph();
            XSLFTextRun cell_projektprotfolio_startdatum_r = cell_projektprotfolio_startdatum_p.addNewTextRun();


            cell_projektprotfolio_startdatum.setFillColor(new Color(255,255,255));
            cell_projektprotfolio_startdatum.setVerticalAlignment(VerticalAlignment.MIDDLE);


            cell_projektprotfolio_startdatum.setBorderColor(TableCell.BorderEdge.top,Color.white);
            cell_projektprotfolio_startdatum.setBorderColor(TableCell.BorderEdge.right,Color.white);
            cell_projektprotfolio_startdatum.setBorderColor(TableCell.BorderEdge.left,Color.white);
            cell_projektprotfolio_startdatum.setBorderColor(TableCell.BorderEdge.bottom,new Color(211,211,211));
            cell_projektprotfolio_startdatum.setBorderWidth(TableCell.BorderEdge.bottom,1);

            cell_projektprotfolio_startdatum_r.setText("17.12.2021");
            cell_projektprotfolio_startdatum_r.setFontColor(new Color(100,100,100));
            cell_projektprotfolio_startdatum_r.setFontSize(10.0);
            //----------------------------------------------Planfertigstellung
            XSLFTableCell cell_projektprotfolio_planfertigstellung = projektportfolio_tr_projekt1.addCell();
            XSLFTextParagraph cell_projektprotfolio_planfertigstellung_p = cell_projektprotfolio_planfertigstellung.addNewTextParagraph();
            XSLFTextRun cell_projektprotfolio_planfertigstellung_r = cell_projektprotfolio_planfertigstellung_p.addNewTextRun();


            cell_projektprotfolio_planfertigstellung.setFillColor(new Color(255,255,255));
            cell_projektprotfolio_planfertigstellung.setVerticalAlignment(VerticalAlignment.MIDDLE);


            cell_projektprotfolio_planfertigstellung.setBorderColor(TableCell.BorderEdge.top,Color.white);
            cell_projektprotfolio_planfertigstellung.setBorderColor(TableCell.BorderEdge.right,Color.white);
            cell_projektprotfolio_planfertigstellung.setBorderColor(TableCell.BorderEdge.left,Color.white);
            cell_projektprotfolio_planfertigstellung.setBorderColor(TableCell.BorderEdge.bottom,new Color(211,211,211));
            cell_projektprotfolio_planfertigstellung.setBorderWidth(TableCell.BorderEdge.bottom,1);

            cell_projektprotfolio_planfertigstellung_r.setText("31.12.2022");
            cell_projektprotfolio_planfertigstellung_r.setFontColor(new Color(100,100,100));
            cell_projektprotfolio_planfertigstellung_r.setFontSize(10.0);
            }

            //----------------------------------------------------------------------------------------------------------FOOTER
            XSLFTextBox projektportfolio_footer = projektportfolio.createTextBox();
            XSLFTextParagraph projektportfolio_footer_p = projektportfolio_footer.addNewTextParagraph();
            XSLFTextRun projektportfolio_footer_r = projektportfolio_footer_p.addNewTextRun();
            projektportfolio_footer_r.setText("Projekt Portfolio Komitee (PPK) – 19.07.2022");
            projektportfolio_footer_r.setFontColor(new Color(0, 82, 129));
            projektportfolio_footer_r.setFontSize(12.);
            projektportfolio_footer.setAnchor(new Rectangle(440, 500, 270, 50));

            //Größenangaben der Folie
            java.awt.Dimension pgsize = ppt.getPageSize();
            int width = pgsize.width;
            int height = pgsize.height;
            System.out.println("width" + width);
            System.out.println("height" + height);



            ppt.write(os);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
