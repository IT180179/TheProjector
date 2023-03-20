package com.example.workloads.Meilensteine_Daten;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Meilensteine_DatenRepo implements PanacheRepository<Meilensteine_Daten> {

    public void update(Meilensteine_Daten meilensteine_daten) {
        this.getEntityManager().merge(meilensteine_daten);
    }

}