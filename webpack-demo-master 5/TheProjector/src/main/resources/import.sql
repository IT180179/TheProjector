insert into Bereiche (name)
values ('Strat. RM & ORG IT'),
       ('Retail'),
       ('Corporate'),
       ('Treasurg'),
       ('KuV'),
       ('Finanzen & Controlling'),
       ('Immobilien & Betriebsmanagement'),
       ('Kredit RM'),
       ('Datenqualität & Digitalisierung'),
       ('HR'),
       ('Marketing & Kommunikation'),
       ('Compl./Recht/Revision');
insert into Abteilungen (name, bereichs_id_bereich_id)
values ('Organisation', 1),
       ('Strateg. Risikomanagement', 1),
       ('IT', 1),
       ('Retail', 2),
       ('Corporate', 3),
       ('Treasurg', 4),
       ('Analytik & Kundenwissen', 5),
       ('Channelmanagement', 5),
       ('Kundenlösungen & Produktumsetzung', 5),
       ('Segmentmanagement', 5),
       ('Spezialfinanzierung', 5),
       ('Vertriebscontrolling', 5),
       ('Finanzen & Controlling', 6),
       ('Immobilien & Betriebsmanagement', 7),
       ('Kredit RM', 8),
       ('Datenqualität & Digitalisierung', 9),
       ('HR', 10),
       ('Marketing & Kommunikation', 11),
       ('Compl./Recht/Revision', 12);
insert into Rollen (name)
values ('Projektmanager'),
       ('Fachkoordinator'),
       ('Mitarbeiter'),
       ('Stakeholder'),
       ('Auftraggeber');
insert into Personen (vorname, nachname, username, passwort, rechte, abteilungs_id_abteilungs_id)
values ('Simon', 'Walchshofer', 'SimonW', 'slGwxOIh+lu8gyRXh5E8Y7+iw7AhbEBog5/Jb7E73g057OIjifwckvcflLRuS7mQ', 1, 3),
       ('Fabian', 'Huemer-Huemer', 'FabianH', 'slGwxOIh+lu8gyRXh5E8Y7+iw7AhbEBog5/Jb7E73g057OIjifwckvcflLRuS7mQ', 2, 3),
       ('Ana', 'Bikic', 'AnaB', 'slGwxOIh+lu8gyRXh5E8Y7+iw7AhbEBog5/Jb7E73g057OIjifwckvcflLRuS7mQ', 2, 3),
       ('Annika', 'Cagitz', 'AnnikaC', 'slGwxOIh+lu8gyRXh5E8Y7+iw7AhbEBog5/Jb7E73g057OIjifwckvcflLRuS7mQ', 2, 3),
       ('David', 'Ursprung', 'DavidU', 'slGwxOIh+lu8gyRXh5E8Y7+iw7AhbEBog5/Jb7E73g057OIjifwckvcflLRuS7mQ', 2, 3);
insert into Kategorien (name)
values ('Softwareprojekt'),
       ('Organisationsprojekt');
