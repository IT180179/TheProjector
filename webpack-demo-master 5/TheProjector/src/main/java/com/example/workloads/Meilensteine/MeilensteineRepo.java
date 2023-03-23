package com.example.workloads.Meilensteine;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class MeilensteineRepo implements PanacheRepository<Meilensteine> {

    public List<Meilensteine> getAllSorted() {
        Query query = this.getEntityManager().createQuery("select m from Meilensteine m order by m.end_datum asc");
        return query.getResultList();
    }

    public void update(Meilensteine meilensteine) {
        this.getEntityManager().merge(meilensteine);
    }

}
