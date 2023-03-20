package com.example.workloads.Arbeitszeiten;

import com.example.workloads.Einsaetze.Einsaetze;
import com.example.workloads.Personen.Personen;
import com.example.workloads.Projekte.Projekte;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Arbeitszeiten extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long arbeitszeiten_id;
    private Double arbeitszeit;
    private String date;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "EINSAETZE_ID_PERSONEN_ID_PERSONEN_ID", referencedColumnName = "PERSONEN_ID_PERSONEN_ID"),
            @JoinColumn(name = "EINSAETZE_ID_PROJEKTE_ID_PROJEKT_ID", referencedColumnName = "PROJEKTE_ID_PROJEKT_ID"),
            @JoinColumn(name = "EINSAETZE_ID_ROLLEN_ID_ROLLEN_ID", referencedColumnName = "ROLLEN_ID_ROLLEN_ID")
    })
    private Einsaetze einsaetze_id;

    public Einsaetze getEinsaetze_id() {
        return einsaetze_id;
    }

    public void setEinsaetze_id(Einsaetze einsaetze_id) {
        this.einsaetze_id = einsaetze_id;
    }

    public Long getArbeitszeiten_id() {
        return arbeitszeiten_id;
    }

    public void setArbeitszeiten_id(Long arbeitszeiten_id) {
        this.arbeitszeiten_id = arbeitszeiten_id;
    }

    public Double getArbeitszeit() {
        return arbeitszeit;
    }

    public void setArbeitszeit(Double arbeitszeit) {
        this.arbeitszeit = arbeitszeit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}