package com.example.workloads.Abteilungen;

import com.example.workloads.Personen.Personen;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class AbteilungenRepo implements PanacheRepository<Abteilungen> {

    public void update(Abteilungen abteilungen) {
        this.getEntityManager().merge(abteilungen);
    }

    public Long countAllArbeitsstunden(Long abteilungs_id) {
        Query query = this.getEntityManager().createQuery("select sum(e.arbeitsstunden) from Einsaetze e " +
                        "join Personen p on e.einsaetze_id.personen_id.personen_id = p.personen_id where p.abteilungs_id.abteilungs_id = :abteilungs_id", Long.class)
                .setParameter("abteilungs_id", abteilungs_id);
        return (Long) query.getSingleResult();
    }

    public Long countArbeitsstunden(Long abteilungs_id, Long projekt_id) {
        Query query = this.getEntityManager().createQuery("select sum(e.arbeitsstunden) from Einsaetze e " +
                        "join Personen p on e.einsaetze_id.personen_id.personen_id = p.personen_id where p.abteilungs_id.abteilungs_id = :abteilungs_id and e.einsaetze_id.projekte_id.projekt_id = :projekt_id", Long.class)
                .setParameter("abteilungs_id", abteilungs_id)
                .setParameter("projekt_id", projekt_id);
        return (Long) query.getSingleResult();
    }

    public List<Abteilungen> getAbteilungenOfBereich(Long bereichs_id) {
        Query query = this.getEntityManager().createQuery("select a from Abteilungen a where a.bereichs_id.bereich_id = :bereichs_id", Abteilungen.class)
                .setParameter("bereichs_id", bereichs_id);
        return query.getResultList();
    }

    public List<Personen> getPersonenOfAbteilung(Long abteilungs_id) {
        Query query = this.getEntityManager().createQuery("select p from Personen p where p.abteilungs_id.abteilungs_id = :abteilungs_id", Personen.class)
                .setParameter("abteilungs_id", abteilungs_id);
        return query.getResultList();
    }
}