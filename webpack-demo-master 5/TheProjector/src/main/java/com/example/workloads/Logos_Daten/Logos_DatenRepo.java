package com.example.workloads.Logos_Daten;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Logos_DatenRepo implements PanacheRepository<Logos_Daten> {

    public void update(Logos_Daten logos_daten) {
        this.getEntityManager().merge(logos_daten);
    }

}