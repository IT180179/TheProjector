package com.example.workloads.Bereiche;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;

@ApplicationScoped
public class BereicheRepo implements PanacheRepository<Bereiche> {

    public void update(Bereiche bereiche) {
        this.getEntityManager().merge(bereiche);
    }

    public Long countAllArbeitsstunden(Long bereichs_id) {
        Query query = this.getEntityManager().createQuery("select sum(e.arbeitsstunden) from Einsaetze e " +
                        "join Personen p on e.einsaetze_id.personen_id.personen_id = p.personen_id join Abteilungen a on p.abteilungs_id.abteilungs_id = a.abteilungs_id where a.bereichs_id.bereich_id = :bereichs_id", Long.class)
                .setParameter("bereichs_id", bereichs_id);
        return (Long) query.getSingleResult();
    }

    public Long countArbeitsstunden(Long bereichs_id, Long projekt_id) {
        Query query = this.getEntityManager().createQuery("select sum(e.arbeitsstunden) from Einsaetze e " +
                        "join Personen p on e.einsaetze_id.personen_id.personen_id = p.personen_id join Abteilungen a on p.abteilungs_id.abteilungs_id = a.abteilungs_id where a.bereichs_id.bereich_id = :bereichs_id and e.einsaetze_id.projekte_id.projekt_id = :projekt_id", Long.class)
                .setParameter("bereichs_id", bereichs_id)
                .setParameter("projekt_id", projekt_id);
        return (Long) query.getSingleResult();
    }
}