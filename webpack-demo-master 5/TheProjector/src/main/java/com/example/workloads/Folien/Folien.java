package com.example.workloads.Folien;

import com.example.workloads.Logos_Daten.Logos_Daten;
import com.example.workloads.Meilensteine_Daten.Meilensteine_Daten;
import com.example.workloads.PPK_Projekte.PPK_Projekte;
import com.example.workloads.PPKategorien.PPKategorie;
import com.example.workloads.Tabellen_Daten.Tabellen_Daten;
import com.example.workloads.Texte_Daten.Texte_Daten;
import com.example.workloads.Titel_Daten.Titel_Daten;
import com.example.workloads.Untertitel_Daten.Untertitel_Daten;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
public class Folien extends PanacheEntityBase {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long folien_id;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "titel_daten_id_titel_daten_id")
    private Titel_Daten titel_Daten_id;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "untertitel_daten_id_untertitel_daten_id")
    private Untertitel_Daten untertitel_Daten_id;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "logos_daten_id_logos_daten_id")
    private Logos_Daten logos_Daten_id;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "meilensteine_daten_id_meilensteine_daten_id")
    private Meilensteine_Daten meilensteine_Daten_id;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "tabellen_daten_id_tabellen_daten_id")
    private Tabellen_Daten tabellen_Daten_id;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "texte_daten_id_texte_daten_id")
    private Texte_Daten texte_Daten_id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "PPK_PROJEKTE_ID_PPK_ID_PPK_ID", referencedColumnName = "PPK_ID_PPK_ID"),
            @JoinColumn(name = "PPK_PROJEKTE_ID_PROJEKTE_ID_PROJEKT_ID", referencedColumnName = "PROJEKTE_ID_PROJEKT_ID")
    })
    private PPK_Projekte ppk_projekte_id;

    @ManyToOne
    @JoinColumn(name = "ppkategorie_id_pp_kategorie")
    private PPKategorie ppkategorie_id;

    public Texte_Daten getTexte_Daten_id() {
        return texte_Daten_id;
    }

    public void setTexte_Daten_id(Texte_Daten texte_Daten_id) {
        this.texte_Daten_id = texte_Daten_id;
    }

    public Long getFolien_id() {
        return folien_id;
    }

    public void setFolien_id(Long folien_id) {
        this.folien_id = folien_id;
    }

    public Tabellen_Daten getTabellen_Daten_id() {
        return tabellen_Daten_id;
    }

    public void setTabellen_Daten_id(Tabellen_Daten tabellen_Daten_id) {
        this.tabellen_Daten_id = tabellen_Daten_id;
    }

    public Meilensteine_Daten getMeilensteine_Daten_id() {
        return meilensteine_Daten_id;
    }

    public void setMeilensteine_Daten_id(Meilensteine_Daten meilensteine_Daten_id) {
        this.meilensteine_Daten_id = meilensteine_Daten_id;
    }

    public Logos_Daten getLogos_Daten_id() {
        return logos_Daten_id;
    }

    public void setLogos_Daten_id(Logos_Daten logos_Daten_id) {
        this.logos_Daten_id = logos_Daten_id;
    }

    public Untertitel_Daten getUntertitel_Daten_id() {
        return untertitel_Daten_id;
    }

    public void setUntertitel_Daten_id(Untertitel_Daten untertitel_Daten_id) {
        this.untertitel_Daten_id = untertitel_Daten_id;
    }

    public Titel_Daten getTitel_Daten_id() {
        return titel_Daten_id;
    }

    public void setTitel_Daten_id(Titel_Daten titel_Daten_id) {
        this.titel_Daten_id = titel_Daten_id;
    }

    public PPKategorie getPpkategorie_id() {
        return ppkategorie_id;
    }

    public void setPpkategorie_id(PPKategorie ppkategorie_id) {
        this.ppkategorie_id = ppkategorie_id;
    }

    public PPK_Projekte getPpk_projekte_id() {
        return ppk_projekte_id;
    }

    public void setPpk_projekte_id(PPK_Projekte ppk_projekte_id) {
        this.ppk_projekte_id = ppk_projekte_id;
    }
}