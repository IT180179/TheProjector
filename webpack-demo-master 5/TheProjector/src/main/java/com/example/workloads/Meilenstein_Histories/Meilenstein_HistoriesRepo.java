package com.example.workloads.Meilenstein_Histories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class Meilenstein_HistoriesRepo implements PanacheRepository<Meilenstein_Histories> {

    public void update(Meilenstein_Histories meilenstein_histories) {
        this.getEntityManager().merge(meilenstein_histories);
    }

    public void deletePerMeilensteinId(Long id) {
        Query query = this.getEntityManager().createQuery("delete from Meilenstein_Histories m where m.meilenstein_id.meilensteine_id = :id")
                .setParameter("id", id);
        query.executeUpdate();
    }

    public List<Meilenstein_Histories> getHistory(Long meilenstein_id) {
        Query query = this.getEntityManager().createQuery("select m from Meilenstein_Histories m where m.meilenstein_id.meilensteine_id = :meilenstein_id", Meilenstein_Histories.class)
                .setParameter("meilenstein_id", meilenstein_id);
        return query.getResultList();
    }

}