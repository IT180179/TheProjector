package com.example.workloads.Tabellen_Daten;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Tabellen_DatenRepo implements PanacheRepository<Tabellen_Daten> {

    public void update(Tabellen_Daten tabellen_daten) {
        this.getEntityManager().merge(tabellen_daten);
    }

}