package com.example.workloads.Phasen_Projekt;

import com.example.workloads.Softwareanforderungen.SoftwareanforderungenRepo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;

@ApplicationScoped
public class Phasen_ProjektRepo implements PanacheRepository<Phasen_Projekt> {

    private final SoftwareanforderungenRepo softwareanforderungenRepo;

    public Phasen_ProjektRepo(SoftwareanforderungenRepo softwareanforderungenRepo) {
        this.softwareanforderungenRepo = softwareanforderungenRepo;
    }

    public void update(Phasen_Projekt phasen_projekt) {
        this.getEntityManager().merge(phasen_projekt);
    }

    public void insert(int status1, int status2, int status3, int status4, int status5, int status6, int status7) {
        var maxId = softwareanforderungenRepo.getMaxId();
        Query query = this.getEntityManager().createNativeQuery("insert into Phasen_Projekt (status, phasen_id_phasen_id, softwareanforderungen_id_softwareanforderungen_id) " +
                        "values (:status1, 1, :id), (:status2, 2, :id), (:status3, 3, :id), (:status4, 4, :id), (:status5, 5, :id), (:status6, 6, :id), (:status7, 7, :id);", Phasen_Projekt.class)
                .setParameter("status1", status1)
                .setParameter("status2", status2)
                .setParameter("status3", status3)
                .setParameter("status4", status4)
                .setParameter("status5", status5)
                .setParameter("status6", status6)
                .setParameter("status7", status7)
                .setParameter("id", maxId);
        query.executeUpdate();
    }

}