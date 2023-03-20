package com.example.workloads.Ressourcen;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Ressourcen extends PanacheEntityBase {

    @EmbeddedId
    private RessourcenID ressourcen_id;
    private Double aufwand;

    public RessourcenID getRessourcen_id() {
        return ressourcen_id;
    }

    public void setRessourcen_id(RessourcenID ressourcen_id) {
        this.ressourcen_id = ressourcen_id;
    }

    public Double getAufwand() {
        return aufwand;
    }

    public void setAufwand(Double aufwand) {
        this.aufwand = aufwand;
    }
}