insert into Projekte (titel, inhalt, ziel, status, budget, start_datum, end_datum, kategorie_id_kategorien_id)
values ('The Projector', 'The Projector ist ein Tool für die Projektverwaltung und Generierung einer Powerpoint', 'Einfach und Übersichtliche Projektverwaltung', 1, 0, '2022-07-04', '2023-03-29', 1),
       ('Aurora', 'Aurora ein Projekt der Sparkasse OÖ ', 'Aurora ein Projekt der Sparkasse OÖ', 1, 0, '2023-07-27', '2023-01-01', 1),
       ('Digitaler Vorstandsantrag', 'Digitaler Vorstandsantrag ein Projekt der Sparkasse OÖ ', 'Digitaler Vorstandsantrag ein Projekt der Sparkasse OÖ', 1, 0, '2023-07-27', '2023-01-01', 1),
       ('UnserKundenHub', 'UnserKundenHub ein Projekt der Sparkasse OÖ ', 'UnserKundenHub ein Projekt der Sparkasse OÖ', 1, 0, '2023-07-27', '2023-01-01', 1),
       ('Digitaler Tresor', 'Digitaler Tresor ein Projekt der Sparkasse OÖ ', 'Digitaler Tresor ein Projekt der Sparkasse OÖ ', 1, 75000, '2023-07-27', '2023-01-01', 1),
       ('Einkaufssoftware', 'Einkaufssoftware ein Projekt der Sparkasse OÖ', 'Einkaufssoftware ein Projekt der Sparkasse OÖ', 1, 2000, '2023-07-27', '2023-01-01', 1),
       ('LeoGreen', 'Quiz über die Natur', 'Menschen über das Klima und die Natur zu infomieren', 1, 10000, '2022-07-04', '2023-03-29', 1),
       ('PicknGo', 'Eine Anwendung zum Essenbestellen', 'Leichtes Essen bestellen ermöglichen', 1, 100, '2023-07-27', '2023-01-01', 1),
       ('Der Cagitzer', 'Reservierungssystem für Gasthäuser', 'Leichtes Verwalten und Reservieren der Reservierungen', 1, 10000, '2023-07-27', '2023-01-01', 1),
       ('Desking', 'Zeiterfassungs-Tool', 'Ein Tool zur Zeiterfassung', 1, 200, '2022-07-04', '2023-03-29', 1),
       ('LeoTools', 'Online Equimentverleih', 'Leichtes und fehlerfreier Verleih von Geräte', 1, 10, '2023-07-27', '2023-01-01', 1);
insert into Einsaetze (personen_id_personen_id, projekte_id_projekt_id, rollen_id_rollen_id, arbeitsstunden)
values (3, 1, 3, 38.5),
       (4, 1, 3, 38.5),
       (5, 1, 3, 38.5),
       (1, 1, 1, 0),
       (1, 1, 2, 0),
       (3, 2, 3, 38.5),
       (4, 2, 3, 38.5),
       (5, 2, 3, 38.5),
       (1, 2, 1, 0),
       (1, 2, 2, 0),
       (3, 3, 3, 38.5),
       (4, 3, 3, 38.5),
       (5, 3, 3, 38.5),
       (1, 3, 1, 0),
       (1, 3, 2, 0);
