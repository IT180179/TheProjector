package com.example.workloads.Softwareanforderungen;

import com.example.workloads.Projekte.Projekte;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
public class Softwareanforderungen extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long softwareanforderungen_id;
    private int status;
    private String beschreibung;
    private String anforderungsprozess;
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "projekte_id_projekt_id")
    private Projekte projekte_id;

    public Projekte getProjekte_id() {
        return projekte_id;
    }

    public void setProjekte_id(Projekte projekte_id) {
        this.projekte_id = projekte_id;
    }

    public Long getSoftwareanforderungen_id() {
        return softwareanforderungen_id;
    }

    public void setSoftwareanforderungen_id(Long softwareanforderungen_id) {
        this.softwareanforderungen_id = softwareanforderungen_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getAnforderungsprozess() {
        return anforderungsprozess;
    }

    public void setAnforderungsprozess(String anforderungsprozess) {
        this.anforderungsprozess = anforderungsprozess;
    }
}