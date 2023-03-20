package com.example.workloads.Meilensteine_Daten;

import com.example.workloads.Meilensteine.Meilensteine;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Meilensteine_Daten extends PanacheEntityBase {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long meilensteine_daten_id;
    private int hoehe;
    private int breite;
    private int x;
    private int y;

    public Long getMeilensteine_daten_id() {
        return meilensteine_daten_id;
    }

    public void setMeilensteine_daten_id(Long meilensteine_daten_id) {
        this.meilensteine_daten_id = meilensteine_daten_id;
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