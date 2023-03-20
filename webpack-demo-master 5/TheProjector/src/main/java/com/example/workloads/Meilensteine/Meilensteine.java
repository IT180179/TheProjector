package com.example.workloads.Meilensteine;

import com.example.workloads.Projekte.Projekte;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Meilensteine extends PanacheEntityBase {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long meilensteine_id;
    private String titel;
    private String beschreibung;
    private int status;
    private LocalDate start_datum;
    private LocalDate end_datum;

    @ManyToOne
    @JoinColumn(name = "projekt_id_projekt_id")
    private Projekte projekt_id;

    public Projekte getProjekt_id() {
        return projekt_id;
    }

    public void setProjekt_id(Projekte projekt_id) {
        this.projekt_id = projekt_id;
    }

    public Long getMeilensteine_id() {
        return meilensteine_id;
    }

    public void setMeilensteine_id(Long meilensteine_id) {
        this.meilensteine_id = meilensteine_id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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