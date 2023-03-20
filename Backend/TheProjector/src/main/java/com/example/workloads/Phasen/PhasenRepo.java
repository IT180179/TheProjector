package com.example.workloads.Phasen;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PhasenRepo implements PanacheRepository<Phasen> {

    public void update(Phasen phasen) {
        this.getEntityManager().merge(phasen);
    }

}