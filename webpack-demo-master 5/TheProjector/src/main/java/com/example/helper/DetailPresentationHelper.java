package com.example.helper;

import com.example.api.PPKRessource;
import com.example.model.PersonenAufwandDTO;
import com.example.workloads.Arbeitszeiten.Arbeitszeiten;
import com.example.workloads.Arbeitszeiten.ArbeitszeitenRepo;
import com.example.workloads.Gaeste.Gaeste;
import com.example.workloads.Gaeste.GaesteRepo;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DetailPresentationHelper {

    public static ByteArrayInputStream generateDetailPresentation()
            throws FileNotFoundException, IOException, InvalidFormatException {

        //<editor-fold desc="REPOS">
        //ProjekteRepo
        final ProjekteRepo projekteRepo = new ProjekteRepo();
        //PPKRepo
        final PPKRepo ppkRepo = new PPKRepo();
        //PersonenRepo
        final PersonenRepo personenRepo = new PersonenRepo();
        //MeilensteinRepo
        final MeilensteineRepo meilensteineRepo = new MeilensteineRepo();
        //ArbeitszeitenRepo
        final ArbeitszeitenRepo arbeitszeitenRepo = new ArbeitszeitenRepo();
        //GästeRepo
        final GaesteRepo gaesteRepo = new GaesteRepo();
        //</editor-fold>

        //<editor-fold desc="Repoaufruf">
        //Liste allerProjekte
        List<Projekte> allProjects = new ArrayList<>();
        allProjects = projekteRepo.listAll();

        //Liste aller MEilensteine
        List<Meilensteine> alleMeilensteine = new ArrayList<>();
        alleMeilensteine = meilensteineRepo.listAll();

        //Liste aller Arbeitszeiten
        List<Arbeitszeiten> allArbeitszeiten = new ArrayList<>();
        allArbeitszeiten = arbeitszeitenRepo.listAll();

        //Liste aller Gäste
        List<Gaeste> alleGaeste = new ArrayList<>();
        alleGaeste = gaesteRepo.listAll();

        //Projekmanager
        Long id = 1L;
        Personen projekmanager = projekteRepo.getProjektmanager(id);

        //Projekteanzahl
        Long projekteanzahl = projekteRepo.getProjekteAnzahl();

        //Datumsformat änder
        LocalDate nextPPK = ppkRepo.getNextPPK();
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date_PPK = nextPPK.format(formatters);
        LocalDate parsedPPK = LocalDate.parse(date_PPK, formatters);
        //</editor-fold>

        try (XMLSlideShow generiertePowerPointPraesentation = new XMLSlideShow()) {

            //<editor-fold desc="LAYOUT">
            //Layout der Folien
            XSLFSlideMaster defaultMaster = generiertePowerPointPraesentation.getSlideMasters().get(0);
            XSLFSlideLayout title_content = defaultMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);
            XSLFSlideLayout title = defaultMaster.getLayout(SlideLayout.TITLE);
            XSLFSlideLayout blank = defaultMaster.getLayout(SlideLayout.BLANK);
            //</editor-fold>

            //<editor-fold desc="LOGO">
            //Logo erstellen
            byte[] pictureData = IOUtils.toByteArray(new FileInputStream("src/main/resources/images/logo_sparkasse.png"));
            XSLFPictureData pd = generiertePowerPointPraesentation.addPicture(pictureData, PictureData.PictureType.PNG);
            //</editor-fold>

            //<editor-fold desc="EINLEITUNGSFOLIE ">
            //Folie generieren
            XSLFSlide einleitungsFolie = generiertePowerPointPraesentation.createSlide(blank);

            //Hintergrundbild
            byte[] hintergrund = IOUtils.toByteArray(new FileInputStream("src/main/resources/images/startseite.png"));
            XSLFPictureData hintergrund_pd = generiertePowerPointPraesentation.addPicture(hintergrund, PictureData.PictureType.PNG);
            XSLFPictureShape hintergrund_pic = einleitungsFolie.createPicture(hintergrund_pd);
            hintergrund_pic.setAnchor(new Rectangle(0,0,720,540));

            XSLFTextBox balken = einleitungsFolie.createTextBox();
            balken.setFillColor(new Color(195,227,248));
            balken.setAnchor(new Rectangle(0,460,720,80));

            //Logo einbinden
            XSLFPictureShape logo = einleitungsFolie.createPicture(pd);
            logo.setAnchor(new Rectangle(30, 480, 150, 35));

            XSLFTextBox box = einleitungsFolie.createTextBox();
            box.setFillColor(new Color(195,227,248));
            box.setAnchor(new Rectangle(330,120,360,300));

            XSLFTextBox gremium = einleitungsFolie.createTextBox();
            XSLFTextParagraph gremium_p = gremium.addNewTextParagraph();
            XSLFTextRun gremium_r = gremium_p.addNewTextRun();
            gremium_r.setText("Gremium PPK");
            gremium_r.setBold(true);
            gremium_r.setFontColor(Color.white);
            gremium_r.setFontSize(40.0);
            gremium.setAnchor(new Rectangle(30, 155, 320, 50));

            XSLFTextBox ppk_text = einleitungsFolie.createTextBox();
            XSLFTextParagraph ppk_text_p = ppk_text.addNewTextParagraph();
            XSLFTextRun ppk_text_r = ppk_text_p.addNewTextRun();
            ppk_text_r.setText("Projekt Portfolio Komitee \n" +nextPPK.format(formatters));
            ppk_text_r.setFontColor(Color.white);
            ppk_text_r.setFontSize(23.0);
            ppk_text.setAnchor(new Rectangle(30, 225, 320, 50));

            XSLFTextBox nebentext1 = einleitungsFolie.createTextBox();
            XSLFTextParagraph nebentext1_p = nebentext1.addNewTextParagraph();
            XSLFTextRun nebentext1_r = nebentext1_p.addNewTextRun();
            nebentext1_r.setText("OE Organisation");
            nebentext1_r.setFontColor(Color.white);
            nebentext1_r.setFontSize(12.0);
            nebentext1.setAnchor(new Rectangle(30, 305, 320, 50));

            XSLFTextBox nebentext2 = einleitungsFolie.createTextBox();
            XSLFTextParagraph nebentext2_p = nebentext2.addNewTextParagraph();
            XSLFTextRun nebentext2_r = nebentext2_p.addNewTextRun();
            nebentext2_r.setText("Bereich Strategisches Risikomanagement & ORG IT");
            nebentext2_r.setFontColor(Color.white);
            nebentext2_r.setFontSize(12.0);
            nebentext2.setAnchor(new Rectangle(30, 320, 320, 50));

            XSLFTextBox zeitplan = einleitungsFolie.createTextBox();
            XSLFTextParagraph zeitplan_p = zeitplan.addNewTextParagraph();
            XSLFTextRun zeitplan_r = zeitplan_p.addNewTextRun();
            zeitplan_r.setText("Zeitplan:");
            zeitplan_r.setFontColor(new Color(0, 82, 129));
            zeitplan_r.setFontSize(18.0);
            zeitplan_r.setBold(true);
            zeitplan.setAnchor(new Rectangle(360, 140, 320, 50));

            XSLFTextBox zeitplan_1 = einleitungsFolie.createTextBox();
            XSLFTextParagraph zeitplan_1_p = zeitplan_1.addNewTextParagraph();
            XSLFTextRun zeitplan_1_r = zeitplan_1_p.addNewTextRun();
            zeitplan_1_r.setText("14:00 – 14:45 Projekt Portfolio");
            zeitplan_1_r.setFontColor(new Color(0, 82, 129));
            zeitplan_1_r.setFontSize(16.0);
            zeitplan_1.setAnchor(new Rectangle(360, 180, 320, 50));

            XSLFTextBox zeitplan_2 = einleitungsFolie.createTextBox();
            XSLFTextParagraph zeitplan_2_p = zeitplan_2.addNewTextParagraph();
            XSLFTextRun zeitplan_2_r = zeitplan_2_p.addNewTextRun();
            zeitplan_2_r.setText("14:45 – 15:30 Projekt Steuerungskomitees");
            zeitplan_2_r.setFontColor(new Color(0, 82, 129));
            zeitplan_2_r.setFontSize(16.0);
            zeitplan_2.setAnchor(new Rectangle(360, 200, 320, 50));

            XSLFTextBox zeitplan_3 = einleitungsFolie.createTextBox();
            XSLFTextParagraph zeitplan_3_p = zeitplan_3.addNewTextParagraph();
            XSLFTextRun zeitplan_3_r = zeitplan_3_p.addNewTextRun();
            zeitplan_3_r.setText("15:30 – 16:00 Allfälliges");
            zeitplan_3_r.setFontColor(new Color(0, 82, 129));
            zeitplan_3_r.setFontSize(16.0);
            zeitplan_3.setAnchor(new Rectangle(360, 220, 320, 50));

            XSLFTextBox gaeste = einleitungsFolie.createTextBox();
            XSLFTextParagraph gaeste_p = gaeste.addNewTextParagraph();
            XSLFTextRun gaeste_r = gaeste_p.addNewTextRun();
            gaeste_r.setText("Gäste:");
            gaeste_r.setFontColor(new Color(0, 82, 129));
            gaeste_r.setFontSize(18.0);
            gaeste_r.setBold(true);
            gaeste.setAnchor(new Rectangle(360, 260, 320, 50));

            XSLFTextBox gaeste_beitext = einleitungsFolie.createTextBox();
            XSLFTextParagraph gaeste_beitext_p = gaeste_beitext.addNewTextParagraph();
            XSLFTextRun gaeste_beitext_r = gaeste_beitext_p.addNewTextRun();
            gaeste_beitext_r.setText(" (werden zum jeweiligen Thema via MS Teams \n zugeschaltet)");
            gaeste_beitext_r.setFontColor(new Color(0, 82, 129));
            gaeste_beitext_r.setFontSize(11.0);
            gaeste_beitext.setAnchor(new Rectangle(420, 264, 320, 50));

            for(int i = 0; i < alleGaeste.size(); i++){
                XSLFTextBox gaeste1 = einleitungsFolie.createTextBox();
                XSLFTextParagraph gaeste1_p = gaeste1.addNewTextParagraph();
                XSLFTextRun gaeste1_r = gaeste1_p.addNewTextRun();
                gaeste1_r.setText(""+ alleGaeste.get(i).getName());
                gaeste1_r.setFontColor(new Color(0, 82, 129));
                gaeste1_r.setFontSize(16.0);
                gaeste1.setAnchor(new Rectangle(360, 300+20*i, 320, 50));
            }
            //</editor-fold>

            //<editor-fold desc="STARTBILD">
            //Folie generiern
            XSLFSlide ersteFolie = generiertePowerPointPraesentation.createSlide(blank);

            //Hintergrundbild
            byte[] ersteSeite = IOUtils.toByteArray(new FileInputStream("src/main/resources/images/firstPicture.png"));
            XSLFPictureData ersteSeite_pd = generiertePowerPointPraesentation.addPicture(ersteSeite, PictureData.PictureType.PNG);
            XSLFPictureShape ersteSeite_pic = ersteFolie.createPicture(ersteSeite_pd);
            ersteSeite_pic.setAnchor(new Rectangle(0,0,720,540));
            //</editor-fold>

            //<editor-fold desc="STARTFOLIE ">
            //Folie generiern
            XSLFSlide startFolie = generiertePowerPointPraesentation.createSlide(blank);

            //Hintergrundbild
            byte[] startSeite = IOUtils.toByteArray(new FileInputStream("src/main/resources/images/laufende_projekte.png"));
            XSLFPictureData startSeite_pd = generiertePowerPointPraesentation.addPicture(startSeite, PictureData.PictureType.PNG);
            XSLFPictureShape startSeite_pic = startFolie.createPicture(startSeite_pd);
            startSeite_pic.setAnchor(new Rectangle(0,0,750,540));
            //</editor-fold>

            //<editor-fold desc="DETAILINFOS">
            //Durchlaufen aller Projekte
            for (int b = 0; b < projekteanzahl; b++) {
                id++;

                //Folie generieren
                XSLFSlide detailFolie = generiertePowerPointPraesentation.createSlide(title_content);

                //Logo einbinden
                XSLFPictureShape pic_detailFolie = detailFolie.createPicture(pd);
                pic_detailFolie.setAnchor(new Rectangle(560, 32, 130, 20));

                //Titel einfügen
                XSLFTextShape detailFolie_title = detailFolie.getPlaceholder(0);
                detailFolie_title.clearText();
                XSLFTextParagraph detailFolie_title_p = detailFolie_title.addNewTextParagraph();
                XSLFTextRun detailFolie_title_r = detailFolie_title_p.addNewTextRun();

                detailFolie_title_r.setText("" + allProjects.get(b).getTitel()); //Titel des Projekts
                detailFolie_title_r.setFontColor(new Color(0, 82, 129));
                detailFolie_title_r.setFontSize(30.0);
                detailFolie_title_r.setBold(true);
                detailFolie_title.setAnchor(new Rectangle(20, 20, 500, 50));

                //Tabelle anlegen
                XSLFTextShape body_detailFolie = detailFolie.getPlaceholder(1);
                body_detailFolie.clearText();

                XSLFTable table_detailFolie = body_detailFolie.getSheet().createTable();
                table_detailFolie.setAnchor(new Rectangle(10, 160, 0, 0));

                int numColumns_detailFolie = 2;
                XSLFTableRow headerRow_detailFolie = table_detailFolie.addRow();
                headerRow_detailFolie.setHeight(20.0);

                //Header für die Tabelle
                for (int i = 0; i < numColumns_detailFolie; i++) {

                    XSLFTableCell th_detailFolie = headerRow_detailFolie.addCell();
                    XSLFTextParagraph p_th_detailFolie = th_detailFolie.addNewTextParagraph();
                    p_th_detailFolie.setTextAlign(TextParagraph.TextAlign.CENTER);
                    XSLFTextRun r_th_detailFolie = p_th_detailFolie.addNewTextRun();

                    if (i == 0) {
                        r_th_detailFolie.setText("");
                        r_th_detailFolie.setFontSize(2.0);
                        table_detailFolie.setColumnWidth(i, 350);
                        th_detailFolie.setFillColor(new Color(0, 82, 129));
                        r_th_detailFolie.setFontColor(new Color(0, 82, 129));
                    }
                    if (i == 1) {
                        r_th_detailFolie.setText("");
                        r_th_detailFolie.setFontSize(2.0);
                        table_detailFolie.setColumnWidth(i, 350);
                        th_detailFolie.setFillColor(new Color(0, 82, 129));
                        r_th_detailFolie.setFontColor(new Color(0, 82, 129));
                    }
                }

                //Row Projektname
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

                //----------------------------------------------Projektname DB
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

                //Row Projektmanager
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

                //----------------------------------------------Projektmanager DB
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

                //Row Status
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

                //----------------------------------------------Status DB
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

                //Status durch Icon ersetzen
                if(allProjects.get(b).getStatus() == 1){

                    cell_detailFolie_status_eingegeben_r.setText("\uD83D\uDFE2 planmäßig");
                    cell_detailFolie_status_eingegeben_r.setFontColor(new Color(100, 100, 100));
                    cell_detailFolie_status_eingegeben_r.setFontSize(15.0);

                }else if(allProjects.get(b).getStatus() == 2){

                    cell_detailFolie_status_eingegeben_r.setText("\uD83D\uDFE0 im Verzug");
                    cell_detailFolie_status_eingegeben_r.setFontColor(new Color(100, 100, 100));
                    cell_detailFolie_status_eingegeben_r.setFontSize(15.0);

                }else if(allProjects.get(b).getStatus() == 3){

                    cell_detailFolie_status_eingegeben_r.setText("\uD83D\uDD34 verspätet");
                    cell_detailFolie_status_eingegeben_r.setFontColor(new Color(100, 100, 100));
                    cell_detailFolie_status_eingegeben_r.setFontSize(15.0);

                }


                //Row Startdatum
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

                //----------------------------------------------Startdatum DB
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

                //Startdatum Format ändern
                LocalDate startDatum = allProjects.get(b).getStart_datum();

                cell_detailFolie_startDatum_eingegeben_r.setText("" + startDatum.format(formatters));
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

                //----------------------------------------------End-Datum DB
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

                //Enddatum Format ändern
                LocalDate enddatum = allProjects.get(b).getEnd_datum();

                cell_detailFolie_endDatum_eingegeben_r.setText("" + enddatum.format(formatters));
                cell_detailFolie_endDatum_eingegeben_r.setFontColor(new Color(100, 100, 100));
                cell_detailFolie_endDatum_eingegeben_r.setFontSize(15.0);

                //----------------------------------------------------------------------------------------------------------FOOTER
                XSLFTextBox detailffolie_footer = detailFolie.createTextBox();
                XSLFTextParagraph detailffolie_footer_p = detailffolie_footer.addNewTextParagraph();
                XSLFTextRun detailffolie_footer_r = detailffolie_footer_p.addNewTextRun();
                detailffolie_footer_r.setText("Projekt Portfolio Komitee am " + nextPPK.format(formatters));
                detailffolie_footer_r.setFontColor(new Color(0, 82, 129));
                detailffolie_footer_r.setFontSize(12.);
                detailffolie_footer.setAnchor(new Rectangle(440, 500, 270, 50));
            }
            //</editor-fold>

            //<editor-fold desc="MEILENSTEINLISTE">
            //Durchlaufen aller Projekte
            for (int b = 0; b < allProjects.size(); b++) {

                //Folie generieren
                XSLFSlide meilensteinFolie = generiertePowerPointPraesentation.createSlide(title_content);

                //Logo einfügen
                XSLFPictureShape pic_meilensteinFolie = meilensteinFolie.createPicture(pd);
                pic_meilensteinFolie.setAnchor(new Rectangle(560, 32, 130, 20));

                //Titel einfügen
                XSLFTextShape meilensteinFolie_title = meilensteinFolie.getPlaceholder(0);
                meilensteinFolie_title.clearText();
                XSLFTextParagraph meilensteinFolie_title_p = meilensteinFolie_title.addNewTextParagraph();
                XSLFTextRun meilensteinFolie_title_r = meilensteinFolie_title_p.addNewTextRun();

                meilensteinFolie_title_r.setText("" + allProjects.get(b).getTitel()); //Titel des Projekts
                meilensteinFolie_title_r.setFontColor(Color.RED);
                meilensteinFolie_title_r.setFontSize(30.0);
                meilensteinFolie_title_r.setBold(true);
                meilensteinFolie_title.setAnchor(new Rectangle(20, 120, 500, 50));

                XSLFTextShape body_meilensteinFolie = meilensteinFolie.getPlaceholder(1);
                body_meilensteinFolie.clearText();

                //Tabelle anlegen
                XSLFTable table_meilensteine = body_meilensteinFolie.getSheet().createTable();
                table_meilensteine.setAnchor(new Rectangle(20, 200, 0, 0));

                XSLFTableRow headerRow_meilensteine = table_meilensteine.addRow();

                XSLFTextBox meilenstein = meilensteinFolie.createTextBox();
                XSLFTextParagraph meilenstein_p = meilenstein.addNewTextParagraph();
                XSLFTextRun meilenstein_r = meilenstein_p.addNewTextRun();
                meilenstein_r.setText("Meilensteinliste:");
                meilenstein_r.setFontSize(16.0);
                meilenstein_r.setBold(true);
                meilenstein_r.setFontColor(new Color(0, 82, 129));
                meilenstein.setAnchor(new Rectangle(10, 140, 140, 100));

                //Header für Tabelle
                for (int i = 0; i < 4; i++) {
                    XSLFTableCell th_detailFolie_meilensteine = headerRow_meilensteine.addCell();
                    XSLFTextParagraph p_th_detailFolie_meilensteine = th_detailFolie_meilensteine.addNewTextParagraph();
                    p_th_detailFolie_meilensteine.setTextAlign(TextParagraph.TextAlign.LEFT);
                    XSLFTextRun r_th_detailFolie_meilensteine = p_th_detailFolie_meilensteine.addNewTextRun();
                    r_th_detailFolie_meilensteine.setBold(true);

                    if (i == 0) {

                        r_th_detailFolie_meilensteine.setText("Titel");
                        table_meilensteine.setColumnWidth(i, 170);
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
                        table_meilensteine.setColumnWidth(i, 170);
                        th_detailFolie_meilensteine.setFillColor(new Color(0, 82, 129));

                        th_detailFolie_meilensteine.setVerticalAlignment(VerticalAlignment.MIDDLE);
                        th_detailFolie_meilensteine.setBorderColor(TableCell.BorderEdge.bottom, new Color(0, 82, 129));
                        th_detailFolie_meilensteine.setBorderWidth(TableCell.BorderEdge.bottom, 1);

                        r_th_detailFolie_meilensteine.setFontColor(new Color(255, 255, 255));
                        r_th_detailFolie_meilensteine.setFontSize(10.0);

                    }
                    if (i == 3) {

                        r_th_detailFolie_meilensteine.setText("Fertigstellung");
                        table_meilensteine.setColumnWidth(i, 170);
                        th_detailFolie_meilensteine.setFillColor(new Color(0, 82, 129));

                        th_detailFolie_meilensteine.setVerticalAlignment(VerticalAlignment.MIDDLE);
                        th_detailFolie_meilensteine.setBorderColor(TableCell.BorderEdge.bottom, new Color(0, 82, 129));
                        th_detailFolie_meilensteine.setBorderWidth(TableCell.BorderEdge.bottom, 1);

                        r_th_detailFolie_meilensteine.setFontColor(new Color(255, 255, 255));
                        r_th_detailFolie_meilensteine.setFontSize(10.0);

                    }
                }


                //Liste, um Meilensteine pro Projekt zu bekommen
                List<Meilensteine> meileinensteineProProjekt = new ArrayList<>();

                for (int i = 0; i < alleMeilensteine.size(); i++) {
                    Meilensteine aktuellerMeilenstein = alleMeilensteine.get(i);
                    if (aktuellerMeilenstein.getProjekt_id().getProjekt_id().equals(allProjects.get(b).getProjekt_id())) {
                        meileinensteineProProjekt.add(aktuellerMeilenstein);
                    }
                }

                //Durchlaufen der Meilensteine pro Projekt
                for (int i = 0; i < meileinensteineProProjekt.size(); i++) {
                    Meilensteine aktuellerMeilenstein = alleMeilensteine.get(i);

                    //Row Meilensteintitel
                    XSLFTableRow detailFolie_tr_meilensteinTitle;
                    detailFolie_tr_meilensteinTitle = table_meilensteine.addRow();
                    detailFolie_tr_meilensteinTitle.setHeight(35.0);

                    //----------------------------------------------Meilensteintitel DB
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

                    //----------------------------------------------Beschreibung DB
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


                    //----------------------------------------------Meilensteinstatus DB
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

                    //Status ändern durch ICON
                    if(allProjects.get(b).getStatus() == 1){

                        cell_detailFolie_meielnsteinStatus_r.setText("\uD83D\uDFE2 planmäßig");
                        cell_detailFolie_meielnsteinStatus_r.setFontColor(new Color(100, 100, 100));
                        cell_detailFolie_meielnsteinStatus_r.setFontSize(10.0);

                    }else if(allProjects.get(b).getStatus() == 2){

                        cell_detailFolie_meielnsteinStatus_r.setText("\uD83D\uDFE0 im Verzug");
                        cell_detailFolie_meielnsteinStatus_r.setFontColor(new Color(100, 100, 100));
                        cell_detailFolie_meielnsteinStatus_r.setFontSize(10.0);

                    }else if(allProjects.get(b).getStatus() == 3){

                        cell_detailFolie_meielnsteinStatus_r.setText("\uD83D\uDD34 verspätet");
                        cell_detailFolie_meielnsteinStatus_r.setFontColor(new Color(100, 100, 100));
                        cell_detailFolie_meielnsteinStatus_r.setFontSize(10.0);

                    }

                    //----------------------------------------------StartDatum DB
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

                    //Stardatum Format ändern
                    LocalDate enddatum = aktuellerMeilenstein.getEnd_datum();

                    cell_detailFolie_meielnsteinStartDatum_r.setText("" + enddatum.format(formatters));
                    cell_detailFolie_meielnsteinStartDatum_r.setFontColor(new Color(100, 100, 100));
                    cell_detailFolie_meielnsteinStartDatum_r.setFontSize(10.0);

                }

                //----------------------------------------------------------------------------------------------------------FOOTER
                XSLFTextBox meilensteinFolie_footer = meilensteinFolie.createTextBox();
                XSLFTextParagraph meilensteinFolie_footer_p = meilensteinFolie_footer.addNewTextParagraph();
                XSLFTextRun meilensteinFolie_footer_r = meilensteinFolie_footer_p.addNewTextRun();
                meilensteinFolie_footer_r.setText("Projekt Portfolio Komitee am " + nextPPK.format(formatters));
                meilensteinFolie_footer_r.setFontColor(new Color(0, 82, 129));
                meilensteinFolie_footer_r.setFontSize(12.);
                meilensteinFolie_footer.setAnchor(new Rectangle(440, 500, 270, 50));
            }
            //</editor-fold>

            //<editor-fold desc="ARBEITSZEITEN">
            //Durchlaufen aller Projekte
            for(int i = 0; i < allProjects.size(); i++){
                //Variable um Gesamtstunden zu erfassen
                double stunden_gesamt = 0.0;
                //Liste der Arbeitszeiten pro Projekt
                List<Arbeitszeiten> arbeitszeitenProProjekt = new ArrayList<>();
                //Durchlaufen der Arbeitszeiten pro Projekt
                for(int j = 0; j < allArbeitszeiten.size(); j++){
                    if(allProjects.get(i).getProjekt_id().equals(allArbeitszeiten.get(j).getEinsaetze_id().getEinsaetze_id().getProjekte_id().getProjekt_id())){
                        arbeitszeitenProProjekt.add(allArbeitszeiten.get(j));
                    }
                }
                //Falls keine Arbeitszeiten eingetragen wurden, wird keine Folie generiert
                if(!arbeitszeitenProProjekt.isEmpty()){

                    //Folie generieren
                    XSLFSlide arbeitszeit = generiertePowerPointPraesentation.createSlide(title_content);

                    //Logo einfügen
                    XSLFPictureShape pic_detailFolie = arbeitszeit.createPicture(pd);
                    pic_detailFolie.setAnchor(new Rectangle(560, 32, 130, 20));

                    //Titel einfügen
                    XSLFTextShape meilensteinFolie_title = arbeitszeit.getPlaceholder(0);
                    meilensteinFolie_title.clearText();
                    XSLFTextParagraph meilensteinFolie_title_p = meilensteinFolie_title.addNewTextParagraph();
                    XSLFTextRun meilensteinFolie_title_r = meilensteinFolie_title_p.addNewTextRun();

                    meilensteinFolie_title_r.setText("" + allProjects.get(i).getTitel()); //Titel des Projekts
                    meilensteinFolie_title_r.setFontColor(Color.RED);
                    meilensteinFolie_title_r.setFontSize(30.0);
                    meilensteinFolie_title_r.setBold(true);
                    meilensteinFolie_title.setAnchor(new Rectangle(20, 30, 700, 50));

                    XSLFTextShape body_meilensteinFolie = arbeitszeit.getPlaceholder(1);
                    body_meilensteinFolie.clearText();

                    //Tabelle anlegen
                    XSLFTable table_meilensteine = body_meilensteinFolie.getSheet().createTable();
                    table_meilensteine.setAnchor(new Rectangle(10, 150, 0, 0));

                    XSLFTableRow headerRow_meilensteine = table_meilensteine.addRow();

                    //Arbeitszeitendatum Format ändern
                    LocalDate arbeitszeitenDatum = LocalDate.parse(arbeitszeitenProProjekt.get(0).getDate());

                    XSLFTextBox meilenstein = arbeitszeit.createTextBox();
                    XSLFTextParagraph meilenstein_p = meilenstein.addNewTextParagraph();
                    XSLFTextRun meilenstein_r = meilenstein_p.addNewTextRun();
                    meilenstein_r.setText("Erfasste Stunden in der Woche vom:                          " +arbeitszeitenDatum.format(formatters));
                    meilenstein_r.setFontSize(16.0);
                    meilenstein_r.setBold(true);
                    meilenstein_r.setFontColor(new Color(0, 82, 129));
                    meilenstein.setAnchor(new Rectangle(30,90,700,100));

                    //Header für die Tabelle
                    for (int a = 0; a < 3; a++) {
                        XSLFTableCell th_detailFolie_meilensteine = headerRow_meilensteine.addCell();
                        XSLFTextParagraph p_th_detailFolie_meilensteine = th_detailFolie_meilensteine.addNewTextParagraph();
                        p_th_detailFolie_meilensteine.setTextAlign(TextParagraph.TextAlign.LEFT);
                        XSLFTextRun r_th_detailFolie_meilensteine = p_th_detailFolie_meilensteine.addNewTextRun();
                        r_th_detailFolie_meilensteine.setBold(true);

                        if (a == 0) {

                            r_th_detailFolie_meilensteine.setText("Mitarbeiter: ");
                            table_meilensteine.setColumnWidth(a, 240);
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
                            table_meilensteine.setColumnWidth(a, 240);
                            th_detailFolie_meilensteine.setFillColor(new Color(0, 82, 129));

                            th_detailFolie_meilensteine.setVerticalAlignment(VerticalAlignment.MIDDLE);
                            th_detailFolie_meilensteine.setBorderColor(TableCell.BorderEdge.bottom, new Color(0, 82, 129));
                            th_detailFolie_meilensteine.setBorderWidth(TableCell.BorderEdge.bottom, 1);

                            r_th_detailFolie_meilensteine.setFontColor(new Color(255, 255, 255));
                            r_th_detailFolie_meilensteine.setFontSize(10.0);

                        }
                        if(a == 2){
                            r_th_detailFolie_meilensteine.setText("Stunden: ");
                            table_meilensteine.setColumnWidth(a, 220);
                            th_detailFolie_meilensteine.setFillColor(new Color(0, 82, 129));

                            th_detailFolie_meilensteine.setVerticalAlignment(VerticalAlignment.MIDDLE);
                            th_detailFolie_meilensteine.setBorderColor(TableCell.BorderEdge.bottom, new Color(0, 82, 129));
                            th_detailFolie_meilensteine.setBorderWidth(TableCell.BorderEdge.bottom, 1);

                            r_th_detailFolie_meilensteine.setFontColor(new Color(255, 255, 255));
                            r_th_detailFolie_meilensteine.setFontSize(10.0);
                        }
                    }

                    //Durchlaufen der Arbeitszeiten pro Projekt
                    for(int c = 0; c < arbeitszeitenProProjekt.size(); c++){

                        //Row
                        XSLFTableRow detailFolie_tr_meilensteinTitle;
                        detailFolie_tr_meilensteinTitle = table_meilensteine.addRow();
                        detailFolie_tr_meilensteinTitle.setHeight(30.0);

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

                        stunden_gesamt+=arbeitszeitenProProjekt.get(c).getArbeitszeit();

                    }

                    //row Gesamtsumme
                    XSLFTableRow detailFolie_tr_meilensteinTitle;
                    detailFolie_tr_meilensteinTitle = table_meilensteine.addRow();
                    detailFolie_tr_meilensteinTitle.setHeight(10.0);

                    //----------------------------------------------
                    XSLFTableCell cell_detailFolie_meielnsteinTitel = detailFolie_tr_meilensteinTitle.addCell();
                    XSLFTextParagraph cell_detailFolie_meielnsteinTitel_p = cell_detailFolie_meielnsteinTitel.addNewTextParagraph();
                    XSLFTextRun cell_detailFolie_meielnsteinTitel_r = cell_detailFolie_meielnsteinTitel_p.addNewTextRun();

                    cell_detailFolie_meielnsteinTitel.setFillColor(new Color(0, 82, 129));
                    cell_detailFolie_meielnsteinTitel.setVerticalAlignment(VerticalAlignment.MIDDLE);

                    cell_detailFolie_meielnsteinTitel.setBorderColor(TableCell.BorderEdge.top, new Color(0, 82, 129));
                    cell_detailFolie_meielnsteinTitel.setBorderColor(TableCell.BorderEdge.right, new Color(0, 82, 129));
                    cell_detailFolie_meielnsteinTitel.setBorderColor(TableCell.BorderEdge.left, new Color(0, 82, 129));

                    cell_detailFolie_meielnsteinTitel_r.setText("");
                    cell_detailFolie_meielnsteinTitel_r.setFontColor(new Color(0, 82, 129));
                    cell_detailFolie_meielnsteinTitel_r.setFontSize(10.0);

                    //----------------------------------------------GesamtSumme Text
                    XSLFTableCell cell_detailFolie_meielnsteinBeschreibung = detailFolie_tr_meilensteinTitle.addCell();
                    XSLFTextParagraph cell_detailFolie_meielnsteinBeschreibung_p = cell_detailFolie_meielnsteinBeschreibung.addNewTextParagraph();
                    XSLFTextRun cell_detailFolie_meielnsteinBeschreibung_r = cell_detailFolie_meielnsteinBeschreibung_p.addNewTextRun();


                    cell_detailFolie_meielnsteinBeschreibung.setFillColor(new Color(0, 82, 129));
                    cell_detailFolie_meielnsteinBeschreibung.setVerticalAlignment(VerticalAlignment.MIDDLE);


                    cell_detailFolie_meielnsteinBeschreibung.setBorderColor(TableCell.BorderEdge.top, Color.white);
                    cell_detailFolie_meielnsteinBeschreibung.setBorderColor(TableCell.BorderEdge.right, new Color(0, 82, 129));
                    cell_detailFolie_meielnsteinBeschreibung.setBorderColor(TableCell.BorderEdge.left, Color.white);

                    cell_detailFolie_meielnsteinBeschreibung_r.setText("Gesamtsumme der Stunden: ");
                    cell_detailFolie_meielnsteinBeschreibung_r.setBold(true);
                    cell_detailFolie_meielnsteinBeschreibung_r.setFontColor(new Color(255, 255, 255));
                    cell_detailFolie_meielnsteinBeschreibung_r.setFontSize(10.0);

                    //----------------------------------------------Gesamtstunden berechnet
                    XSLFTableCell cell_detailFolie_stunden = detailFolie_tr_meilensteinTitle.addCell();
                    XSLFTextParagraph cell_detailFolie_stunden_p = cell_detailFolie_stunden.addNewTextParagraph();
                    XSLFTextRun cell_detailFolie_stunden_r = cell_detailFolie_stunden_p.addNewTextRun();

                    cell_detailFolie_stunden.setFillColor(new Color(0, 82, 129));
                    cell_detailFolie_stunden.setVerticalAlignment(VerticalAlignment.MIDDLE);

                    cell_detailFolie_stunden.setBorderColor(TableCell.BorderEdge.top, Color.white);
                    cell_detailFolie_stunden.setBorderWidth(TableCell.BorderEdge.top, 10.0);
                    cell_detailFolie_stunden.setBorderColor(TableCell.BorderEdge.right, Color.white);
                    cell_detailFolie_stunden.setBorderColor(TableCell.BorderEdge.left, Color.white);

                    cell_detailFolie_stunden_r.setText("" + stunden_gesamt);
                    cell_detailFolie_stunden_r.setBold(true);
                    cell_detailFolie_stunden_r.setFontColor(new Color(255, 255, 255));
                    cell_detailFolie_stunden_r.setFontSize(10.0);

                    //----------------------------------------------------------------------------------------------------------FOOTER
                    XSLFTextBox arbeitszeit_footer = arbeitszeit.createTextBox();
                    XSLFTextParagraph arbeitszeit_footer_p = arbeitszeit_footer.addNewTextParagraph();
                    XSLFTextRun arbeitszeit_footer_r = arbeitszeit_footer_p.addNewTextRun();
                    arbeitszeit_footer_r.setText("Projekt Portfolio Komitee am " + nextPPK.format(formatters));
                    arbeitszeit_footer_r.setFontColor(new Color(0, 82, 129));
                    arbeitszeit_footer_r.setFontSize(12.);
                    arbeitszeit_footer.setAnchor(new Rectangle(440, 500, 270, 50));

                }

            }
            //</editor-fold>

            //<editor-fold desc="ENDFOLIE">
            //Folie generieren
            XSLFSlide letzteFolie = generiertePowerPointPraesentation.createSlide(blank);

            //Bild einfügen
            byte[] endSeite = IOUtils.toByteArray(new FileInputStream("src/main/resources/images/glaubandich.png"));
            XSLFPictureData endSeite_pd = generiertePowerPointPraesentation.addPicture(endSeite, PictureData.PictureType.PNG);
            XSLFPictureShape endSeite_pic = letzteFolie.createPicture(endSeite_pd);
            endSeite_pic.setAnchor(new Rectangle(0,0,720,540));
            //</editor-fold>

            //PowerPoint ausgeben
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
