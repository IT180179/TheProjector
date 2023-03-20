package com.example.workloads.Titel_Daten;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Titel_DatenRepo implements PanacheRepository<Titel_Daten> {

    public void update(Titel_Daten titel_daten) {
        this.getEntityManager().merge(titel_daten);
    }

}