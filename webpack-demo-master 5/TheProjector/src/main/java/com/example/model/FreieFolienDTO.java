package com.example.model;

import com.example.workloads.PPK_Projekte.PPK_Projekte;

import javax.persistence.Column;

public class FreieFolienDTO {

    private String titel;
    private String untertitel;
    private String beschreibung;
    private String freitext;
    private String bild;
    private PPK_Projekte ppk_projekte_id;

    public FreieFolienDTO() {
    }

    public FreieFolienDTO(String titel, String untertitel, String beschreibung, String freitext, String bild, PPK_Projekte ppk_projekte_id) {
        this.titel = titel;
        this.untertitel = untertitel;
        this.beschreibung = beschreibung;
        this.freitext = freitext;
        this.bild = bild;
        this.ppk_projekte_id = ppk_projekte_id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getUntertitel() {
        return untertitel;
    }

    public void setUntertitel(String untertitel) {
        this.untertitel = untertitel;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getFreitext() {
        return freitext;
    }

    public void setFreitext(String freitext) {
        this.freitext = freitext;
    }

    public String getBild() {
        return bild;
    }

    public void setBild(String bild) {
        this.bild = bild;
    }

    public PPK_Projekte getPpk_projekte_id() {
        return ppk_projekte_id;
    }

    public void setPpk_projekte_id(PPK_Projekte ppk_projekte_id) {
        this.ppk_projekte_id = ppk_projekte_id;
    }
}