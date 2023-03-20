package com.example.workloads.Meilensteine;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MeilensteineRepo implements PanacheRepository<Meilensteine> {

    public void update(Meilensteine meilensteine) {
        this.getEntityManager().merge(meilensteine);
    }

}