package com.example.workloads.Texte_Daten;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Texte_DatenRepo implements PanacheRepository<Texte_Daten> {

    public void update(Texte_Daten texte_daten) {
        this.getEntityManager().merge(texte_daten);
    }

}