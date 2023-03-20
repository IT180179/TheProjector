package com.example.workloads.BeschlussFolien;

import com.example.workloads.PPK_Projekte.PPK_Projekte;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
public class BeschlussFolien extends PanacheEntityBase {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long beschlussfolien_id;
    private String titel;
    private String untertitel;
    private int entscheidung;
    private String anmerkung;
    private String freitext;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "PPK_PROJEKTE_ID_PPK_ID_PPK_ID", referencedColumnName = "PPK_ID_PPK_ID"),
            @JoinColumn(name = "PPK_PROJEKTE_ID_PROJEKTE_ID_PROJEKT_ID", referencedColumnName = "PROJEKTE_ID_PROJEKT_ID")
    })
    private PPK_Projekte ppk_projekte_id;

    public PPK_Projekte getPpk_projekte_id() {
        return ppk_projekte_id;
    }

    public void setPpk_projekte_id(PPK_Projekte ppk_projekte_id) {
        this.ppk_projekte_id = ppk_projekte_id;
    }

    public Long getBeschlussfolien_id() {
        return beschlussfolien_id;
    }

    public void setBeschlussfolien_id(Long beschlussfolien_id) {
        this.beschlussfolien_id = beschlussfolien_id;
    }

    public int getEntscheidung() {
        return entscheidung;
    }

    public void setEntscheidung(int entscheidung) {
        this.entscheidung = entscheidung;
    }

    public String getAnmerkung() {
        return anmerkung;
    }

    public void setAnmerkung(String anmerkung) {
        this.anmerkung = anmerkung;
    }

    public String getFreitext() {
        return freitext;
    }

    public void setFreitext(String freitext) {
        this.freitext = freitext;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getUntertitel() {
        return untertitel;
    }

    public void setUntertitel(String untertitel) {
        this.untertitel = untertitel;
    }
}