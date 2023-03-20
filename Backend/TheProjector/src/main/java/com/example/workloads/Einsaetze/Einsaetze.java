package com.example.workloads.Einsaetze;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Einsaetze extends PanacheEntityBase {

    @EmbeddedId
    private EinsaetzeId einsaetze_id;
    private Double arbeitsstunden;

    public EinsaetzeId getEinsaetze_id() {
        return einsaetze_id;
    }

    public void setEinsaetze_id(EinsaetzeId einsaetze_id) {
        this.einsaetze_id = einsaetze_id;
    }

    public Double getArbeitsstunden() {
        return arbeitsstunden;
    }

    public void setArbeitsstunden(Double arbeitsstunden) {
        this.arbeitsstunden = arbeitsstunden;
    }
}