package com.example.workloads.FreieFolien;

import com.example.workloads.PPK_Projekte.PPK_Projekte;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
public class FreieFolien extends PanacheEntityBase {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long freieFolien_id;
    private String titel;
    private String untertitel;
    private String beschreibung;
    private String freitext;

    //private String upload;
    @Lob
    @Column(name = "upload")
    private byte[] upload;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "PPK_PROJEKTE_ID_PPK_ID_PPK_ID", referencedColumnName = "PPK_ID_PPK_ID"),
            @JoinColumn(name = "PPK_PROJEKTE_ID_PROJEKTE_ID_PROJEKT_ID", referencedColumnName = "PROJEKTE_ID_PROJEKT_ID")
    })
    private PPK_Projekte ppk_projekte_id;

    public byte[] getUpload() {
        return upload;
    }

    public void setUpload(byte[] upload) {
        this.upload = upload;
    }

    /*public String getUpload() {
        return upload;
    }

    public void setUpload(String upload) {
        this.upload = upload;
    }*/

    public PPK_Projekte getPpk_projekte_id() {
        return ppk_projekte_id;
    }

    public void setPpk_projekte_id(PPK_Projekte ppk_projekte_id) {
        this.ppk_projekte_id = ppk_projekte_id;
    }

    public Long getFreieFolien_id() {
        return freieFolien_id;
    }

    public void setFreieFolien_id(Long freieFolien_id) {
        this.freieFolien_id = freieFolien_id;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
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