insert into Meilensteine (titel, beschreibung, status, start_datum, end_datum, projekt_id_projekt_id)
values ('Projektstart erfolgt', 'Projektstart erfolgt', 1, '2022-07-04', '2022-07-04', 1),
       ('Pflichtenheft fertig', 'Pflichtenheft fertig', 2, '2022-07-04', '2022-07-15', 1),
       ('Backend mit DB', 'Backend mit DB', 1, '2022-07-15', '2022-07-04', 1),
       ('Deployment in Testumgebung', 'Deployment in Testumgebung', 1, '2022-07-04', '2022-12-23', 1),
       ('Testen und Qualitätssicherung abgeschlossen', 'Testen und Qualitätssicherung abgeschlossen', 1, '2022-07-04', '2023-02-24', 1),
       ('Projektstart erfolgt', 'Projektstart erfolgt', 1, '2022-07-04', '2022-07-04', 2),
       ('Projektende', 'Projektende', 1, '2022-07-04', '2022-08-04', 2),
       ('Projektstart erfolgt', 'Projektstart erfolgt', 1, '2022-07-04', '2022-07-04', 3),
       ('Projektende', 'Projektende', 1, '2022-07-04', '2022-08-04', 3),
       ('Projektstart erfolgt', 'Projektstart erfolgt', 1, '2022-07-04', '2022-07-04', 4),
       ('Pflichtenheft fertig', 'Pflichtenheft fertig', 2, '2022-07-04', '2022-07-15', 4),
       ('Backend mit DB', 'Backend mit DB', 1, '2022-07-15', '2022-07-04', 4),
       ('Deployment in Testumgebung', 'Deployment in Testumgebung', 1, '2022-07-04', '2022-12-23', 4),
       ('Testen und Qualitätssicherung abgeschlossen', 'Testen und Qualitätssicherung abgeschlossen', 1, '2022-07-04', '2023-02-24', 5),
       ('Projektstart erfolgt', 'Projektstart erfolgt', 1, '2022-07-04', '2022-07-04', 5),
       ('Projektende', 'Projektende', 1, '2022-07-04', '2022-08-04', 5),
       ('Projektstart erfolgt', 'Projektstart erfolgt', 1, '2022-07-04', '2022-07-04', 6),
       ('Projektende', 'Projektende', 1, '2022-07-04', '2022-08-04', 6),
       ('Projektstart erfolgt', 'Projektstart erfolgt', 1, '2022-07-04', '2022-07-04', 7),
       ('Projektende', 'Projektende', 1, '2022-07-04', '2022-08-04', 7),
       ('Projektstart erfolgt', 'Projektstart erfolgt', 1, '2022-07-04', '2022-07-04', 8),
       ('Pflichtenheft fertig', 'Pflichtenheft fertig', 2, '2022-07-04', '2022-07-15', 8),
       ('Backend mit DB', 'Backend mit DB', 1, '2022-07-15', '2022-07-04', 8),
       ('Deployment in Testumgebung', 'Deployment in Testumgebung', 1, '2022-07-04', '2022-12-23', 8),
       ('Testen und Qualitätssicherung abgeschlossen', 'Testen und Qualitätssicherung abgeschlossen', 1, '2022-07-04', '2023-02-24', 9),
       ('Projektstart erfolgt', 'Projektstart erfolgt', 1, '2022-07-04', '2022-07-04', 9),
       ('Projektende', 'Projektende', 1, '2022-07-04', '2022-08-04', 9);
insert into Meilenstein_Histories (meilenstein_id_meilensteine_id, aenderung, alter_status, datum, end_datum)
values (2, 'End-Datum geändert.', 1, '2022-07-04', '2022-07-15'),
       (2, 'End-Datum geändert.', 1, '2022-07-04', '2022-07-20');
insert into PPK (datum)
values ('2023-10-10'),
       ('2023-8-10'),
       ('2023-6-13');

insert into Gaeste (ppk_id_ppk_id, name)
values (1, 'Bikic'),
       (1, 'Cagitz'),
       (2, 'Ursprung'),
        (2, 'Maar'),
       (2, 'Halilovic');
