package com.example.helper;

import com.example.workloads.BeschlussFolien.BeschlussFolien;
import com.example.workloads.BeschlussFolien.BeschlussFolienRepo;
import com.example.workloads.FreieFolien.FreieFolien;
import com.example.workloads.FreieFolien.FreieFolienRepo;
import com.example.workloads.Gaeste.Gaeste;
import com.example.workloads.Gaeste.GaesteRepo;
import com.example.workloads.Meilensteine.Meilensteine;
import com.example.workloads.Meilensteine.MeilensteineRepo;
import com.example.workloads.PPK.PPKRepo;
import com.example.workloads.Personen.Personen;
import com.example.workloads.Phasen_Projekt.Phasen_Projekt;
import com.example.workloads.Phasen_Projekt.Phasen_ProjektRepo;
import com.example.workloads.Projekte.Projekte;
import com.example.workloads.Projekte.ProjekteRepo;
import com.example.workloads.Softwareanforderungen.Softwareanforderungen;
import com.example.workloads.Softwareanforderungen.SoftwareanforderungenRepo;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.sl.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xslf.usermodel.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class PPKMeetingHelper {

    public static ByteArrayInputStream generatePPKPresentation()
            throws FileNotFoundException, IOException, InvalidFormatException {

        //Repos einbinden
        final ProjekteRepo projekteRepo = new ProjekteRepo();
        final SoftwareanforderungenRepo softwareanforderungenRepo = new SoftwareanforderungenRepo();
        final BeschlussFolienRepo beschlussFolienRepo = new BeschlussFolienRepo();
        final MeilensteineRepo meilensteineRepo = new MeilensteineRepo();
        final PPKRepo ppk = new PPKRepo();
        final FreieFolienRepo freieFolienRepo = new FreieFolienRepo();
        final Phasen_ProjektRepo phasen_projektRepo = new Phasen_ProjektRepo(softwareanforderungenRepo);
        final GaesteRepo gaesteRepo = new GaesteRepo();

        //GET --> alle Projekte
        List<Projekte> allProjects = new ArrayList<>();
        allProjects = projekteRepo.listAll();

        //GET --> Projektmanager pro ID
        Long idManager = 1L;
        Personen projekmanager = projekteRepo.getProjektmanager(idManager);

        //GET --> Fachkoordinator pro ID
        Long idFachkoordinator = 1L;
        Personen fachkoordinator = projekteRepo.getFachkoordinator(idFachkoordinator);

        //GET --> alle Softwareanforderungen
        List<Softwareanforderungen> allSoftwareanforderungen = new ArrayList<>();
        allSoftwareanforderungen = softwareanforderungenRepo.listAll();

        //GET --> alle Meilensteine
        List<Meilensteine> alleMeilensteine = new ArrayList<>();
        alleMeilensteine = meilensteineRepo.listAll();

        //GET --> alle Phasenprojekte
        List<Phasen_Projekt> allePhasen = new ArrayList<>();
        allePhasen = phasen_projektRepo.listAll();

        //GET --> alle Beschlüsse
        List<BeschlussFolien> allBeschluss = new ArrayList<>();
        allBeschluss = beschlussFolienRepo.listAll();

        //GET --> alle freien Folien
        List<FreieFolien> freieFoliens = new ArrayList<>();
        freieFoliens = freieFolienRepo.listAll();

        //GET --> alle Gäste
        List<Gaeste> alleGaeste = new ArrayList<>();
        alleGaeste = gaesteRepo.listAll();

        //Ändern des Datumsformats beim PPK
        LocalDate nextPPK = ppk.getNextPPK();
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date_PPK = nextPPK.format(formatters);
        LocalDate parsedPPK = LocalDate.parse(date_PPK, formatters);

        //Circle für Datum

        //Ändern des Datumformats beim Enddatum

        var count = meilensteineRepo.count();
        Long projekteanzahl = projekteRepo.getProjekteAnzahl();


        try(XMLSlideShow generiertePowerPointPraesentation = new XMLSlideShow()) {

            //<editor-fold desc="Folien mit einzelnen Layout">
            //Layout der Folien
            XSLFSlideMaster defaultMaster = generiertePowerPointPraesentation.getSlideMasters().get(0);
            XSLFSlideLayout title_content = defaultMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);
            XSLFSlideLayout title = defaultMaster.getLayout(SlideLayout.TITLE);
            XSLFSlideLayout blank = defaultMaster.getLayout(SlideLayout.BLANK);
            XSLFSlideLayout title_only = defaultMaster.getLayout(SlideLayout.TITLE_ONLY);

            //<editor-fold desc="LOGO">
            //Logo erstellen
            byte[] pictureData = IOUtils.toByteArray(new FileInputStream("src/main/resources/images/logo_sparkasse.png"));
            XSLFPictureData pd = generiertePowerPointPraesentation.addPicture(pictureData, PictureData.PictureType.PNG);
            //</editor-fold>

            //<editor-fold desc="EINLEITUNGSFOLIE ">
            XSLFSlide einleitungsFolie = generiertePowerPointPraesentation.createSlide(blank);

            byte[] hintergrund = IOUtils.toByteArray(new FileInputStream("src/main/resources/images/startseite.png"));
            XSLFPictureData hintergrund_pd = generiertePowerPointPraesentation.addPicture(hintergrund, PictureData.PictureType.PNG);
            XSLFPictureShape hintergrund_pic = einleitungsFolie.createPicture(hintergrund_pd);
            hintergrund_pic.setAnchor(new Rectangle(0,0,720,540));

            XSLFTextBox balken = einleitungsFolie.createTextBox();
            balken.setFillColor(new Color(195,227,248));
            balken.setAnchor(new Rectangle(0,460,720,80));

            //----Logo anlegen
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

            //<editor-fold desc="Erste Folie --> STARTBILD">
            XSLFSlide ersteFolie = generiertePowerPointPraesentation.createSlide(blank);
            byte[] ersteSeite = IOUtils.toByteArray(new FileInputStream("src/main/resources/images/firstPicture.png"));
            XSLFPictureData ersteSeite_pd = generiertePowerPointPraesentation.addPicture(ersteSeite, PictureData.PictureType.PNG);
            XSLFPictureShape ersteSeite_pic = ersteFolie.createPicture(ersteSeite_pd);
            ersteSeite_pic.setAnchor(new Rectangle(0,0,720,540));
            //</editor-fold>

            //<editor-fold desc="STARTFOLIE ">
            XSLFSlide startFolie = generiertePowerPointPraesentation.createSlide(blank);
            byte[] startSeite = IOUtils.toByteArray(new FileInputStream("src/main/resources/images/laufende_projekte.png"));
            XSLFPictureData startSeite_pd = generiertePowerPointPraesentation.addPicture(startSeite, PictureData.PictureType.PNG);
            XSLFPictureShape startSeite_pic = startFolie.createPicture(startSeite_pd);
            startSeite_pic.setAnchor(new Rectangle(0,0,750,540));

            XSLFTextBox nextPPKMeeting = startFolie.createTextBox();
            XSLFTextParagraph nextPPKMeeting_p = nextPPKMeeting.addNewTextParagraph();
            XSLFTextRun nextPPKMeeting_r = nextPPKMeeting_p.addNewTextRun();
            nextPPKMeeting_r.setText("");
            nextPPKMeeting_r.setBold(true);
            nextPPKMeeting_r.setFontColor(Color.white);
            nextPPKMeeting_r.setFontSize(70.0);
            nextPPKMeeting.setAnchor(new Rectangle(450, 10, 720, 50));
            //</editor-fold>

            //<editor-fold desc="Zweite Folie --> PROJEKT-PORTFOLIO">
            XSLFSlide projektportfolioFolie = generiertePowerPointPraesentation.createSlide(title_content);
            //----Logo anlegen
            XSLFPictureShape pic_projektportfolioFolie = projektportfolioFolie.createPicture(pd);
            pic_projektportfolioFolie.setAnchor(new Rectangle(560, 32, 130, 20));

            //Titel erstellen
            XSLFTextShape title_projektportfolio = projektportfolioFolie.getPlaceholder(0);
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
            XSLFTextShape body_projektportfolio = projektportfolioFolie.getPlaceholder(1);
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
                if(i==0) {
                    XSLFTextRun r_th_projektportfolio = p_th_projektportfolio.addNewTextRun();
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
                    XSLFTextRun r_th_projektportfolio = p_th_projektportfolio.addNewTextRun();
                    r_th_projektportfolio.setText("Status");
                    table_projektportfolio.setColumnWidth(i,100);
                    th_projektportfolio.setFillColor(new Color(255,255,255));

                    th_projektportfolio.setVerticalAlignment(VerticalAlignment.MIDDLE);
                    th_projektportfolio.setBorderColor(TableCell.BorderEdge.bottom, new Color(211,211,211));
                    th_projektportfolio.setBorderWidth(TableCell.BorderEdge.bottom, 1);

                    r_th_projektportfolio.setFontSize(10.0);
                    r_th_projektportfolio.setFontColor(new Color(160,160,160));

                }if(i==2){
                    XSLFTextRun r_th_projektportfolio = p_th_projektportfolio.addNewTextRun();
                    r_th_projektportfolio.setText("Projektmanager:in");
                    table_projektportfolio.setColumnWidth(i,100);
                    th_projektportfolio.setFillColor(new Color(255,255,255));

                    th_projektportfolio.setVerticalAlignment(VerticalAlignment.MIDDLE);
                    th_projektportfolio.setBorderColor(TableCell.BorderEdge.bottom, new Color(211,211,211));
                    th_projektportfolio.setBorderWidth(TableCell.BorderEdge.bottom, 1);

                    r_th_projektportfolio.setFontSize(10.0);
                    r_th_projektportfolio.setFontColor(new Color(160,160,160));
                }if(i==3){
                    XSLFTextRun r_th_projektportfolio = p_th_projektportfolio.addNewTextRun();
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
                    XSLFTextRun r_th_projektportfolio = p_th_projektportfolio.addNewTextRun();
                    r_th_projektportfolio.setText("Startdatum");
                    table_projektportfolio.setColumnWidth(i,100);
                    th_projektportfolio.setFillColor(new Color(255,255,255));

                    th_projektportfolio.setVerticalAlignment(VerticalAlignment.MIDDLE);
                    th_projektportfolio.setBorderColor(TableCell.BorderEdge.bottom, new Color(211,211,211));
                    th_projektportfolio.setBorderWidth(TableCell.BorderEdge.bottom, 1);

                    r_th_projektportfolio.setFontSize(10.0);
                    r_th_projektportfolio.setFontColor(new Color(160,160,160));
                }if(i==5){
                    XSLFTextRun r_th_projektportfolio = p_th_projektportfolio.addNewTextRun();
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

            for(int generate = 0; generate < projekteanzahl; generate++){

                idManager++;
                idFachkoordinator++;

                //Erstes Projekt
                XSLFTableRow projektportfolio_tr_projekt1;
                projektportfolio_tr_projekt1=table_projektportfolio.addRow();
                projektportfolio_tr_projekt1.setHeight(33.0);

                //----------------------------------------------Projektname
                XSLFTableCell cell_projektprotfolio_projektname = projektportfolio_tr_projekt1.addCell();
                XSLFTextParagraph cell_projektprotfolio_projektname_p = cell_projektprotfolio_projektname.addNewTextParagraph();
                XSLFTextRun cell_projektprotfolio_projektname_r = cell_projektprotfolio_projektname_p.addNewTextRun();
                cell_projektprotfolio_projektname_r.setText(""+allProjects.get(generate).getTitel());
                cell_projektprotfolio_projektname_r.setFontColor(new Color(0, 82, 129));
                cell_projektprotfolio_projektname_r.setFontSize(10.0);

                cell_projektprotfolio_projektname.setFillColor(new Color(255,255,255));
                cell_projektprotfolio_projektname.setVerticalAlignment(VerticalAlignment.MIDDLE);

                cell_projektprotfolio_projektname.setBorderColor(TableCell.BorderEdge.top,Color.white);
                cell_projektprotfolio_projektname.setBorderColor(TableCell.BorderEdge.right,Color.white);
                cell_projektprotfolio_projektname.setBorderColor(TableCell.BorderEdge.left,Color.white);
                cell_projektprotfolio_projektname.setBorderColor(TableCell.BorderEdge.bottom,new Color(211,211,211));
                cell_projektprotfolio_projektname.setBorderWidth(TableCell.BorderEdge.bottom,1);

                //----------------------------------------------Projektstatus
                XSLFTableCell cell_projektprotfolio_status = projektportfolio_tr_projekt1.addCell();
                XSLFTextParagraph cell_projektprotfolio_status_p = cell_projektprotfolio_status.addNewTextParagraph();


                cell_projektprotfolio_status.setFillColor(new Color(255,255,255));
                cell_projektprotfolio_status.setVerticalAlignment(VerticalAlignment.MIDDLE);


                cell_projektprotfolio_status.setBorderColor(TableCell.BorderEdge.top,Color.white);
                cell_projektprotfolio_status.setBorderColor(TableCell.BorderEdge.right,Color.white);
                cell_projektprotfolio_status.setBorderColor(TableCell.BorderEdge.left,Color.white);
                cell_projektprotfolio_status.setBorderColor(TableCell.BorderEdge.bottom,new Color(211,211,211));
                cell_projektprotfolio_status.setBorderWidth(TableCell.BorderEdge.bottom,1);

                if(allProjects.get(generate).getStatus() == 1){
                    XSLFTextRun cell_projektprotfolio_status_r = cell_projektprotfolio_status_p.addNewTextRun();
                    cell_projektprotfolio_status_r.setText("\uD83D\uDFE2 planmäßig");
                    cell_projektprotfolio_status_r.setFontColor(new Color(100,100,100));
                    cell_projektprotfolio_status_r.setFontSize(10.0);

                }else if(allProjects.get(generate).getStatus() == 2){
                    XSLFTextRun cell_projektprotfolio_status_r = cell_projektprotfolio_status_p.addNewTextRun();
                    cell_projektprotfolio_status_r.setText("\uD83D\uDFE0 im Verzug");
                    cell_projektprotfolio_status_r.setFontColor(new Color(100,100,100));
                    cell_projektprotfolio_status_r.setFontSize(10.0);

                }else if(allProjects.get(generate).getStatus() == 3){
                    XSLFTextRun cell_projektprotfolio_status_r = cell_projektprotfolio_status_p.addNewTextRun();
                    cell_projektprotfolio_status_r.setText("\uD83D\uDD34 verspätet");
                    cell_projektprotfolio_status_r.setFontColor(new Color(100,100,100));
                    cell_projektprotfolio_status_r.setFontSize(10.0);

                }

                //----------------------------------------------Projektmanager
                XSLFTableCell cell_projektprotfolio_projektmanager = projektportfolio_tr_projekt1.addCell();
                XSLFTextParagraph cell_projektprotfolio_projektmanager_p = cell_projektprotfolio_projektmanager.addNewTextParagraph();
                XSLFTextRun cell_projektprotfolio_projektmanager_r = cell_projektprotfolio_projektmanager_p.addNewTextRun();
                cell_projektprotfolio_projektmanager_r.setText(""+projekmanager.getVorname() +" "+projekmanager.getNachname());
                cell_projektprotfolio_projektmanager_r.setFontColor(new Color(100,100,100));
                cell_projektprotfolio_projektmanager_r.setFontSize(10.0);


                cell_projektprotfolio_projektmanager.setFillColor(new Color(255,255,255));
                cell_projektprotfolio_projektmanager.setVerticalAlignment(VerticalAlignment.MIDDLE);


                cell_projektprotfolio_projektmanager.setBorderColor(TableCell.BorderEdge.top,Color.white);
                cell_projektprotfolio_projektmanager.setBorderColor(TableCell.BorderEdge.right,Color.white);
                cell_projektprotfolio_projektmanager.setBorderColor(TableCell.BorderEdge.left,Color.white);
                cell_projektprotfolio_projektmanager.setBorderColor(TableCell.BorderEdge.bottom,new Color(211,211,211));
                cell_projektprotfolio_projektmanager.setBorderWidth(TableCell.BorderEdge.bottom,1);

                //----------------------------------------------Fachkoordinator
                XSLFTableCell cell_projektprotfolio_fachkoordinator = projektportfolio_tr_projekt1.addCell();
                XSLFTextParagraph cell_projektprotfolio_fachkoordinator_p = cell_projektprotfolio_fachkoordinator.addNewTextParagraph();
                XSLFTextRun cell_projektprotfolio_fachkoordinator_r = cell_projektprotfolio_fachkoordinator_p.addNewTextRun();
                cell_projektprotfolio_fachkoordinator_r.setText(""+fachkoordinator.getVorname() +" " + fachkoordinator.getNachname());
                cell_projektprotfolio_fachkoordinator_r.setFontColor(new Color(100,100,100));
                cell_projektprotfolio_fachkoordinator_r.setFontSize(10.0);


                cell_projektprotfolio_fachkoordinator.setFillColor(new Color(255,255,255));
                cell_projektprotfolio_fachkoordinator.setVerticalAlignment(VerticalAlignment.MIDDLE);


                cell_projektprotfolio_fachkoordinator.setBorderColor(TableCell.BorderEdge.top,Color.white);
                cell_projektprotfolio_fachkoordinator.setBorderColor(TableCell.BorderEdge.right,Color.white);
                cell_projektprotfolio_fachkoordinator.setBorderColor(TableCell.BorderEdge.left,Color.white);
                cell_projektprotfolio_fachkoordinator.setBorderColor(TableCell.BorderEdge.bottom,new Color(211,211,211));
                cell_projektprotfolio_fachkoordinator.setBorderWidth(TableCell.BorderEdge.bottom,1);

                //----------------------------------------------Startdatum
                XSLFTableCell cell_projektprotfolio_startdatum = projektportfolio_tr_projekt1.addCell();
                XSLFTextParagraph cell_projektprotfolio_startdatum_p = cell_projektprotfolio_startdatum.addNewTextParagraph();
                XSLFTextRun cell_projektprotfolio_startdatum_r = cell_projektprotfolio_startdatum_p.addNewTextRun();

                //Startdatum
                LocalDate startDatum = alleMeilensteine.get(generate).getStart_datum();

                cell_projektprotfolio_startdatum_r.setText(""+startDatum.format(formatters));
                cell_projektprotfolio_startdatum_r.setFontColor(new Color(100,100,100));
                cell_projektprotfolio_startdatum_r.setFontSize(10.0);


                cell_projektprotfolio_startdatum.setFillColor(new Color(255,255,255));
                cell_projektprotfolio_startdatum.setVerticalAlignment(VerticalAlignment.MIDDLE);


                cell_projektprotfolio_startdatum.setBorderColor(TableCell.BorderEdge.top,Color.white);
                cell_projektprotfolio_startdatum.setBorderColor(TableCell.BorderEdge.right,Color.white);
                cell_projektprotfolio_startdatum.setBorderColor(TableCell.BorderEdge.left,Color.white);
                cell_projektprotfolio_startdatum.setBorderColor(TableCell.BorderEdge.bottom,new Color(211,211,211));
                cell_projektprotfolio_startdatum.setBorderWidth(TableCell.BorderEdge.bottom,1);


                //----------------------------------------------Planfertigstellung
                XSLFTableCell cell_projektprotfolio_planfertigstellung = projektportfolio_tr_projekt1.addCell();
                XSLFTextParagraph cell_projektprotfolio_planfertigstellung_p = cell_projektprotfolio_planfertigstellung.addNewTextParagraph();
                XSLFTextRun cell_projektprotfolio_planfertigstellung_r = cell_projektprotfolio_planfertigstellung_p.addNewTextRun();
                //Enddatum
                LocalDate endDatum = allProjects.get(generate).getEnd_datum();

                cell_projektprotfolio_planfertigstellung_r.setText("" + endDatum.format(formatters));
                cell_projektprotfolio_planfertigstellung_r.setFontColor(new Color(100,100,100));
                cell_projektprotfolio_planfertigstellung_r.setFontSize(10.0);


                cell_projektprotfolio_planfertigstellung.setFillColor(new Color(255,255,255));
                cell_projektprotfolio_planfertigstellung.setVerticalAlignment(VerticalAlignment.MIDDLE);


                cell_projektprotfolio_planfertigstellung.setBorderColor(TableCell.BorderEdge.top,Color.white);
                cell_projektprotfolio_planfertigstellung.setBorderColor(TableCell.BorderEdge.right,Color.white);
                cell_projektprotfolio_planfertigstellung.setBorderColor(TableCell.BorderEdge.left,Color.white);
                cell_projektprotfolio_planfertigstellung.setBorderColor(TableCell.BorderEdge.bottom,new Color(211,211,211));
                cell_projektprotfolio_planfertigstellung.setBorderWidth(TableCell.BorderEdge.bottom,1);

            }

            //----------------------------------------------------------------------------------------------------------FOOTER
            XSLFTextBox projektportfolio_footer = projektportfolioFolie.createTextBox();
            XSLFTextParagraph projektportfolio_footer_p = projektportfolio_footer.addNewTextParagraph();
            XSLFTextRun projektportfolio_footer_r = projektportfolio_footer_p.addNewTextRun();
            projektportfolio_footer_r.setText("Projekt Portfolio Komitee (PPK) am " +parsedPPK.format(formatters));
            projektportfolio_footer_r.setFontColor(new Color(0, 82, 129));
            projektportfolio_footer_r.setFontSize(12.);
            projektportfolio_footer.setAnchor(new Rectangle(440, 500, 270, 50));
            //</editor-fold>

            //<editor-fold desc="Dritte Folie --> SOFTWAREANFORDERUNGEN">
            byte[] raute_start = IOUtils.toByteArray(new FileInputStream("src/main/resources/images/raute1.png"));
            XSLFPictureData raute_start_pd = generiertePowerPointPraesentation.addPicture(raute_start, PictureData.PictureType.PNG);

            byte[] pfeil = IOUtils.toByteArray(new FileInputStream("src/main/resources/images/pfeil.png"));
            XSLFPictureData pfeil_pd = generiertePowerPointPraesentation.addPicture(pfeil, PictureData.PictureType.PNG);

            byte[] raute_ende = IOUtils.toByteArray(new FileInputStream("src/main/resources/images/raute2.png"));
            XSLFPictureData raute_ende_pd = generiertePowerPointPraesentation.addPicture(raute_ende, PictureData.PictureType.PNG);

            for(int i = 0; i < allSoftwareanforderungen.size() ; i++) {
                XSLFSlide softwareanforderungsFolie = generiertePowerPointPraesentation.createSlide(title);
                //----Logo anlegen
                XSLFPictureShape pic_softwareanforderungsFolie = softwareanforderungsFolie.createPicture(pd);
                pic_softwareanforderungsFolie.setAnchor(new Rectangle(560, 32, 130, 20));

                //Titel erstellen
                XSLFTextShape title_softwareanforderungsFolie = softwareanforderungsFolie.getPlaceholder(0);
                title_softwareanforderungsFolie.clearText();
                XSLFTextParagraph title_softwareanforderungsFolie_pt = title_softwareanforderungsFolie.addNewTextParagraph();
                XSLFTextRun title_softwareanforderungsFolie_rt = title_softwareanforderungsFolie_pt.addNewTextRun();
                title_softwareanforderungsFolie_rt.setText("Softwareanforderungen");
                title_softwareanforderungsFolie_rt.setFontColor(new Color(0, 82, 129));
                title_softwareanforderungsFolie_rt.setFontSize(26.0);
                title_softwareanforderungsFolie_rt.setBold(true);
                title_softwareanforderungsFolie.setAnchor(new Rectangle(10, 20, 380, 50));

                //Untertitel erstellen
                XSLFTextShape subtitle_softwareanforderungsFolie = softwareanforderungsFolie.getPlaceholder(1);
                subtitle_softwareanforderungsFolie.clearText();
                XSLFTextParagraph subtitle_softwareanforderungsFolie_pst = subtitle_softwareanforderungsFolie.addNewTextParagraph();
                XSLFTextRun subtitle_softwareanforderungsFolie_rst = subtitle_softwareanforderungsFolie_pst.addNewTextRun();
                //aus Datenbank Projektname holen
                subtitle_softwareanforderungsFolie_rst.setText("" + allProjects.get(i).getTitel());
                subtitle_softwareanforderungsFolie_rst.setFontColor(new Color(0, 82, 129));
                subtitle_softwareanforderungsFolie_rst.setFontSize(22.0);
                subtitle_softwareanforderungsFolie.setAnchor(new Rectangle(60, 60, 245, 50));

                //Textbox --> Auftrag von = bleibt immer fix
                XSLFTextBox auftragVon = softwareanforderungsFolie.createTextBox();
                XSLFTextParagraph auftragVon_p = auftragVon.addNewTextParagraph();
                XSLFTextRun auftragVon_r = auftragVon_p.addNewTextRun();
                auftragVon_r.setText("Auftrag von:");
                auftragVon_r.setFontColor(new Color(0, 82, 129));
                auftragVon_r.setFontSize(16.0);
                auftragVon_r.setBold(true);
                auftragVon.setAnchor(new Rectangle(65, 100, 245, 59));

                //Textbox --> Auftraggeber = ändert sich durch Abfrage auf die Datenbank
                XSLFTextBox auftraggeber = softwareanforderungsFolie.createTextBox();
                XSLFTextParagraph auftraggeber_p = auftraggeber.addNewTextParagraph();
                XSLFTextRun auftraggeber_r = auftraggeber_p.addNewTextRun();
                auftraggeber_r.setText("" + projekmanager.getVorname() + " " + projekmanager.getNachname());
                auftraggeber_r.setFontColor(new Color(0, 82, 129));
                auftraggeber_r.setFontSize(16.0);
                auftraggeber.setAnchor(new Rectangle(155, 100, 245, 59));

                //Textbox --> geplanter Einsatzpunkt = bleibt immer fix
                XSLFTextBox geplanter_Einsatzpunkt = softwareanforderungsFolie.createTextBox();
                XSLFTextParagraph geplanter_Einsatzpunkt_p = geplanter_Einsatzpunkt.addNewTextParagraph();
                XSLFTextRun geplanter_Einsatzpunkt_r = geplanter_Einsatzpunkt_p.addNewTextRun();
                geplanter_Einsatzpunkt_r.setText("Geplanter Einsatzpunkt:");
                geplanter_Einsatzpunkt_r.setFontColor(new Color(0, 82, 129));
                geplanter_Einsatzpunkt_r.setFontSize(16.0);
                geplanter_Einsatzpunkt_r.setBold(true);
                geplanter_Einsatzpunkt.setAnchor(new Rectangle(65, 120, 245, 59));

                //Textbox --> Einsatzpunkt = ändert sich durch Abfrage auf die Datenbank

                LocalDate einsatzPunkt = allProjects.get(i).getEnd_datum();

                XSLFTextBox einsatzpunkt = softwareanforderungsFolie.createTextBox();
                XSLFTextParagraph einsatzpunkt_p = einsatzpunkt.addNewTextParagraph();
                XSLFTextRun einsatzpunkt_r = einsatzpunkt_p.addNewTextRun();
                einsatzpunkt_r.setText("" + einsatzPunkt.format(formatters));
                einsatzpunkt_r.setFontColor(new Color(0, 82, 129));
                einsatzpunkt_r.setFontSize(16.0);
                einsatzpunkt.setAnchor(new Rectangle(230, 120, 245, 59));

                //Textbox --> Beschreibung = bleibt immer fix
                XSLFTextBox beschreibung_softwareanforderung = softwareanforderungsFolie.createTextBox();
                XSLFTextParagraph beschreibung_softwareanforderung_p = beschreibung_softwareanforderung.addNewTextParagraph();
                XSLFTextRun beschreibung_softwareanforderung_r = beschreibung_softwareanforderung_p.addNewTextRun();
                beschreibung_softwareanforderung_r.setText("Beschreibung");
                beschreibung_softwareanforderung_r.setFontColor(new Color(0, 82, 129));
                beschreibung_softwareanforderung_r.setFontSize(20.0);
                beschreibung_softwareanforderung_r.setBold(true);
                beschreibung_softwareanforderung.setAnchor(new Rectangle(65, 180, 130, 59));

                //Textbox --> Beschreibung = ändert sich durch Abfrage auf die Datenbank
                XSLFTextBox beschreibungsText = softwareanforderungsFolie.createTextBox();
                XSLFTextParagraph beschreibungsText_p = beschreibungsText.addNewTextParagraph();
                XSLFTextRun beschreibungsText_r = beschreibungsText_p.addNewTextRun();
                beschreibungsText_r.setText("" + allSoftwareanforderungen.get(i).getBeschreibung());
                beschreibungsText_r.setFontColor(new Color(0, 82, 129));
                beschreibungsText_r.setFontSize(16.0);
                beschreibungsText.setAnchor(new Rectangle(65, 210, 410, 100));

                //Textbox --> Status Anforderungsprozess = bleibt immer fix
                XSLFTextBox anforderungsProzess = softwareanforderungsFolie.createTextBox();
                XSLFTextParagraph anforderungsProzess_p = anforderungsProzess.addNewTextParagraph();
                XSLFTextRun anforderungsProzess_r = anforderungsProzess_p.addNewTextRun();
                anforderungsProzess_r.setText("Status Anforderungsprozess");
                anforderungsProzess_r.setFontColor(new Color(0, 82, 129));
                anforderungsProzess_r.setFontSize(20.0);
                anforderungsProzess_r.setBold(true);
                anforderungsProzess.setAnchor(new Rectangle(65, 330, 250, 59));

                //Textbox --> Status Anforderungsprozess = ändert sich durch Abfrage auf die Datenbank
                XSLFTextBox anforderungsProzessText = softwareanforderungsFolie.createTextBox();
                XSLFTextParagraph anforderungsProzessText_p = anforderungsProzessText.addNewTextParagraph();
                XSLFTextRun anforderungsProzessText_r = anforderungsProzessText_p.addNewTextRun();
                anforderungsProzessText_r.setText("" + allSoftwareanforderungen.get(i).getAnforderungsprozess());
                anforderungsProzessText_r.setFontColor(new Color(0, 82, 129));
                anforderungsProzessText_r.setFontSize(16.0);
                anforderungsProzessText.setAnchor(new Rectangle(65, 360, 410, 100));

                //----------------------------------------------------------------------------------------------------------ABLAUF-DIAGRAMM
                //Ablaufdiagramm
                XSLFPictureShape raute_start_pic = softwareanforderungsFolie.createPicture(raute_start_pd);
                raute_start_pic.setAnchor(new Rectangle(562, 66, 65, 45));

                XSLFPictureShape pfeil1 = softwareanforderungsFolie.createPicture(pfeil_pd);
                pfeil1.setAnchor(new Rectangle(588, 147, 13, 13));

                XSLFPictureShape pfeil2 = softwareanforderungsFolie.createPicture(pfeil_pd);
                pfeil2.setAnchor(new Rectangle(588, 197, 13, 13));

                XSLFPictureShape pfeil3 = softwareanforderungsFolie.createPicture(pfeil_pd);
                pfeil3.setAnchor(new Rectangle(588, 247, 13, 13));

                XSLFPictureShape pfeil4 = softwareanforderungsFolie.createPicture(pfeil_pd);
                pfeil4.setAnchor(new Rectangle(588, 297, 13, 13));

                XSLFPictureShape pfeil5 = softwareanforderungsFolie.createPicture(pfeil_pd);
                pfeil5.setAnchor(new Rectangle(588, 347, 13, 13));

                XSLFPictureShape pfeil6 = softwareanforderungsFolie.createPicture(pfeil_pd);
                pfeil6.setAnchor(new Rectangle(588, 397, 13, 13));

                XSLFPictureShape raute_ende_pic = softwareanforderungsFolie.createPicture(raute_ende_pd);
                raute_ende_pic.setAnchor(new Rectangle(560, 447, 70, 45));

                for(int a = 0; a < allePhasen.size(); a++) {

                    if(allSoftwareanforderungen.get(i).getProjekte_id().getProjekt_id().equals(allePhasen.get(a).getSoftwareanforderungen_id().getProjekte_id().getProjekt_id())) {

                        XSLFAutoShape anforderungserhebung = softwareanforderungsFolie.createAutoShape();
                        anforderungserhebung.setShapeType(ShapeType.RECT);
                        anforderungserhebung.setAnchor(new Rectangle(490, (int) (110 + 50 * (allePhasen.get(a).getPhasen_id().getPhasen_id()-1)), 210, 35));

                        if(allePhasen.get(a).getStatus() == 0){
                            anforderungserhebung.setFillColor(new Color(172, 192, 219));
                            anforderungserhebung.setLineColor(new Color(172, 192, 219));
                        }else if(allePhasen.get(a).getStatus() == 1){
                            anforderungserhebung.setFillColor(new Color(210, 210, 210));
                            anforderungserhebung.setLineColor(new Color(210, 210, 210));
                        }else if(allePhasen.get(a).getStatus() == 2){
                            anforderungserhebung.setFillColor(new Color(217, 171, 168));
                            anforderungserhebung.setLineColor(new Color(217, 171, 168));

                        }

                        anforderungserhebung.setLineWidth(0.5);
                        anforderungserhebung.setHorizontalCentered(true);

                        anforderungserhebung.setText("" + allePhasen.get(a).getPhasen_id().getTitel());
                        XSLFTextRun tr_anforderungserhebung = anforderungserhebung.getTextParagraphs().get(0).getTextRuns().get(0);
                        tr_anforderungserhebung.setFontSize(15.0);
                        tr_anforderungserhebung.setBold(true);
                        tr_anforderungserhebung.setFontColor(Color.white);
                        anforderungserhebung.setVerticalAlignment(org.apache.poi.sl.usermodel.VerticalAlignment.MIDDLE);
                    }
                }

                //----------------------------------------------------------------------------------------------------------FOOTER
                XSLFTextBox softwareanforderungsfolie_footer = softwareanforderungsFolie.createTextBox();
                XSLFTextParagraph softwareanforderungsfolie_footer_p = softwareanforderungsfolie_footer.addNewTextParagraph();
                XSLFTextRun softwareanforderungsfolie_footer_r = softwareanforderungsfolie_footer_p.addNewTextRun();
                softwareanforderungsfolie_footer_r.setText("Projekt Portfolio Komitee (PPK) am " + parsedPPK.format(formatters));
                softwareanforderungsfolie_footer_r.setFontColor(new Color(0, 82, 129));
                softwareanforderungsfolie_footer_r.setFontSize(12.);
                softwareanforderungsfolie_footer.setAnchor(new Rectangle(440, 500, 270, 50));
            }
            //</editor-fold>

            //<editor-fold desc="Vierte Folie --> ENTSCHEIDUNGSFOLIE">

            for(int i = 0; i < allBeschluss.size(); i++) {

                    XSLFSlide entscheidungsFolie = generiertePowerPointPraesentation.createSlide(title);
                    //----Logo anlegen
                    XSLFPictureShape pic_entscheidungsFolie = entscheidungsFolie.createPicture(pd);
                    pic_entscheidungsFolie.setAnchor(new Rectangle(560, 32, 130, 20));

                    //Titel erstellen
                    XSLFTextShape entscheidungsFolie_title = entscheidungsFolie.getPlaceholder(0);
                    entscheidungsFolie_title.clearText();
                    XSLFTextParagraph entscheidungsFolie_title_p = entscheidungsFolie_title.addNewTextParagraph();
                    XSLFTextRun entscheidungsFolie_title_r = entscheidungsFolie_title_p.addNewTextRun();
                    entscheidungsFolie_title_r.setText("" + allBeschluss.get(i).getPpk_projekte_id().getPpk_projekte_id().getProjekte_id().getTitel());
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
                    entscheidungsFolie_subtitle.setAnchor(new Rectangle(20, 60, 380, 50));

                    //pic1
                    byte[] aenderungsVorschlag = IOUtils.toByteArray(new FileInputStream("src/main/resources/images/aenderung.png"));
                    XSLFPictureData aenderungsVorschlag_pd = generiertePowerPointPraesentation.addPicture(aenderungsVorschlag, PictureData.PictureType.PNG);
                    XSLFPictureShape aenderungsVorschlag_pic = entscheidungsFolie.createPicture(aenderungsVorschlag_pd);
                    aenderungsVorschlag_pic.setAnchor(new Rectangle(70, 120, 50, 65));

                    //pic2
                    byte[] empfehlung = IOUtils.toByteArray(new FileInputStream("src/main/resources/images/play.png"));
                    XSLFPictureData empfehlung_pd = generiertePowerPointPraesentation.addPicture(empfehlung, PictureData.PictureType.PNG);
                    XSLFPictureShape empfehlung_pic = entscheidungsFolie.createPicture(empfehlung_pd);
                    empfehlung_pic.setAnchor(new Rectangle(65, 370, 60, 50));

                    //Text for aenderungdVorschlag
                    XSLFTextBox aenderungsText = entscheidungsFolie.createTextBox();
                    XSLFTextParagraph aenderungs_p = aenderungsText.addNewTextParagraph();
                    XSLFTextRun aenderungs_r = aenderungs_p.addNewTextRun();
                    aenderungs_r.setText("" + allBeschluss.get(i).getFreitext());
                    aenderungs_r.setFontColor(new Color(0, 82, 129));
                    aenderungs_r.setFontSize(16.);
                    aenderungsText.setAnchor(new Rectangle(150, 100, 550, 260));

                    //Text for empfehlung des PPK
                    XSLFTextBox empfehlungsText = entscheidungsFolie.createTextBox();
                    XSLFTextParagraph empfehlung_p = empfehlungsText.addNewTextParagraph();
                    XSLFTextRun empfehlung_r = empfehlung_p.addNewTextRun();
                    empfehlung_r.setText("Das PPK empfiehlt die oben beschriebene Vorgehensweise.");
                    empfehlung_r.setFontColor(new Color(0, 82, 129));
                    empfehlung_r.setFontSize(16.);
                    empfehlungsText.setAnchor(new Rectangle(150, 370, 550, 50));

                    //Rectangle for decision --> yes
                    XSLFAutoShape rectangle_yes = entscheidungsFolie.createAutoShape();
                    rectangle_yes.setShapeType(ShapeType.RECT);
                    rectangle_yes.setAnchor(new Rectangle2D.Double(100, 460, 25, 25));
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
                    yes.setAnchor(new Rectangle(130, 460, 70, 25));

                    //Rectangle for decision --> yes, with notes
                    XSLFAutoShape rectangle_no = entscheidungsFolie.createAutoShape();
                    rectangle_no.setShapeType(ShapeType.RECT);
                    rectangle_no.setAnchor(new Rectangle2D.Double(250, 460, 25, 25));
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
                    no_note.setAnchor(new Rectangle(280, 460, 200, 25));

                    //Rectangle for decision --> no
                    XSLFAutoShape rectangle_no2 = entscheidungsFolie.createAutoShape();
                    rectangle_no2.setShapeType(ShapeType.RECT);
                    rectangle_no2.setAnchor(new Rectangle2D.Double(540, 460, 25, 25));
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
                    no.setAnchor(new Rectangle(570, 460, 70, 25));

                    //FOOTER
                    XSLFTextBox footer = entscheidungsFolie.createTextBox();
                    XSLFTextParagraph footer_p = footer.addNewTextParagraph();
                    XSLFTextRun footer_r = footer_p.addNewTextRun();
                    footer_r.setText("Projekt Portfolio Komitee (PPK) am " + parsedPPK.format(formatters));
                    footer_r.setFontColor(new Color(0, 82, 129));
                    footer_r.setFontSize(12.);
                    footer.setAnchor(new Rectangle(440, 500, 270, 50));
                }
            //</editor-fold>

            //<editor-fold desc="Fünfte Folie --> MEILENSTEIN">
            for(int c = 0; c <projekteanzahl; c++){
            XSLFSlide meilensteinFolie = generiertePowerPointPraesentation.createSlide(title);
            //----Logo anlegen
            XSLFPictureShape pic_meilensteinFolie = meilensteinFolie.createPicture(pd);
            pic_meilensteinFolie.setAnchor(new Rectangle(560, 32, 130, 20));

            //Titel erstellen
            XSLFTextShape meilensteinFolie_title = meilensteinFolie.getPlaceholder(0);
            meilensteinFolie_title.clearText();
            XSLFTextParagraph meilensteinFolie_title_p = meilensteinFolie_title.addNewTextParagraph();
            XSLFTextRun meilensteinFolie_title_r = meilensteinFolie_title_p.addNewTextRun();
            meilensteinFolie_title_r.setText(""+allProjects.get(c).getTitel());
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

            //----------------------------------------------------------------------------------------------------------MEILENSTEINPFEIL
            XSLFAutoShape meilensteinPfeil = meilensteinFolie.createAutoShape();
            meilensteinPfeil.setShapeType(ShapeType.RIGHT_ARROW);
            meilensteinPfeil.setAnchor(new java.awt.Rectangle(0, 205, 720, 100));
            meilensteinPfeil.setFillColor(new Color(195,227,248));

            List<Meilensteine> meileinensteineProProjekt = new ArrayList<>();

            for(int i = 0; i < alleMeilensteine.size();i++) {
                Meilensteine aktuellerMeilenstein = alleMeilensteine.get(i);
                if (aktuellerMeilenstein.getProjekt_id().getProjekt_id().equals(allProjects.get(c).getProjekt_id())) {
                    meileinensteineProProjekt.add(aktuellerMeilenstein);
                }
            }

            for(int i = 0; i< meileinensteineProProjekt.size(); i++) {
                Meilensteine aktuellerMeilenstein = alleMeilensteine.get(i);

                    var y = 600/meileinensteineProProjekt.size();

                    LocalDate endDatum= aktuellerMeilenstein.getEnd_datum();

                    //----------------------------------------------------------------------------------------------------------ERSTER MEILENSTEIN
                    if(aktuellerMeilenstein.getStatus() == 1) {
                        byte[] abgenommen_pic = IOUtils.toByteArray(new FileInputStream("src/main/resources/images/abgenommen.png"));
                        XSLFPictureData abgenommen_pic_pd = generiertePowerPointPraesentation.addPicture(abgenommen_pic, PictureData.PictureType.PNG);
                        XSLFPictureShape abgenommen_pic_shape = meilensteinFolie.createPicture(abgenommen_pic_pd);
                        abgenommen_pic_shape.setAnchor(new Rectangle((38 + y) * i, 180, 40, 40));

                        XSLFTextShape ersterMeilenstein = meilensteinFolie.createTextBox();
                        ersterMeilenstein.clearText();
                        XSLFTextParagraph ersterMeilenstein_p = ersterMeilenstein.addNewTextParagraph();
                        XSLFTextRun ersterMeilenstein_r = ersterMeilenstein_p.addNewTextRun();
                        ersterMeilenstein_p.setTextAlign(TextParagraph.TextAlign.CENTER);
                        ersterMeilenstein_r.setText(endDatum.format(formatters) + "\n\n" + aktuellerMeilenstein.getTitel());
                        ersterMeilenstein_r.setFontColor(Color.BLACK);
                        ersterMeilenstein_r.setFontFamily("Cavolini");
                        ersterMeilenstein_r.setFontSize(10.);

                        ersterMeilenstein.setAnchor(new Rectangle((30 + y) * i, 350, 95, 40));

                    }else {

                        byte[] nichtAbgenommen_pic = IOUtils.toByteArray(new FileInputStream("src/main/resources/images/nicht_abgenommen.png"));
                        XSLFPictureData nichtAbgenommen_pic_pd = generiertePowerPointPraesentation.addPicture(nichtAbgenommen_pic, PictureData.PictureType.PNG);
                        XSLFPictureShape nichtAbgenommen_pic_shape = meilensteinFolie.createPicture(nichtAbgenommen_pic_pd);
                        nichtAbgenommen_pic_shape.setAnchor(new Rectangle((38 + y) * i, 190, 30, 30));

                        XSLFTextShape ersterMeilenstein = meilensteinFolie.createTextBox();
                        ersterMeilenstein.clearText();
                        XSLFTextParagraph ersterMeilenstein_p = ersterMeilenstein.addNewTextParagraph();
                        XSLFTextRun ersterMeilenstein_r = ersterMeilenstein_p.addNewTextRun();
                        ersterMeilenstein_p.setTextAlign(TextParagraph.TextAlign.CENTER);
                        ersterMeilenstein_r.setText(endDatum.format(formatters) + "\n\n" + aktuellerMeilenstein.getTitel());
                        ersterMeilenstein_r.setFontColor(Color.BLACK);
                        ersterMeilenstein_r.setFontFamily("Cavolini");
                        ersterMeilenstein_r.setFontSize(10.);

                        ersterMeilenstein.setAnchor(new Rectangle((30 + y) * i, 350, 95, 40));

                    }

                }

                byte[] abgenommen_pic = IOUtils.toByteArray(new FileInputStream("src/main/resources/images/abgenommen.png"));
                XSLFPictureData abgenommen_pic_pd = generiertePowerPointPraesentation.addPicture(abgenommen_pic, PictureData.PictureType.PNG);
                XSLFPictureShape abgenommen_pic_shape = meilensteinFolie.createPicture(abgenommen_pic_pd);
                abgenommen_pic_shape.setAnchor(new Rectangle(36, 438, 42, 42));

                XSLFTextShape ersterMeilenstein_abgenommen = meilensteinFolie.createTextBox();
                ersterMeilenstein_abgenommen.clearText();
                XSLFTextParagraph ersterMeilenstein_p_abgenommen = ersterMeilenstein_abgenommen.addNewTextParagraph();
                XSLFTextRun ersterMeilenstein_r_abgenommen = ersterMeilenstein_p_abgenommen.addNewTextRun();
                ersterMeilenstein_p_abgenommen.setTextAlign(TextParagraph.TextAlign.CENTER);
                ersterMeilenstein_r_abgenommen.setText("abgenommen");
                ersterMeilenstein_r_abgenommen.setFontColor(Color.BLACK);
                ersterMeilenstein_r_abgenommen.setFontFamily("Cavolini");
                ersterMeilenstein_r_abgenommen.setFontSize(10.);
                ersterMeilenstein_r_abgenommen.setBold(true);

                ersterMeilenstein_abgenommen.setAnchor(new Rectangle(60,455,100,10));

                byte[] nichtAbgenommen_pic = IOUtils.toByteArray(new FileInputStream("src/main/resources/images/nicht_abgenommen.png"));
                XSLFPictureData nichtAbgenommen_pic_pd = generiertePowerPointPraesentation.addPicture(nichtAbgenommen_pic, PictureData.PictureType.PNG);
                XSLFPictureShape nichtAbgenommen_pic_shape = meilensteinFolie.createPicture(nichtAbgenommen_pic_pd);
                nichtAbgenommen_pic_shape.setAnchor(new Rectangle(40, 480, 30, 30));

                XSLFTextShape ersterMeilenstein_nichtAbgenommen = meilensteinFolie.createTextBox();
                ersterMeilenstein_nichtAbgenommen.clearText();
                XSLFTextParagraph ersterMeilenstein_p_nichtAbgenommen = ersterMeilenstein_nichtAbgenommen.addNewTextParagraph();
                XSLFTextRun ersterMeilenstein_r_nichtAbgenommen = ersterMeilenstein_p_nichtAbgenommen.addNewTextRun();
                ersterMeilenstein_p_nichtAbgenommen.setTextAlign(TextParagraph.TextAlign.CENTER);
                ersterMeilenstein_r_nichtAbgenommen.setText("nicht abgenommen");
                ersterMeilenstein_r_nichtAbgenommen.setFontColor(Color.BLACK);
                ersterMeilenstein_r_nichtAbgenommen.setFontFamily("Cavolini");
                ersterMeilenstein_r_nichtAbgenommen.setFontSize(10.);
                ersterMeilenstein_r_nichtAbgenommen.setBold(true);

                ersterMeilenstein_nichtAbgenommen.setAnchor(new Rectangle(60,475,100,10));

                //FOOTER
                XSLFTextBox projektuebersichtsfolie_footer = meilensteinFolie.createTextBox();
                XSLFTextParagraph projektuebersichtsfolie_footer_p = projektuebersichtsfolie_footer.addNewTextParagraph();
                XSLFTextRun projektuebersichtsfolie_footer_r = projektuebersichtsfolie_footer_p.addNewTextRun();
                projektuebersichtsfolie_footer_r.setText("Projekt Portfolio Komitee (PPK) am " +parsedPPK.format(formatters));
                projektuebersichtsfolie_footer_r.setFontColor(new Color(0, 82, 129));
                projektuebersichtsfolie_footer_r.setFontSize(12.);
                projektuebersichtsfolie_footer.setAnchor(new Rectangle(440, 500, 270, 50));
            }
            //</editor-fold>

            //<editor-fold desc="PROJEKTUEBERSICHTSFOLIE">
            for(int a = 0; a < projekteanzahl; a++) {

            //FOLIE ERSTELLEN
            XSLFSlide projektuebersichtsFolie = generiertePowerPointPraesentation.createSlide(title_content);

            //Status Circle

            //LOGO
            XSLFPictureShape pic_projektuebersichtsFolie = projektuebersichtsFolie.createPicture(pd);
            pic_projektuebersichtsFolie.setAnchor(new Rectangle(560, 32, 130, 20));

            //TITEL
            XSLFTextShape title_projektuebersichtsFolie = projektuebersichtsFolie.getPlaceholder(0);
            title_projektuebersichtsFolie.clearText();
            XSLFTextParagraph title_projektuebersichtsFolie_p1 = title_projektuebersichtsFolie.addNewTextParagraph();
            XSLFTextRun title_projektuebersichtsFolie_r1 = title_projektuebersichtsFolie_p1.addNewTextRun();
            //-------------------------------------------------------------TITEL
            title_projektuebersichtsFolie_r1.setText(""+allProjects.get(a).getTitel());
            title_projektuebersichtsFolie_r1.setFontColor(new Color(0, 82, 129));
            title_projektuebersichtsFolie_r1.setFontSize(24.0);
            title_projektuebersichtsFolie_r1.setBold(true);
            title_projektuebersichtsFolie.setAnchor(new Rectangle(20, 20, 500, 50));

            XSLFTextShape body = projektuebersichtsFolie.getPlaceholder(1);
            body.clearText();

            //TABELLE
            XSLFTable tbl = body.getSheet().createTable();
            tbl.setAnchor(new Rectangle(10, 80, 0, 0));

            int numColumns = 3;
            XSLFTableRow headerRow = tbl.addRow();
            headerRow.setHeight(20);

            //HEADER FÜR DIE TABELLE
            for (int i = 0; i < numColumns; i++) {
                XSLFTableCell th = headerRow.addCell();
                XSLFTextParagraph p = th.addNewTextParagraph();
                p.setTextAlign(TextParagraph.TextAlign.CENTER);
                XSLFTextRun r = p.addNewTextRun();
                if (i == 0) {
                    r.setText("Nenning/\n" + projekmanager.getNachname());
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

            //ZEILE = INHALT
            XSLFTableRow tr_inhalt;
            tr_inhalt=tbl.addRow();
            tr_inhalt.setHeight(240);

            //----------------------------------------------INHALT
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

            //----------------------------------------------INHALT --> STATUS
            XSLFTableCell cell_inhalt_status = tr_inhalt.addCell();
            //XSLFTextParagraph cell_inhalt_status_p = cell_inhalt_status.addNewTextParagraph();
            //XSLFTextRun cell_inhalt_status_r = cell_inhalt_status_p.addNewTextRun();

            cell_inhalt_status.setFillColor(new Color(195,227,248));
            cell_inhalt_status.setVerticalAlignment(org.apache.poi.sl.usermodel.VerticalAlignment.MIDDLE);


            cell_inhalt_status.setBorderColor(TableCell.BorderEdge.bottom,Color.white);
            cell_inhalt_status.setBorderColor(TableCell.BorderEdge.right,Color.white);
            cell_inhalt_status.setBorderWidth(TableCell.BorderEdge.right,1);

            if(allProjects.get(a).getStatus() == 1){

                int x=177;
                int y=235;
                int R=20;
                double kappa = 0.5522847498;

                java.awt.geom.GeneralPath circle = new java.awt.geom.GeneralPath();
                circle.moveTo(x, y-R); // move to A
                circle.curveTo(x+R*kappa, y-R, x+R, y-R*kappa, x+R, y); // curve to A', B', B
                circle.curveTo(x+R, y+R*kappa, x+R*kappa, y+R, x, y+R );
                circle.curveTo(x-R*kappa, y+R, x-R, y+R*kappa, x-R, y);
                circle.curveTo(x-R, y-R*kappa, x-R*kappa, y-R, x, y-R );
                circle.closePath();

                XSLFFreeformShape greenCircle = projektuebersichtsFolie.createFreeform();
                greenCircle.setPath(circle);
                greenCircle.setLineWidth(3);
                greenCircle.setFillColor(new Color(58,171,79));
                greenCircle.setLineColor(new Color(58,171,79));


            }else if(allProjects.get(a).getStatus() == 2){

                int x=177;
                int y=235;
                int R=20;
                double kappa = 0.5522847498;

                java.awt.geom.GeneralPath circle = new java.awt.geom.GeneralPath();
                circle.moveTo(x, y-R); // move to A
                circle.curveTo(x+R*kappa, y-R, x+R, y-R*kappa, x+R, y); // curve to A', B', B
                circle.curveTo(x+R, y+R*kappa, x+R*kappa, y+R, x, y+R );
                circle.curveTo(x-R*kappa, y+R, x-R, y+R*kappa, x-R, y);
                circle.curveTo(x-R, y-R*kappa, x-R*kappa, y-R, x, y-R );
                circle.closePath();

                XSLFFreeformShape yellowCircle = projektuebersichtsFolie.createFreeform();
                yellowCircle.setPath(circle);
                yellowCircle.setLineWidth(3);
                yellowCircle.setFillColor(new Color(252,235,45));
                yellowCircle.setLineColor(new Color(252,235,45));

            }else if (allProjects.get(a).getStatus() == 3){

                int x=177;
                int y=235;
                int R=20;
                double kappa = 0.5522847498;

                java.awt.geom.GeneralPath circle = new java.awt.geom.GeneralPath();
                circle.moveTo(x, y-R); // move to A
                circle.curveTo(x+R*kappa, y-R, x+R, y-R*kappa, x+R, y); // curve to A', B', B
                circle.curveTo(x+R, y+R*kappa, x+R*kappa, y+R, x, y+R );
                circle.curveTo(x-R*kappa, y+R, x-R, y+R*kappa, x-R, y);
                circle.curveTo(x-R, y-R*kappa, x-R*kappa, y-R, x, y-R );
                circle.closePath();

                XSLFFreeformShape redCircle = projektuebersichtsFolie.createFreeform();
                redCircle.setPath(circle);
                redCircle.setLineWidth(3);
                redCircle.setFillColor(new Color(204,42,37));
                redCircle.setLineColor(new Color(204,42,37));

            }

            //----------------------------------------------INHALT --> KOMMENTAR
            XSLFTableCell cell_inhalt_kommentar = tr_inhalt.addCell();
            XSLFTextParagraph cell_inhalt_kommentar_p = cell_inhalt_kommentar.addNewTextParagraph();
            XSLFTextRun cell_inhalt_kommentar_r = cell_inhalt_kommentar_p.addNewTextRun();

            cell_inhalt_kommentar.setFillColor(new Color(195,227,248));
            cell_inhalt_kommentar.setVerticalAlignment(org.apache.poi.sl.usermodel.VerticalAlignment.MIDDLE);

            cell_inhalt_kommentar.setBorderColor(TableCell.BorderEdge.bottom, Color.white);

            cell_inhalt_kommentar_r.setText(""+allProjects.get(a).getInhalt());

            //ZEILE = BUDGET
            XSLFTableRow tr_budget;
            tr_budget=tbl.addRow();
            tr_budget.setHeight(70);

            //----------------------------------------------BUDGET
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

            //----------------------------------------------BUDGET --> STATUS
            XSLFTableCell cell_budget_status = tr_budget.addCell();
            //XSLFTextParagraph cell_budget_status_p = cell_budget_status.addNewTextParagraph();
            //XSLFTextRun cell_budget_status_r = cell_budget_status_p.addNewTextRun();

            cell_budget_status.setFillColor(new Color(226,242,252));
            cell_budget_status.setVerticalAlignment(org.apache.poi.sl.usermodel.VerticalAlignment.MIDDLE);

            cell_budget_status.setBorderColor(TableCell.BorderEdge.bottom,Color.white);
            cell_budget_status.setBorderColor(TableCell.BorderEdge.right,Color.white);
            cell_budget_status.setBorderWidth(TableCell.BorderEdge.right,1);

                if(allProjects.get(a).getStatus() == 1){

                    int x=177;
                    int y=390;
                    int R=20;
                    double kappa = 0.5522847498;

                    java.awt.geom.GeneralPath circle = new java.awt.geom.GeneralPath();
                    circle.moveTo(x, y-R); // move to A
                    circle.curveTo(x+R*kappa, y-R, x+R, y-R*kappa, x+R, y); // curve to A', B', B
                    circle.curveTo(x+R, y+R*kappa, x+R*kappa, y+R, x, y+R );
                    circle.curveTo(x-R*kappa, y+R, x-R, y+R*kappa, x-R, y);
                    circle.curveTo(x-R, y-R*kappa, x-R*kappa, y-R, x, y-R );
                    circle.closePath();

                    XSLFFreeformShape greenCircle = projektuebersichtsFolie.createFreeform();
                    greenCircle.setPath(circle);
                    greenCircle.setLineWidth(3);
                    greenCircle.setFillColor(new Color(58,171,79));
                    greenCircle.setLineColor(new Color(58,171,79));


                }else if(allProjects.get(a).getStatus() == 2){

                    int x=177;
                    int y=390;
                    int R=20;
                    double kappa = 0.5522847498;

                    java.awt.geom.GeneralPath circle = new java.awt.geom.GeneralPath();
                    circle.moveTo(x, y-R); // move to A
                    circle.curveTo(x+R*kappa, y-R, x+R, y-R*kappa, x+R, y); // curve to A', B', B
                    circle.curveTo(x+R, y+R*kappa, x+R*kappa, y+R, x, y+R );
                    circle.curveTo(x-R*kappa, y+R, x-R, y+R*kappa, x-R, y);
                    circle.curveTo(x-R, y-R*kappa, x-R*kappa, y-R, x, y-R );
                    circle.closePath();

                    XSLFFreeformShape yellowCircle = projektuebersichtsFolie.createFreeform();
                    yellowCircle.setPath(circle);
                    yellowCircle.setLineWidth(3);
                    yellowCircle.setFillColor(new Color(252,235,45));
                    yellowCircle.setLineColor(new Color(252,235,45));

                }else if (allProjects.get(a).getStatus() == 3){

                    int x=177;
                    int y=390;
                    int R=20;
                    double kappa = 0.5522847498;

                    java.awt.geom.GeneralPath circle = new java.awt.geom.GeneralPath();
                    circle.moveTo(x, y-R); // move to A
                    circle.curveTo(x+R*kappa, y-R, x+R, y-R*kappa, x+R, y); // curve to A', B', B
                    circle.curveTo(x+R, y+R*kappa, x+R*kappa, y+R, x, y+R );
                    circle.curveTo(x-R*kappa, y+R, x-R, y+R*kappa, x-R, y);
                    circle.curveTo(x-R, y-R*kappa, x-R*kappa, y-R, x, y-R );
                    circle.closePath();

                    XSLFFreeformShape redCircle = projektuebersichtsFolie.createFreeform();
                    redCircle.setPath(circle);
                    redCircle.setLineWidth(3);
                    redCircle.setFillColor(new Color(204,42,37));
                    redCircle.setLineColor(new Color(204,42,37));

                }

            //----------------------------------------------BUDGET --> KOMMENTAR
            XSLFTableCell cell_budget_kommentar = tr_budget.addCell();
            XSLFTextParagraph cell_budget_kommentar_p = cell_budget_kommentar.addNewTextParagraph();
            XSLFTextRun cell_budget_kommentar_r = cell_budget_kommentar_p.addNewTextRun();

            cell_budget_kommentar.setFillColor(new Color(226,242,252));
            cell_budget_kommentar.setVerticalAlignment(org.apache.poi.sl.usermodel.VerticalAlignment.MIDDLE);

            cell_budget_kommentar.setBorderColor(TableCell.BorderEdge.bottom, Color.white);

            cell_budget_kommentar.setText(""+allProjects.get(a).getBudget()+" €");


            //TERMIN
            XSLFTableRow tr_termine;
            tr_termine=tbl.addRow();
            tr_termine.setHeight(70);

            //----------------------------------------------TERMIN
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

            //----------------------------------------------TERMIN --> STATUS
            XSLFTableCell cell_termine_status = tr_termine.addCell();
            //XSLFTextParagraph cell_termine_status_p = cell_termine_status.addNewTextParagraph();
                // XSLFTextRun cell_termine_status_r = cell_termine_status_p.addNewTextRun();


            cell_termine_status.setFillColor(new Color(195,227,248));
            cell_termine_status.setVerticalAlignment(org.apache.poi.sl.usermodel.VerticalAlignment.MIDDLE);

            cell_termine_status.setBorderColor(TableCell.BorderEdge.bottom,Color.white);
            cell_termine_status.setBorderColor(TableCell.BorderEdge.right,Color.white);
            cell_termine_status.setBorderWidth(TableCell.BorderEdge.right,1);

                if(allProjects.get(a).getStatus() == 1){

                    int x=177;
                    int y=460;
                    int R=20;
                    double kappa = 0.5522847498;

                    java.awt.geom.GeneralPath circle = new java.awt.geom.GeneralPath();
                    circle.moveTo(x, y-R); // move to A
                    circle.curveTo(x+R*kappa, y-R, x+R, y-R*kappa, x+R, y); // curve to A', B', B
                    circle.curveTo(x+R, y+R*kappa, x+R*kappa, y+R, x, y+R );
                    circle.curveTo(x-R*kappa, y+R, x-R, y+R*kappa, x-R, y);
                    circle.curveTo(x-R, y-R*kappa, x-R*kappa, y-R, x, y-R );
                    circle.closePath();

                    XSLFFreeformShape greenCircle = projektuebersichtsFolie.createFreeform();
                    greenCircle.setPath(circle);
                    greenCircle.setLineWidth(3);
                    greenCircle.setFillColor(new Color(58,171,79));
                    greenCircle.setLineColor(new Color(58,171,79));


                }else if(allProjects.get(a).getStatus() == 2){

                    int x=177;
                    int y=460;
                    int R=20;
                    double kappa = 0.5522847498;

                    java.awt.geom.GeneralPath circle = new java.awt.geom.GeneralPath();
                    circle.moveTo(x, y-R); // move to A
                    circle.curveTo(x+R*kappa, y-R, x+R, y-R*kappa, x+R, y); // curve to A', B', B
                    circle.curveTo(x+R, y+R*kappa, x+R*kappa, y+R, x, y+R );
                    circle.curveTo(x-R*kappa, y+R, x-R, y+R*kappa, x-R, y);
                    circle.curveTo(x-R, y-R*kappa, x-R*kappa, y-R, x, y-R );
                    circle.closePath();

                    XSLFFreeformShape yellowCircle = projektuebersichtsFolie.createFreeform();
                    yellowCircle.setPath(circle);
                    yellowCircle.setLineWidth(3);
                    yellowCircle.setFillColor(new Color(252,235,45));
                    yellowCircle.setLineColor(new Color(252,235,45));

                }else if (allProjects.get(a).getStatus() == 3){

                    int x=177;
                    int y=460;
                    int R=20;
                    double kappa = 0.5522847498;

                    java.awt.geom.GeneralPath circle = new java.awt.geom.GeneralPath();
                    circle.moveTo(x, y-R); // move to A
                    circle.curveTo(x+R*kappa, y-R, x+R, y-R*kappa, x+R, y); // curve to A', B', B
                    circle.curveTo(x+R, y+R*kappa, x+R*kappa, y+R, x, y+R );
                    circle.curveTo(x-R*kappa, y+R, x-R, y+R*kappa, x-R, y);
                    circle.curveTo(x-R, y-R*kappa, x-R*kappa, y-R, x, y-R );
                    circle.closePath();

                    XSLFFreeformShape redCircle = projektuebersichtsFolie.createFreeform();
                    redCircle.setPath(circle);
                    redCircle.setLineWidth(3);
                    redCircle.setFillColor(new Color(204,42,37));
                    redCircle.setLineColor(new Color(204,42,37));

                }

                //----------------------------------------------TERMIN --> KOMMENTAR
                XSLFTableCell cell_termine_kommentar = tr_termine.addCell();
                //XSLFTextParagraph cell_termine_kommentar_p = cell_termine_kommentar.addNewTextParagraph();
                //XSLFTextRun cell_termine_kommentar_r = cell_termine_kommentar_p.addNewTextRun();

                cell_termine_kommentar.setFillColor(new Color(195,227,248));
                cell_termine_kommentar.setVerticalAlignment(org.apache.poi.sl.usermodel.VerticalAlignment.MIDDLE);

                cell_termine_kommentar.setBorderColor(TableCell.BorderEdge.bottom, Color.white);

                LocalDate startDatum = allProjects.get(a).getStart_datum();
                LocalDate endDatum = allProjects.get(a).getEnd_datum();

                cell_termine_kommentar.setText(""+startDatum.format(formatters)+", "+endDatum.format(formatters));


                //FOOTER
                XSLFTextBox projektuebersichtsfolie_footer = projektuebersichtsFolie.createTextBox();
                XSLFTextParagraph projektuebersichtsfolie_footer_p = projektuebersichtsfolie_footer.addNewTextParagraph();
                XSLFTextRun projektuebersichtsfolie_footer_r = projektuebersichtsfolie_footer_p.addNewTextRun();
                projektuebersichtsfolie_footer_r.setText("Projekt Portfolio Komitee (PPK) am " +parsedPPK.format(formatters));
                projektuebersichtsfolie_footer_r.setFontColor(new Color(0, 82, 129));
                projektuebersichtsfolie_footer_r.setFontSize(12.);
                projektuebersichtsfolie_footer.setAnchor(new Rectangle(440, 500, 270, 50));
            }
            //</editor-fold>

            //<editor-fold desc="FREIE FOLIE">
            for(int i = 0; i < freieFoliens.size(); i++){

            //FOLIE ERSTELLEN
            XSLFSlide freieFolie = generiertePowerPointPraesentation.createSlide(blank);

            //LOGO
            XSLFPictureShape pic_freieFolie = freieFolie.createPicture(pd);
            pic_freieFolie.setAnchor(new Rectangle(560, 32, 130, 20));

            //TITLE
            XSLFTextShape title_freieFolie = freieFolie.createTextBox();
            XSLFTextParagraph title_freieFolie_p = title_freieFolie.addNewTextParagraph();
            XSLFTextRun title_freieFolie_r = title_freieFolie_p.addNewTextRun();
            title_freieFolie.setHorizontalCentered(true);
            //-------------------------------------------------------------TITEL
            title_freieFolie_r.setText(""+freieFoliens.get(i).getBeschreibung());
            title_freieFolie_r.setFontColor(new Color(0, 82, 129));
            title_freieFolie_r.setFontSize(24.0);
            title_freieFolie_r.setBold(true);
            title_freieFolie.setAnchor(new Rectangle(20, 20, 500, 50));

            //FREIE FOLIEN
            XSLFTextBox freierText = freieFolie.createTextBox();
            freierText.setAnchor(new Rectangle(40, 90,640, 130 ));
            XSLFTextParagraph freierTextParagraph = freierText.addNewTextParagraph();
            XSLFTextRun freierTextRun = freierTextParagraph.addNewTextRun();
            //-------------------------------------------------------------BESCHREIBUNGSTEXT
            freierTextRun.setText(""+ freieFoliens.get(i).getFreitext());
            freierTextRun.setFontColor(new Color(0, 82, 129));
            freierTextRun.setFontSize(14.0);

            //Bild

                if(freieFoliens.get(i).getUpload() != null){

                    String data = freieFoliens.get(i).getUpload();
                    String base64Image = data.split(",")[1];

                    byte[] decodedBytes = Base64.getDecoder().decode(base64Image);

                    BufferedImage img = ImageIO.read(new ByteArrayInputStream(decodedBytes));

                    // write the image to a file
                    File outputfile = new File("src/main/resources/images/image"+i+".jpeg");
                    ImageIO.write(img, "jpeg", outputfile);

                    byte[] base64IMg = IOUtils.toByteArray(new FileInputStream("src/main/resources/images/image"+i+".jpeg"));
                    XSLFPictureData base64IMg_pd = generiertePowerPointPraesentation.addPicture(base64IMg, PictureData.PictureType.JPEG);
                    XSLFPictureShape base64IMg_pic = freieFolie.createPicture(base64IMg_pd);
                    base64IMg_pic.setAnchor(new Rectangle(135,300,450,200));

                }


            //FOOTER
            XSLFTextBox projektuebersichtsfolie_footer = freieFolie.createTextBox();
            XSLFTextParagraph projektuebersichtsfolie_footer_p = projektuebersichtsfolie_footer.addNewTextParagraph();
            XSLFTextRun projektuebersichtsfolie_footer_r = projektuebersichtsfolie_footer_p.addNewTextRun();
            //-------------------------------------------------------------NÄCHSTES PPK AUS DER DATENBAK
            projektuebersichtsfolie_footer_r.setText("Projekt Portfolio Komitee (PPK) am " +parsedPPK.format(formatters));
            projektuebersichtsfolie_footer_r.setFontColor(new Color(0, 82, 129));
            projektuebersichtsfolie_footer_r.setFontSize(12.);
            projektuebersichtsfolie_footer.setAnchor(new Rectangle(440, 500, 270, 50));

            }
            //</editor-fold>

            //<editor-fold desc="ENDFOLIE">
            //FOLIE ERSTELLEN
            XSLFSlide letzteFolie = generiertePowerPointPraesentation.createSlide(blank);

            //BILD ERSTELLEN
            byte[] endSeite = IOUtils.toByteArray(new FileInputStream("src/main/resources/images/glaubandich.png"));
            XSLFPictureData endSeite_pd = generiertePowerPointPraesentation.addPicture(endSeite, PictureData.PictureType.PNG);
            XSLFPictureShape endSeite_pic = letzteFolie.createPicture(endSeite_pd);
            endSeite_pic.setAnchor(new Rectangle(0,0,720,540));
            //</editor-fold>

            //----------------------------------------------------------------------------------------------------------Output von der PowerPoint
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






