package com.example.workloads.PPKategorien;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PPKategorieRepo implements PanacheRepository<PPKategorie> {

    public void update(PPKategorie ppKategorie) {
        this.getEntityManager().merge(ppKategorie);
    }

}