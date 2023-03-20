package com.example.model;

import com.example.workloads.Abteilungen.Abteilungen;
import com.example.workloads.Rollen.Rollen;

public class PersonenAufwandDTO {

    private Long personen_id;
    private String vorname;
    private String nachname;
    private String username;
    private String passwort;
    private int rechte;
    private Abteilungen abteilung;
    private Long abteilungs_id;
    private Double arbeitsstunden;
    private Rollen rollen_id;

    public PersonenAufwandDTO(Long personen_id, String vorname, String nachname, String username, String passwort, int rechte, Double arbeitsstunden, Abteilungen abteilung, Rollen rollen_id) {
        this.personen_id = personen_id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.username = username;
        this.passwort = passwort;
        this.rechte = rechte;
        this.arbeitsstunden = arbeitsstunden;
        this.abteilung = abteilung;
        this.rollen_id = rollen_id;
    }

    public Rollen getRollen_id() {
        return rollen_id;
    }

    public void setRollen_id(Rollen rollen_id) {
        this.rollen_id = rollen_id;
    }

    public Long getAbteilungs_id() {
        return abteilungs_id;
    }

    public void setAbteilungs_id(Long abteilungs_id) {
        this.abteilungs_id = abteilungs_id;
    }

    public Abteilungen getAbteilung() {
        return abteilung;
    }

    public void setAbteilung(Abteilungen abteilung) {
        this.abteilung = abteilung;
    }

    public Long getPersonen_id() {
        return personen_id;
    }

    public void setPersonen_id(Long personen_id) {
        this.personen_id = personen_id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public int getRechte() {
        return rechte;
    }

    public void setRechte(int rechte) {
        this.rechte = rechte;
    }

    public Double getArbeitsstunden() {
        return arbeitsstunden;
    }

    public void setArbeitsstunden(Double arbeitsstunden) {
        this.arbeitsstunden = arbeitsstunden;
    }
}