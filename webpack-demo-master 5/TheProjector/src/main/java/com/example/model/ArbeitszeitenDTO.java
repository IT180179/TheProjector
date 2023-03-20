package com.example.model;

public class ArbeitszeitenDTO {

    private Long person_id;
    private String vorname;
    private String nachname;
    private Long projekt_id;
    private Long rolle_id;
    private Double arbeitszeit;

    public ArbeitszeitenDTO() {

    }

    public ArbeitszeitenDTO(Long person_id, String vorname, String nachname, Long projekt_id, Long rolle_id, Double arbeitszeit) {
        this.person_id = person_id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.projekt_id = projekt_id;
        this.rolle_id = rolle_id;
        this.arbeitszeit = arbeitszeit;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public Long getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Long person_id) {
        this.person_id = person_id;
    }

    public Long getProjekt_id() {
        return projekt_id;
    }

    public void setProjekt_id(Long projekt_id) {
        this.projekt_id = projekt_id;
    }

    public Long getRolle_id() {
        return rolle_id;
    }

    public void setRolle_id(Long rolle_id) {
        this.rolle_id = rolle_id;
    }

    public Double getArbeitszeit() {
        return arbeitszeit;
    }

    public void setArbeitszeit(Double arbeitszeit) {
        this.arbeitszeit = arbeitszeit;
    }
}