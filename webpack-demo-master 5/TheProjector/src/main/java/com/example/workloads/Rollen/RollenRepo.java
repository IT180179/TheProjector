package com.example.workloads.Rollen;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RollenRepo implements PanacheRepository<Rollen> {

    public void update(Rollen rollen) {
        this.getEntityManager().merge(rollen);
    }

}