package com.example.workloads.Untertitel_Daten;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Untertitel_DatenRepo implements PanacheRepository<Untertitel_Daten> {

    public void update(Untertitel_Daten untertitel_daten) {
        this.getEntityManager().merge(untertitel_daten);
    }

}