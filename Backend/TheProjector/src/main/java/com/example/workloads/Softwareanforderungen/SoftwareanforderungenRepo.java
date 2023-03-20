package com.example.workloads.Softwareanforderungen;

import com.example.workloads.Phasen_Projekt.Phasen_Projekt;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;

@ApplicationScoped
public class SoftwareanforderungenRepo implements PanacheRepository<Softwareanforderungen> {

    public void update(Softwareanforderungen softwareanforderungen) {
        this.getEntityManager().merge(softwareanforderungen);
    }

    public Long getMaxId() {
        Query query = this.getEntityManager().createQuery("select max(s.softwareanforderungen_id) from Softwareanforderungen s", Long.class);
        return (Long) query.getSingleResult();
    }

}