insert into PPK_Projekte (ppk_id_ppk_id, projekte_id_projekt_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (2, 2),
       (2, 3),
       (3, 1),
       (4, 2),
       (5, 1),
       (5, 3);
insert into Ressourcen (ppk_projekte_id_ppk_id_ppk_id, ppk_projekte_id_projekte_id_projekt_id, personen_id_personen_id, aufwand)
values (1, 1, 3, 38.5),
       (1, 1, 4, 38.5),
       (1, 1, 5, 38.5),
       (1, 2, 3, 45);
insert into Beschlussfolien (entscheidung, anmerkung, freitext, ppk_projekte_id_ppk_id_ppk_id, ppk_projekte_id_projekte_id_projekt_id)
values (1, 'Das PPK empfiehlt die oben beschriebene Vorgehensweise.', 'Um die Inhalte aus dem sGruppen-Projekt Digitized Lending in der Spk OÖ bestmöglich verankern und umsetzen zu können soll es auch in der Sparkasse OÖ dazu ein inhouse-Projekt geben (Projektmanager Brandstetter/Fachkoordinatorin Sandra Wallner).
        Aktuell befindet sich das Projekt in der „Pipeline“ und damit die Arbeit beginnen kann sollte das „Go“ für das Projekt erfolgen.
        Aufgrund einer Erkrankung könnten noch nicht alle Details/Inhalte fixiert werden.
        ', 1, 4),
       (0, 'Anmerkung', 'Freitext', 1, 2);
insert into FreieFolien (beschreibung, freitext, upload, ppk_projekte_id_ppk_id_ppk_id, ppk_projekte_id_projekte_id_projekt_id)
values ('Beschreibung', 'Freitext', null, 1, 1),
       ('Beschreibung', 'Freitext', null, 1, 2);
insert into PPKategorie (name)
values ('Vorstand'),
       ('Abteilungsleiter');
insert into Meilensteine_Daten (meilensteine_daten_id, hoehe, breite, x, y)
values (1, 100, 100, 10, 10),
       (2, 100, 100, 10, 10),
       (3, 100, 100, 10, 10),
       (4, 100, 100, 10, 10),
       (5, 100, 100, 10, 10);
insert into Tabellen_Daten (tabellen_daten_id, hintergrundfarbe, hoehe, breite, x, y)
values (1, '#12345', 100, 100, 10, 10),
       (2, '#12345', 100, 100, 10, 10),
       (3, '#12345', 100, 100, 10, 10),
       (4, '#12345', 100, 100, 10, 10),
       (5, '#12345', 100, 100, 10, 10);
insert into Logos_Daten (logos_daten_id, pfad, hoehe, breite, x, y)
values (1, 'Pfad', 100, 100, 10, 10),
       (2, 'Pfad', 100, 100, 10, 10),
       (3, 'Pfad', 100, 100, 10, 10),
       (4, 'Pfad', 100, 100, 10, 10),
       (5, 'Pfad', 100, 100, 10, 10);
insert into Titel_Daten (titel_daten_id, schriftgroesse, schriftart, farbe, bold, hoehe, breite, x, y)
values (1, 10, 'Font', '#12345', true, 100, 100, 10, 10),
       (2, 10, 'Font', '#12345', true, 100, 100, 10, 10),
       (3, 10, 'Font', '#12345', true, 100, 100, 10, 10),
       (4, 10, 'Font', '#12345', true, 100, 100, 10, 10),
       (5, 10, 'Font', '#12345', true, 100, 100, 10, 10);
insert into Texte_Daten (texte_daten_id, schriftgroesse, schriftart, farbe, bold, hoehe, breite, x, y)
values (1, 10, 'Font', '#12345', false, 100, 100, 10, 10),
       (2, 10, 'Font', '#12345', false, 100, 100, 10, 10),
       (3, 10, 'Font', '#12345', false, 100, 100, 10, 10),
       (4, 10, 'Font', '#12345', false, 100, 100, 10, 10),
       (5, 10, 'Font', '#12345', false, 100, 100, 10, 10);
insert into Untertitel_Daten (untertitel_daten_id, schriftgroesse, schriftart, farbe, bold, hoehe, breite, x, y)
values (1, 10, 'Font', '#12345', false, 100, 100, 10, 10),
       (2, 10, 'Font', '#12345', false, 100, 100, 10, 10),
       (3, 10, 'Font', '#12345', false, 100, 100, 10, 10),
       (4, 10, 'Font', '#12345', false, 100, 100, 10, 10),
       (5, 10, 'Font', '#12345', false, 100, 100, 10, 10);
insert into Folien (folien_id, titel_daten_id_titel_daten_id, untertitel_daten_id_untertitel_daten_id, texte_daten_id_texte_daten_id, logos_daten_id_logos_daten_id, meilensteine_daten_id_meilensteine_daten_id, tabellen_daten_id_tabellen_daten_id, ppkategorie_id_pp_kategorie, ppk_projekte_id_ppk_id_ppk_id, ppk_projekte_id_projekte_id_projekt_id)
values (1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
       (2, 2, 2, 2, 2, 2, 2, 1, 1, 1),
       (3, 3, 3, 3, 3, 3, 3, 1, 1, 1),
       (4, 4, 4, 4, 4, 4, 4, 1, 1, 1),
       (5, 5, 5, 5, 5, 5, 5, 1, 1, 1),
       (6, 1, 1, 1, 1, 1, 1, 2, 1, 1),
       (7, 2, 2, 2, 2, 2, 2, 2, 1, 1),
       (8, 3, 3, 3, 3, 3, 3, 2, 1, 1),
       (9, 4, 4, 4, 4, 4, 4, 2, 1, 1),
       (10, 5, 5, 5, 5, 5, 5, 2, 1, 1);
insert into Phasen (titel)
values ('Anforderungserhebung'),
       ('Softwarelösung evaluieren'),
       ('RFI erstellen'),
       ('Produktpräsentation'),
       ('Softwarelösung abstimmen'),
       ('ISD erstellen'),
       ('Software implementieren');
insert into Softwareanforderungen (status, beschreibung, anforderungsprozess, projekte_id_projekt_id)
values (1, 'The Projector', 'Text', 1),
       (1, 'Aurora', 'Text', 2),
       (1, 'Einkaufssoftware', 'Text', 3),
       (1, 'Die Software dient zur digitalen Übermittlung von Vorstandsanträge. Die Vorstandsanträge sollen weiters in einer digitalen Form durch den Vorstand bearbeitet werden können.', 'Umsetzung via PowerApps / PowerAutomate wurde durchgeführt. Interne Tests des Entwurfs wurden durchgeführt und dabei aufgetretene Fehler behoben.
        Aufgrund der Reevaluierung der Vertraulichkeitsstufen der Datenklassifikation muss die Datenablage gesondert betrachtet werden.', 4),
       (1, 'Es wurde eine Software zur Unterstützung bei der internen Kundenfinden angefordert, welche mittels Gamification die entsprechenden Mitarbeiter motivieren soll.
        ', 'Weiterentwicklung des erstellten Anforderungskatalogs mittels einer Workshopreihe.', 5);
insert into Phasen_Projekt (status, phasen_id_phasen_id, softwareanforderungen_id_softwareanforderungen_id)
values (0, 1, 1),
       (0, 2, 1),
       (1, 3, 1),
       (2, 4, 1),
       (0, 5, 1),
       (0, 6, 1),
       (0, 7, 1),
       (0, 1, 2),
       (1, 2, 2),
       (2, 3, 2),
       (0, 4, 2),
       (0, 5, 2),
       (0, 6, 2),
       (0, 7, 2),
       (0, 1, 3),
       (1, 2, 3),
       (0, 3, 3),
       (0, 4, 3),
       (1, 5, 3),
       (2, 6, 3),
       (0, 7, 3);
insert into Arbeitszeiten (arbeitszeit, date, EINSAETZE_ID_PERSONEN_ID_PERSONEN_ID, EINSAETZE_ID_PROJEKTE_ID_PROJEKT_ID, EINSAETZE_ID_ROLLEN_ID_ROLLEN_ID)
values (10, '2023-03-10', 1, 1, 1),
       (10, '2023-03-11', 1, 1, 2),
       (10, '2023-03-10', 3, 1, 3),
       (15, '2023-03-11', 3, 1, 3),
       (10, '2023-03-10', 4, 1, 3),
       (15, '2023-03-11', 4, 1, 3),
       (10, '2023-03-10', 5, 1, 3),
       (15, '2023-03-11', 5, 1, 3),
       (10, '2023-03-10', 1, 2, 1),
       (10, '2023-03-11', 1, 2, 2),
       (15, '2023-03-10', 3, 2, 3),
       (10, '2023-03-11', 3, 2, 3),
       (10, '2023-03-10', 4, 2, 3),
       (15, '2023-03-11', 4, 2, 3),
       (10, '2023-03-10', 5, 2, 3),
       (15, '2023-03-11', 5, 2, 3),
       (10, '2023-03-10', 1, 3, 1),
       (10, '2023-03-11', 1, 3, 2),
       (15, '2023-03-10', 3, 3, 3),
       (10, '2023-03-11', 3, 3, 3),
       (10, '2023-03-10', 4, 3, 3),
       (15, '2023-03-11', 4, 3, 3),
       (10, '2023-03-10', 5, 3, 3),
       (15, '2023-03-11', 5, 3, 3);