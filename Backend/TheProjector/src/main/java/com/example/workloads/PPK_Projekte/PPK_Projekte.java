package com.example.workloads.PPK_Projekte;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class PPK_Projekte extends PanacheEntityBase {

    @EmbeddedId
    private PPK_ProjekteId ppk_projekte_id;

    public PPK_ProjekteId getPpk_projekte_id() {
        return ppk_projekte_id;
    }

    public void setPpk_projekte_id(PPK_ProjekteId ppk_projekte_id) {
        this.ppk_projekte_id = ppk_projekte_id;
    }
}