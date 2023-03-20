package com.example.workloads.Kategorien;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class KategorienRepo implements PanacheRepository<Kategorien> {

    public void update(Kategorien kategorien) {
        this.getEntityManager().merge(kategorien);
    }

}