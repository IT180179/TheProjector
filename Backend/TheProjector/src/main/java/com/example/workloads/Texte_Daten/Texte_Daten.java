package com.example.workloads.Texte_Daten;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
public class Texte_Daten extends PanacheEntityBase {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long texte_daten_id;
    private int schriftgroesse;
    private String schriftart;
    private String farbe;
    private Boolean bold;
    private int hoehe;
    private int breite;
    private int x;
    private int y;

    public Long getTexte_daten_id() {
        return texte_daten_id;
    }

    public void setTexte_daten_id(Long texte_daten_id) {
        this.texte_daten_id = texte_daten_id;
    }

    public int getSchriftgroesse() {
        return schriftgroesse;
    }

    public void setSchriftgroesse(int schriftgroesse) {
        this.schriftgroesse = schriftgroesse;
    }

    public String getSchriftart() {
        return schriftart;
    }

    public void setSchriftart(String schriftart) {
        this.schriftart = schriftart;
    }

    public String getFarbe() {
        return farbe;
    }

    public void setFarbe(String farbe) {
        this.farbe = farbe;
    }

    public Boolean getBold() {
        return bold;
    }

    public void setBold(Boolean bold) {
        this.bold = bold;
    }

    public int getHoehe() {
        return hoehe;
    }

    public void setHoehe(int hoehe) {
        this.hoehe = hoehe;
    }

    public int getBreite() {
        return breite;
    }

    public void setBreite(int breite) {
        this.breite = breite;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}