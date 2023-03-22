package com.example.helper;

import com.example.api.PPKRessource;
import com.example.model.PersonenAufwandDTO;
import com.example.workloads.Arbeitszeiten.Arbeitszeiten;
import com.example.workloads.Arbeitszeiten.ArbeitszeitenRepo;
import com.example.workloads.Meilensteine.Meilensteine;
import com.example.workloads.Meilensteine.MeilensteineRepo;
import com.example.workloads.PPK.PPKRepo;
import com.example.workloads.Personen.Personen;
import com.example.workloads.Personen.PersonenRepo;
import com.example.workloads.Projekte.Projekte;
import com.example.workloads.Projekte.ProjekteRepo;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.TableCell;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.VerticalAlignment;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xslf.usermodel.*;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DetailPresentationHelper {

    public static ByteArrayInputStream generateDetailPresentation()
            throws FileNotFoundException, IOException, InvalidFormatException {

        final ProjekteRepo projekteRepo = new ProjekteRepo();
        final PPKRepo ppkRepo = new PPKRepo();
        final PersonenRepo personenRepo = new PersonenRepo();
        final MeilensteineRepo meilensteineRepo = new MeilensteineRepo();
        final ArbeitszeitenRepo arbeitszeitenRepo = new ArbeitszeitenRepo();

        List<Projekte> allProjects = new ArrayList<>();
        allProjects = projekteRepo.listAll();

        List<Meilensteine> alleMeilensteine = new ArrayList<>();
        alleMeilensteine = meilensteineRepo.listAll();

        List<Arbeitszeiten> allArbeitszeiten = new ArrayList<>();
        allArbeitszeiten = arbeitszeitenRepo.listAll();

        Long id = 1L;
        Personen projekmanager = projekteRepo.getProjektmanager(id);

        Long projekteanzahl = projekteRepo.getProjekteAnzahl();

        try (XMLSlideShow generiertePowerPointPraesentation = new XMLSlideShow()) {


            //<editor-fold desc="Folien mit einzelnen Layout">
            //Layout der Folien
            XSLFSlideMaster defaultMaster = generiertePowerPointPraesentation.getSlideMasters().get(0);
            XSLFSlideLayout title_content = defaultMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);
            XSLFSlideLayout title = defaultMaster.getLayout(SlideLayout.TITLE);
            XSLFSlideLayout blank = defaultMaster.getLayout(SlideLayout.BLANK);
            XSLFSlide startFolie = generiertePowerPointPraesentation.createSlide(blank);
            //</editor-fold>

            /*
            //<editor-fold desc="LOGO">
            //Logo erstellen
            byte[] pictureData = IOUtils.toByteArray(new FileInputStream("/Users/anabikic/Documents/SCHULE/5BHITM/Diplomarbeit/apachePOI_try/src/main/img/logo_sparkasse.png"));
            XSLFPictureData pd = generiertePowerPointPraesentation.addPicture(pictureData, PictureData.PictureType.PNG);
            //</editor-fold>

            //<editor-fold desc="Erste Folie --> STARTBILD">
            byte[] startSeite = IOUtils.toByteArray(new FileInputStream("/Users/anabikic/Documents/SCHULE/5BHITM/Diplomarbeit/apachePOI_try/src/main/img/sparkassa.jpeg"));
            XSLFPictureData startSeite_pd = generiertePowerPointPraesentation.addPicture(startSeite, PictureData.PictureType.JPEG);
            XSLFPictureShape startSeite_pic = startFolie.createPicture(startSeite_pd);
            startSeite_pic.setAnchor(new Rectangle(0, 0, 720, 540));

            //----Logo anlegen
            XSLFPictureShape pic_startfolie = startFolie.createPicture(pd);
            pic_startfolie.setAnchor(new Rectangle(19, 0, 150, 50));

             */

            XSLFTextBox beschreibung = startFolie.createTextBox();
            beschreibung.setFillColor(new Color(195, 227, 248));
            beschreibung.setAnchor(new Rectangle(0, 0, 720, 50));

            XSLFTextBox nextPPKMeeting = startFolie.createTextBox();
            XSLFTextParagraph nextPPKMeeting_p = nextPPKMeeting.addNewTextParagraph();
            XSLFTextRun nextPPKMeeting_r = nextPPKMeeting_p.addNewTextRun();
            nextPPKMeeting_r.setText("Projekt Portfolio Komitee ( " + ppkRepo.getNextPPK() + " )");
            nextPPKMeeting_r.setBold(true);
            nextPPKMeeting_r.setFontColor(new Color(0, 82, 129));
            nextPPKMeeting_r.setFontSize(12.);
            nextPPKMeeting.setAnchor(new Rectangle(450, 10, 270, 50));
            //</editor-fold>

            //<editor-fold desc="Zweite Folie --> DETAILPRÄSENTATION">
            for (int b = 0; b < projekteanzahl; b++) {
                id++;

                XSLFSlide detailFolie = generiertePowerPointPraesentation.createSlide(title_content);

                /*
                XSLFPictureShape pic_detailFolie = detailFolie.createPicture(pd);
                pic_detailFolie.setAnchor(new Rectangle(540, 20, 150, 50));
                 */

                XSLFTextShape detailFolie_title = detailFolie.getPlaceholder(0);
                detailFolie_title.clearText();
                XSLFTextParagraph detailFolie_title_p = detailFolie_title.addNewTextParagraph();
                XSLFTextRun detailFolie_title_r = detailFolie_title_p.addNewTextRun();

                detailFolie_title_r.setText("" + allProjects.get(b).getTitel()); //Titel des Projekts
                detailFolie_title_r.setFontColor(new Color(0, 82, 129));
                detailFolie_title_r.setFontSize(30.0);
                detailFolie_title_r.setBold(true);
                detailFolie_title.setAnchor(new Rectangle(20, 20, 500, 50));

                //Body
                XSLFTextShape body_detailFolie = detailFolie.getPlaceholder(1);
                body_detailFolie.clearText();

                XSLFTable table_detailFolie = body_detailFolie.getSheet().createTable();
                table_detailFolie.setAnchor(new Rectangle(10, 160, 0, 0));

                int numColumns_detailFolie = 2;
                XSLFTableRow headerRow_detailFolie = table_detailFolie.addRow();
                headerRow_detailFolie.setHeight(20.0);

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
                detailFoli_tr_projektTitel.setHeight(50.0);

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

                cell_detailFolie_projektname_r.setText("Projektitel:");
                cell_detailFolie_projektname_r.setFontColor(new Color(0, 82, 129));
                cell_detailFolie_projektname_r.setFontSize(15.0);
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

                cell_detailFolie_projektname_eingegeben_r.setText("" + allProjects.get(b).getTitel());
                cell_detailFolie_projektname_eingegeben_r.setBold(true);
                cell_detailFolie_projektname_eingegeben_r.setFontColor(new Color(100, 100, 100));
                cell_detailFolie_projektname_eingegeben_r.setFontSize(15.0);

                //Reihe Projektmanager
                XSLFTableRow detailFolie_tr_projektStatus;
                detailFolie_tr_projektStatus = table_detailFolie.addRow();
                detailFolie_tr_projektStatus.setHeight(50.0);

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
                cell_detailFolie_projektStatus_r.setFontSize(15.0);
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

                cell_detailFolie_projektStatus_eingegeben_r.setText("" + projekmanager.getVorname() + " " + projekmanager.getNachname());
                cell_detailFolie_projektStatus_eingegeben_r.setFontColor(new Color(100, 100, 100));
                cell_detailFolie_projektStatus_eingegeben_r.setFontSize(15.0);

                //Reihe Status
                XSLFTableRow detailFolie_tr_status;
                detailFolie_tr_status = table_detailFolie.addRow();
                detailFolie_tr_status.setHeight(50.0);

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
                cell_detailFolie_status_r.setFontSize(15.0);
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

                cell_detailFolie_status_eingegeben_r.setText("" + allProjects.get(b).getStatus());
                cell_detailFolie_status_eingegeben_r.setFontColor(new Color(100, 100, 100));
                cell_detailFolie_status_eingegeben_r.setFontSize(15.0);


                //Reihe Startdatum
                XSLFTableRow detailFolie_tr_startDatum;
                detailFolie_tr_startDatum = table_detailFolie.addRow();
                detailFolie_tr_startDatum.setHeight(50.0);

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
                cell_detailFolie_startDatum_r.setFontSize(15.0);
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

                cell_detailFolie_startDatum_eingegeben_r.setText("" + allProjects.get(b).getStart_datum());
                cell_detailFolie_startDatum_eingegeben_r.setFontColor(new Color(100, 100, 100));
                cell_detailFolie_startDatum_eingegeben_r.setFontSize(15.0);

                //Reihe End-Datum
                XSLFTableRow detailFolie_tr_endDatum;
                detailFolie_tr_endDatum = table_detailFolie.addRow();
                detailFolie_tr_endDatum.setHeight(50.0);

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
                cell_detailFolie_endDatum_r.setFontSize(15.0);
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

                cell_detailFolie_endDatum_eingegeben_r.setText("" + allProjects.get(b).getEnd_datum());
                cell_detailFolie_endDatum_eingegeben_r.setFontColor(new Color(100, 100, 100));
                cell_detailFolie_endDatum_eingegeben_r.setFontSize(15.0);

                //----------------------------------------------------------------------------------------------------------FOOTER
                XSLFTextBox detailffolie_footer = detailFolie.createTextBox();
                XSLFTextParagraph detailffolie_footer_p = detailffolie_footer.addNewTextParagraph();
                XSLFTextRun detailffolie_footer_r = detailffolie_footer_p.addNewTextRun();
                detailffolie_footer_r.setText("Projekt Portfolio Komitee ( " + ppkRepo.getNextPPK() + " )");
                detailffolie_footer_r.setFontColor(new Color(0, 82, 129));
                detailffolie_footer_r.setFontSize(12.);
                detailffolie_footer.setAnchor(new Rectangle(440, 500, 270, 50));
            }
            //</editor-fold>

            //<editor-fold desc="Dritte Folie --> Meilensteinliste">
            for (int b = 0; b < allProjects.size(); b++) {

                XSLFSlide meilensteinFolie = generiertePowerPointPraesentation.createSlide(title_content);
                /*
                XSLFPictureShape pic_meilensteinFolie = meilensteinFolie.createPicture(pd);
                pic_meilensteinFolie.setAnchor(new Rectangle(540, 20, 150, 50));

                 */

                XSLFTextShape meilensteinFolie_title = meilensteinFolie.getPlaceholder(0);
                meilensteinFolie_title.clearText();
                XSLFTextParagraph meilensteinFolie_title_p = meilensteinFolie_title.addNewTextParagraph();
                XSLFTextRun meilensteinFolie_title_r = meilensteinFolie_title_p.addNewTextRun();

                meilensteinFolie_title_r.setText("" + allProjects.get(b).getTitel()); //Titel des Projekts
                meilensteinFolie_title_r.setFontColor(new Color(0, 82, 129));
                meilensteinFolie_title_r.setFontSize(30.0);
                meilensteinFolie_title_r.setBold(true);
                meilensteinFolie_title.setAnchor(new Rectangle(20, 20, 500, 50));

                XSLFTextShape body_meilensteinFolie = meilensteinFolie.getPlaceholder(1);
                body_meilensteinFolie.clearText();


                //Tabelle anlegen
                XSLFTable table_meilensteine = body_meilensteinFolie.getSheet().createTable();
                table_meilensteine.setAnchor(new Rectangle(10, 200, 0, 0));


                XSLFTableRow headerRow_meilensteine = table_meilensteine.addRow();

                XSLFTextBox meilenstein = meilensteinFolie.createTextBox();
                XSLFTextParagraph meilenstein_p = meilenstein.addNewTextParagraph();
                XSLFTextRun meilenstein_r = meilenstein_p.addNewTextRun();
                meilenstein_r.setText("Meilensteinliste:");
                meilenstein_r.setFontSize(16.0);
                meilenstein_r.setBold(true);
                meilenstein_r.setFontColor(new Color(0, 82, 129));
                meilenstein.setAnchor(new Rectangle(10, 140, 140, 100));


                for (int i = 0; i < 5; i++) {
                    XSLFTableCell th_detailFolie_meilensteine = headerRow_meilensteine.addCell();
                    XSLFTextParagraph p_th_detailFolie_meilensteine = th_detailFolie_meilensteine.addNewTextParagraph();
                    p_th_detailFolie_meilensteine.setTextAlign(TextParagraph.TextAlign.LEFT);
                    XSLFTextRun r_th_detailFolie_meilensteine = p_th_detailFolie_meilensteine.addNewTextRun();
                    r_th_detailFolie_meilensteine.setBold(true);

                    if (i == 0) {

                        r_th_detailFolie_meilensteine.setText("Titel");
                        table_meilensteine.setColumnWidth(i, 144);
                        table_meilensteine.setRowHeight(i, 20.0);
                        th_detailFolie_meilensteine.setFillColor(new Color(0, 82, 129));

                        th_detailFolie_meilensteine.setVerticalAlignment(VerticalAlignment.MIDDLE);
                        th_detailFolie_meilensteine.setBorderColor(TableCell.BorderEdge.bottom, new Color(0, 82, 129));
                        th_detailFolie_meilensteine.setBorderWidth(TableCell.BorderEdge.bottom, 1);

                        r_th_detailFolie_meilensteine.setFontColor(new Color(255, 255, 255));
                        r_th_detailFolie_meilensteine.setFontSize(10.0);

                    }
                    if (i == 1) {

                        r_th_detailFolie_meilensteine.setText("Beschreibung");
                        table_meilensteine.setColumnWidth(i, 170);
                        th_detailFolie_meilensteine.setFillColor(new Color(0, 82, 129));

                        th_detailFolie_meilensteine.setVerticalAlignment(VerticalAlignment.MIDDLE);
                        th_detailFolie_meilensteine.setBorderColor(TableCell.BorderEdge.bottom, new Color(0, 82, 129));
                        th_detailFolie_meilensteine.setBorderWidth(TableCell.BorderEdge.bottom, 1);

                        r_th_detailFolie_meilensteine.setFontColor(new Color(255, 255, 255));
                        r_th_detailFolie_meilensteine.setFontSize(10.0);

                    }
                    if (i == 2) {

                        r_th_detailFolie_meilensteine.setText("Status");
                        table_meilensteine.setColumnWidth(i, 144);
                        th_detailFolie_meilensteine.setFillColor(new Color(0, 82, 129));

                        th_detailFolie_meilensteine.setVerticalAlignment(VerticalAlignment.MIDDLE);
                        th_detailFolie_meilensteine.setBorderColor(TableCell.BorderEdge.bottom, new Color(0, 82, 129));
                        th_detailFolie_meilensteine.setBorderWidth(TableCell.BorderEdge.bottom, 1);

                        r_th_detailFolie_meilensteine.setFontColor(new Color(255, 255, 255));
                        r_th_detailFolie_meilensteine.setFontSize(10.0);

                    }
                    if (i == 3) {

                        r_th_detailFolie_meilensteine.setText("Start-Datum");
                        table_meilensteine.setColumnWidth(i, 120);
                        th_detailFolie_meilensteine.setFillColor(new Color(0, 82, 129));

                        th_detailFolie_meilensteine.setVerticalAlignment(VerticalAlignment.MIDDLE);
                        th_detailFolie_meilensteine.setBorderColor(TableCell.BorderEdge.bottom, new Color(0, 82, 129));
                        th_detailFolie_meilensteine.setBorderWidth(TableCell.BorderEdge.bottom, 1);

                        r_th_detailFolie_meilensteine.setFontColor(new Color(255, 255, 255));
                        r_th_detailFolie_meilensteine.setFontSize(10.0);

                    }
                    if (i == 4) {

                        r_th_detailFolie_meilensteine.setText("End-Datum");
                        table_meilensteine.setColumnWidth(i, 120);
                        th_detailFolie_meilensteine.setFillColor(new Color(0, 82, 129));

                        th_detailFolie_meilensteine.setVerticalAlignment(VerticalAlignment.MIDDLE);
                        th_detailFolie_meilensteine.setBorderColor(TableCell.BorderEdge.bottom, new Color(0, 82, 129));
                        th_detailFolie_meilensteine.setBorderWidth(TableCell.BorderEdge.bottom, 1);

                        r_th_detailFolie_meilensteine.setFontColor(new Color(255, 255, 255));
                        r_th_detailFolie_meilensteine.setFontSize(10.0);

                    }
                }


                List<Meilensteine> meileinensteineProProjekt = new ArrayList<>();

                for (int i = 0; i < alleMeilensteine.size(); i++) {
                    Meilensteine aktuellerMeilenstein = alleMeilensteine.get(i);
                    if (aktuellerMeilenstein.getProjekt_id().getProjekt_id().equals(allProjects.get(b).getProjekt_id())) {
                        meileinensteineProProjekt.add(aktuellerMeilenstein);
                    }
                }

                for (int i = 0; i < meileinensteineProProjekt.size(); i++) {
                    Meilensteine aktuellerMeilenstein = alleMeilensteine.get(i);

                    //Erstes Projekt
                    XSLFTableRow detailFolie_tr_meilensteinTitle;
                    detailFolie_tr_meilensteinTitle = table_meilensteine.addRow();
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

                    cell_detailFolie_meielnsteinTitel_r.setText("" + aktuellerMeilenstein.getTitel());
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

                    cell_detailFolie_meielnsteinBeschreibung_r.setText("" + aktuellerMeilenstein.getBeschreibung());
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

                    cell_detailFolie_meielnsteinStatus_r.setText("" + aktuellerMeilenstein.getStatus());
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

                    cell_detailFolie_meielnsteinStartDatum_r.setText("" + aktuellerMeilenstein.getStart_datum());
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

                    cell_detailFolie_meielnsteinEndDatum_r.setText("" + aktuellerMeilenstein.getEnd_datum());
                    cell_detailFolie_meielnsteinEndDatum_r.setFontColor(new Color(100, 100, 100));
                    cell_detailFolie_meielnsteinEndDatum_r.setFontSize(10.0);

                }
                XSLFTextBox meilensteinFolie_footer = meilensteinFolie.createTextBox();
                XSLFTextParagraph meilensteinFolie_footer_p = meilensteinFolie_footer.addNewTextParagraph();
                XSLFTextRun meilensteinFolie_footer_r = meilensteinFolie_footer_p.addNewTextRun();
                meilensteinFolie_footer_r.setText("Projekt Portfolio Komitee ( " + ppkRepo.getNextPPK() + " )");
                meilensteinFolie_footer_r.setFontColor(new Color(0, 82, 129));
                meilensteinFolie_footer_r.setFontSize(12.);
                meilensteinFolie_footer.setAnchor(new Rectangle(440, 500, 270, 50));
            }
            //</editor-fold>

            //<editor-fold desc="Folie --> Arbeitszeiten">



            for(int i = 0; i < allProjects.size(); i++){
                List<Arbeitszeiten> arbeitszeitenProProjekt = new ArrayList<>();
                for(int j = 0; j < allArbeitszeiten.size(); j++){
                    if(allProjects.get(i).getProjekt_id().equals(allArbeitszeiten.get(j).getEinsaetze_id().getEinsaetze_id().getProjekte_id().getProjekt_id())){
                        arbeitszeitenProProjekt.add(allArbeitszeiten.get(j));
                    }
                }
                if(!arbeitszeitenProProjekt.isEmpty()){

                    XSLFSlide arbeitszeit = generiertePowerPointPraesentation.createSlide(title_content);


                    XSLFTextShape meilensteinFolie_title = arbeitszeit.getPlaceholder(0);
                    meilensteinFolie_title.clearText();
                    XSLFTextParagraph meilensteinFolie_title_p = meilensteinFolie_title.addNewTextParagraph();
                    XSLFTextRun meilensteinFolie_title_r = meilensteinFolie_title_p.addNewTextRun();

                    meilensteinFolie_title_r.setText("" + allProjects.get(i).getTitel()); //Titel des Projekts
                    meilensteinFolie_title_r.setFontColor(new Color(0, 82, 129));
                    meilensteinFolie_title_r.setFontSize(30.0);
                    meilensteinFolie_title_r.setBold(true);
                    meilensteinFolie_title.setAnchor(new Rectangle(20, 20, 500, 50));

                    XSLFTextShape body_meilensteinFolie = arbeitszeit.getPlaceholder(1);
                    body_meilensteinFolie.clearText();


                    //Tabelle anlegen
                    XSLFTable table_meilensteine = body_meilensteinFolie.getSheet().createTable();
                    table_meilensteine.setAnchor(new Rectangle(50, 200, 0, 0));


                    XSLFTableRow headerRow_meilensteine = table_meilensteine.addRow();

                    XSLFTextBox meilenstein = arbeitszeit.createTextBox();
                    XSLFTextParagraph meilenstein_p = meilenstein.addNewTextParagraph();
                    XSLFTextRun meilenstein_r = meilenstein_p.addNewTextRun();
                    meilenstein_r.setText("Anzahl der Stunden pro Mitarbeiter für das Projekt:");
                    meilenstein_r.setFontSize(16.0);
                    meilenstein_r.setBold(true);
                    meilenstein_r.setFontColor(new Color(0, 82, 129));
                    meilenstein.setAnchor(new Rectangle(30,90,200,100));


                    for (int a = 0; a < 3; a++) {
                        XSLFTableCell th_detailFolie_meilensteine = headerRow_meilensteine.addCell();
                        XSLFTextParagraph p_th_detailFolie_meilensteine = th_detailFolie_meilensteine.addNewTextParagraph();
                        p_th_detailFolie_meilensteine.setTextAlign(TextParagraph.TextAlign.LEFT);
                        XSLFTextRun r_th_detailFolie_meilensteine = p_th_detailFolie_meilensteine.addNewTextRun();
                        r_th_detailFolie_meilensteine.setBold(true);

                        if (a == 0) {

                            r_th_detailFolie_meilensteine.setText("Mitarbeiter: ");
                            table_meilensteine.setColumnWidth(a, 144);
                            table_meilensteine.setRowHeight(a, 20.0);
                            th_detailFolie_meilensteine.setFillColor(new Color(0, 82, 129));

                            th_detailFolie_meilensteine.setVerticalAlignment(VerticalAlignment.MIDDLE);
                            th_detailFolie_meilensteine.setBorderColor(TableCell.BorderEdge.bottom, new Color(0, 82, 129));
                            th_detailFolie_meilensteine.setBorderWidth(TableCell.BorderEdge.bottom, 1);

                            r_th_detailFolie_meilensteine.setFontColor(new Color(255, 255, 255));
                            r_th_detailFolie_meilensteine.setFontSize(10.0);

                        }
                        if (a == 1) {

                            r_th_detailFolie_meilensteine.setText("Rolle: ");
                            table_meilensteine.setColumnWidth(a, 170);
                            th_detailFolie_meilensteine.setFillColor(new Color(0, 82, 129));

                            th_detailFolie_meilensteine.setVerticalAlignment(VerticalAlignment.MIDDLE);
                            th_detailFolie_meilensteine.setBorderColor(TableCell.BorderEdge.bottom, new Color(0, 82, 129));
                            th_detailFolie_meilensteine.setBorderWidth(TableCell.BorderEdge.bottom, 1);

                            r_th_detailFolie_meilensteine.setFontColor(new Color(255, 255, 255));
                            r_th_detailFolie_meilensteine.setFontSize(10.0);

                        }
                        if(a == 2){
                            r_th_detailFolie_meilensteine.setText("Stunden: ");
                            table_meilensteine.setColumnWidth(a, 170);
                            th_detailFolie_meilensteine.setFillColor(new Color(0, 82, 129));

                            th_detailFolie_meilensteine.setVerticalAlignment(VerticalAlignment.MIDDLE);
                            th_detailFolie_meilensteine.setBorderColor(TableCell.BorderEdge.bottom, new Color(0, 82, 129));
                            th_detailFolie_meilensteine.setBorderWidth(TableCell.BorderEdge.bottom, 1);

                            r_th_detailFolie_meilensteine.setFontColor(new Color(255, 255, 255));
                            r_th_detailFolie_meilensteine.setFontSize(10.0);
                        }
                    }

                    for(int c = 0; c < arbeitszeitenProProjekt.size(); c++){

                        //Erstes Projekt
                        XSLFTableRow detailFolie_tr_meilensteinTitle;
                        detailFolie_tr_meilensteinTitle = table_meilensteine.addRow();
                        detailFolie_tr_meilensteinTitle.setHeight(25.0);

                        //----------------------------------------------Mitarbeiter
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

                        cell_detailFolie_meielnsteinTitel_r.setText(""+ arbeitszeitenProProjekt.get(c).getEinsaetze_id().getEinsaetze_id().getPersonen_id().getVorname() +" " + arbeitszeitenProProjekt.get(c).getEinsaetze_id().getEinsaetze_id().getPersonen_id().getNachname());
                        cell_detailFolie_meielnsteinTitel_r.setFontColor(new Color(0, 82, 129));
                        cell_detailFolie_meielnsteinTitel_r.setFontSize(10.0);

                        //----------------------------------------------Rolle
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

                        cell_detailFolie_meielnsteinBeschreibung_r.setText(""+arbeitszeitenProProjekt.get(c).getEinsaetze_id().getEinsaetze_id().getRollen_id().getName());
                        cell_detailFolie_meielnsteinBeschreibung_r.setFontColor(new Color(100, 100, 100));
                        cell_detailFolie_meielnsteinBeschreibung_r.setFontSize(10.0);

                        //----------------------------------------------Stunden
                        XSLFTableCell cell_detailFolie_stunden = detailFolie_tr_meilensteinTitle.addCell();
                        XSLFTextParagraph cell_detailFolie_stunden_p = cell_detailFolie_stunden.addNewTextParagraph();
                        XSLFTextRun cell_detailFolie_stunden_r = cell_detailFolie_stunden_p.addNewTextRun();


                        cell_detailFolie_stunden.setFillColor(new Color(255, 255, 255));
                        cell_detailFolie_stunden.setVerticalAlignment(VerticalAlignment.MIDDLE);


                        cell_detailFolie_stunden.setBorderColor(TableCell.BorderEdge.top, Color.white);
                        cell_detailFolie_stunden.setBorderColor(TableCell.BorderEdge.right, Color.white);
                        cell_detailFolie_stunden.setBorderColor(TableCell.BorderEdge.left, Color.white);
                        cell_detailFolie_stunden.setBorderColor(TableCell.BorderEdge.bottom, new Color(211, 211, 211));
                        cell_detailFolie_stunden.setBorderWidth(TableCell.BorderEdge.bottom, 1);

                        cell_detailFolie_stunden_r.setText(""+arbeitszeitenProProjekt.get(c).getArbeitszeit());
                        cell_detailFolie_stunden_r.setFontColor(new Color(100, 100, 100));
                        cell_detailFolie_stunden_r.setFontSize(10.0);

                    }



                    XSLFTextBox arbeitszeit_footer = arbeitszeit.createTextBox();
                    XSLFTextParagraph arbeitszeit_footer_p = arbeitszeit_footer.addNewTextParagraph();
                    XSLFTextRun arbeitszeit_footer_r = arbeitszeit_footer_p.addNewTextRun();
                    arbeitszeit_footer_r.setText("Projekt Portfolio Komitee ( " + ppkRepo.getNextPPK() + " )");
                    arbeitszeit_footer_r.setFontColor(new Color(0, 82, 129));
                    arbeitszeit_footer_r.setFontSize(12.);
                    arbeitszeit_footer.setAnchor(new Rectangle(440, 500, 270, 50));

                }

            }







                /*
                XSLFPictureShape pic_meilensteinFolie = arbeitszeit.createPicture(pd);
                pic_meilensteinFolie.setAnchor(new Rectangle(540, 20, 150, 50));

                 */



            //</editor-fold>

            //<editor-fold desc="Letzte Folie --> ENDBILD">
            /*
            XSLFSlide endFolie = generiertePowerPointPraesentation.createSlide(blank);
            byte[] endSeite = IOUtils.toByteArray(new FileInputStream("/Users/anabikic/Documents/SCHULE/5BHITM/Diplomarbeit/apachePOI_try/src/main/img/glaubandich.png"));
            XSLFPictureData endSeite_pd = generiertePowerPointPraesentation.addPicture(endSeite, PictureData.PictureType.PNG);
            XSLFPictureShape endSeite_pic = endFolie.createPicture(endSeite_pd);
            endSeite_pic.setAnchor(new Rectangle(0,0,720,540));
            //</editor-fold>

             */

            //OUTPUT
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            generiertePowerPointPraesentation.write(b);
            return new ByteArrayInputStream(b.toByteArray());

        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
