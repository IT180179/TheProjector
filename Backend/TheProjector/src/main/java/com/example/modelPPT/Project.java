package com.example.modelPPT;

public class Project {
    public String budget;
    public String end_datum;
    public String inhalt;
    public Kategorie kategorie;

    public int projekt_id;
    public String start_datum;

    public int status;
    public String title;
    public String ziel;

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getEnd_datum() {
        return end_datum;
    }

    public void setEnd_datum(String end_datum) {
        this.end_datum = end_datum;
    }

    public String getInhalt() {
        return inhalt;
    }

    public void setInhalt(String inhalt) {
        this.inhalt = inhalt;
    }

    public Kategorie getKategorie() {
        return kategorie;
    }

    public void setKategorie(Kategorie kategorie) {
        this.kategorie = kategorie;
    }

    public int getProjekt_id() {
        return projekt_id;
    }

    public void setProjekt_id(int projekt_id) {
        this.projekt_id = projekt_id;
    }

    public String getStart_datum() {
        return start_datum;
    }

    public void setStart_datum(String start_datum) {
        this.start_datum = start_datum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getZiel() {
        return ziel;
    }

    public void setZiel(String ziel) {
        this.ziel = ziel;
    }

}




