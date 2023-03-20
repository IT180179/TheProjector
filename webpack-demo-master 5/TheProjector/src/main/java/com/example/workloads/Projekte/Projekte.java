package com.example.workloads.Projekte;

import com.example.workloads.Kategorien.Kategorien;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Projekte extends PanacheEntityBase {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long projekt_id;
    private String titel;
    private String inhalt;
    private String ziel;
    private int status;
    private int budget;
    private LocalDate start_datum;
    private LocalDate end_datum;

    @ManyToOne
    @JoinColumn(name = "kategorie_id_kategorien_id")
    private Kategorien kategorie_id;

    public Kategorien getKategorie_id() {
        return kategorie_id;
    }

    public void setKategorie_id(Kategorien kategorie_id) {
        this.kategorie_id = kategorie_id;
    }

    public Long getProjekt_id() {
        return projekt_id;
    }

    public void setProjekt_id(Long projekt_id) {
        this.projekt_id = projekt_id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getInhalt() {
        return inhalt;
    }

    public void setInhalt(String inhalt) {
        this.inhalt = inhalt;
    }

    public String getZiel() {
        return ziel;
    }

    public void setZiel(String ziel) {
        this.ziel = ziel;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public LocalDate getStart_datum() {
        return start_datum;
    }

    public void setStart_datum(LocalDate start_datum) {
        this.start_datum = start_datum;
    }

    public LocalDate getEnd_datum() {
        return end_datum;
    }

    public void setEnd_datum(LocalDate end_datum) {
        this.end_datum = end_datum;
    }
}