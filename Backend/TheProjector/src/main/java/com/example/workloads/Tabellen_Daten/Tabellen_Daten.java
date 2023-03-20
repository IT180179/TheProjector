package com.example.workloads.Tabellen_Daten;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tabellen_Daten extends PanacheEntityBase {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long tabellen_daten_id;
    private String hintergrundfarbe;
    private int hoehe;
    private int breite;
    private int x;
    private int y;

    public Long getTabellen_daten_id() {
        return tabellen_daten_id;
    }

    public void setTabellen_daten_id(Long tabellen_daten_id) {
        this.tabellen_daten_id = tabellen_daten_id;
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