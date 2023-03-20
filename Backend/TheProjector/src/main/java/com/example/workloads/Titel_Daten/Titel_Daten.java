package com.example.workloads.Titel_Daten;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Titel_Daten extends PanacheEntityBase {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long titel_daten_id;
    private int schriftgroesse;
    private String schriftart;
    private String farbe;
    private Boolean bold;
    private int hoehe;
    private int breite;
    private int x;
    private int y;

    public Long getTitel_daten_id() {
        return titel_daten_id;
    }

    public void setTitel_daten_id(Long titel_daten_id) {
        this.titel_daten_id = titel_daten_id;
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