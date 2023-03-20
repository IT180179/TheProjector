package com.example.workloads.Gaeste;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GaesteRepo implements PanacheRepository<Gaeste> {

    public void update(Gaeste gaeste) {
        this.getEntityManager().merge(gaeste);
    }

}