package com.example.workloads.Personen;

import com.example.workloads.Abteilungen.Abteilungen;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import javax.resource.spi.SecurityPermission;

@Entity
public class Personen extends PanacheEntityBase {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long personen_id;
    private String vorname;
    private String nachname;
    private String username;
    private String passwort;
    private int rechte;

    @ManyToOne
    @JoinColumn(name = "abteilungs_id_abteilungs_id")
    private Abteilungen abteilungs_id;

    public Abteilungen getAbteilungs_id() {
        return abteilungs_id;
    }

    public void setAbteilungs_id(Abteilungen abteilungs_id) {
        this.abteilungs_id = abteilungs_id;
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
}