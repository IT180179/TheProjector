package com.example.workloads.Arbeitszeiten;

import com.example.model.ArbeitszeitenDTO;
import com.example.workloads.Bereiche.Bereiche;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class ArbeitszeitenRepo implements PanacheRepository<Arbeitszeiten> {

    public void update(Arbeitszeiten arbeitszeiten) {
        this.getEntityManager().merge(arbeitszeiten);
    }

    public List<ArbeitszeitenDTO> getArbeitszeitenPerDate(String start_date, String end_date) {
        Query query = this.getEntityManager().createQuery("select new com.example.model.ArbeitszeitenDTO(a.einsaetze_id.einsaetze_id.personen_id.personen_id, a.einsaetze_id.einsaetze_id.personen_id.vorname, a.einsaetze_id.einsaetze_id.personen_id.nachname, a.einsaetze_id.einsaetze_id.projekte_id.projekt_id, a.einsaetze_id.einsaetze_id.rollen_id.rollen_id, sum(a.arbeitszeit)) from Arbeitszeiten a " +
                        "where a.date between :start_date and :end_date group by a.einsaetze_id.einsaetze_id.personen_id.personen_id, a.einsaetze_id.einsaetze_id.personen_id.vorname, a.einsaetze_id.einsaetze_id.personen_id.nachname, a.einsaetze_id.einsaetze_id.projekte_id.projekt_id, a.einsaetze_id.einsaetze_id.rollen_id.rollen_id", ArbeitszeitenDTO.class)
                .setParameter("start_date", start_date)
                .setParameter("end_date", end_date);
        return query.getResultList();
    }

    public List<Arbeitszeiten> getArbeitszeitenPerProjekt(Long projekte_id) {
        Query query = this.getEntityManager().createQuery("select a from Arbeitszeiten a where a.einsaetze_id.einsaetze_id.projekte_id.projekt_id = :projekte_id", Arbeitszeiten.class)
                .setParameter("projekte_id", projekte_id);
        return query.getResultList();
    }

    public List<Arbeitszeiten> getArbeitszeitenPerPerson(Long personen_id) {
        Query query = this.getEntityManager().createQuery("select a from Arbeitszeiten a where a.einsaetze_id.einsaetze_id.personen_id.personen_id = :personen_id", Arbeitszeiten.class)
                .setParameter("personen_id", personen_id);
        return query.getResultList();
    }

    public List<Arbeitszeiten> getArbeitszeitenPerPersonPerProjekt(Long personen_id, Long projekt_id) {
        Query query = this.getEntityManager().createQuery("select a from Arbeitszeiten a where a.einsaetze_id.einsaetze_id.personen_id.personen_id = :personen_id and a.einsaetze_id.einsaetze_id.projekte_id.projekt_id = :projekt_id", Arbeitszeiten.class)
                .setParameter("personen_id", personen_id)
                .setParameter("projekt_id", projekt_id);
        return query.getResultList();
    }

    public Double countArbeitszeitPerPerson(Long personen_id) {
        Query query = this.getEntityManager().createQuery("select sum(a.arbeitszeit) from Arbeitszeiten a where a.einsaetze_id.einsaetze_id.personen_id.personen_id = :personen_id", Double.class)
                .setParameter("personen_id", personen_id);
        return (Double) query.getSingleResult();
    }

    public Double countArbeitszeitPerPersonPerProjekt(Long personen_id, Long projekt_id) {
        Query query = this.getEntityManager().createQuery("select sum(a.arbeitszeit) from Arbeitszeiten a where a.einsaetze_id.einsaetze_id.personen_id.personen_id = :personen_id and a.einsaetze_id.einsaetze_id.projekte_id.projekt_id = :projekt_id", Double.class)
                .setParameter("personen_id", personen_id)
                .setParameter("projekt_id", projekt_id);
        return (Double) query.getSingleResult();
    }

    public Double countArbeitszeitPerPersonPerProjektPerRolle(Long personen_id, Long projekt_id, Long rolle_id) {
        Query query = this.getEntityManager().createQuery("select sum(a.arbeitszeit) from Arbeitszeiten a where a.einsaetze_id.einsaetze_id.personen_id.personen_id = :personen_id and a.einsaetze_id.einsaetze_id.projekte_id.projekt_id = :projekt_id and a.einsaetze_id.einsaetze_id.rollen_id.rollen_id = :rollen_id", Double.class)
                .setParameter("personen_id", personen_id)
                .setParameter("projekt_id", projekt_id)
                .setParameter("rollen_id", rolle_id);
        return (Double) query.getSingleResult();
    }
}