package com.example.workloads.Folien;

import com.example.workloads.Personen.Personen;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FolienRepo implements PanacheRepository<Folien> {

    public void update(Folien folien) {
        this.getEntityManager().merge(folien);
    }

}