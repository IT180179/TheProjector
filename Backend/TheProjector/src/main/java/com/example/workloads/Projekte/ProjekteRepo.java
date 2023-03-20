package com.example.workloads.Projekte;

import com.example.workloads.Meilensteine.Meilensteine;
import com.example.workloads.Personen.Personen;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class ProjekteRepo implements PanacheRepository<Projekte> {

    public void update(Projekte projekte) {
        this.getEntityManager().merge(projekte);
    }

    public List<Projekte> search(String name) {
        Query query = this.getEntityManager().createQuery("select p from Projekte p where lower(p.titel) like lower(:name)", Projekte.class)
                .setParameter("name", name + "%");
        return query.getResultList();
    }

    public List<Projekte> getProjectsOfPerson(Long person_id) {
        Query query = this.getEntityManager().createQuery("select p from Projekte p join Einsaetze e on p.projekt_id = e.einsaetze_id.projekte_id.projekt_id where e.einsaetze_id.personen_id.personen_id = :person_id", Projekte.class)
                .setParameter("person_id", person_id);
        return query.getResultList();
    }

    public Personen getProjektmanager(Long projekt_id) {
        Query query = this.getEntityManager().createQuery("select p from Personen p join Einsaetze e on p.personen_id = e.einsaetze_id.personen_id.personen_id where e.einsaetze_id.projekte_id.projekt_id = :projekt_id and e.einsaetze_id.rollen_id.rollen_id = 1", Personen.class)
                .setParameter("projekt_id", projekt_id);
        return (Personen) query.getSingleResult();
    }

    public Personen getFachkoordinator(Long projekt_id) {
        Query query = this.getEntityManager().createQuery("select p from Personen p join Einsaetze e on p.personen_id = e.einsaetze_id.personen_id.personen_id where e.einsaetze_id.projekte_id.projekt_id = :projekt_id and e.einsaetze_id.rollen_id.rollen_id = 2", Personen.class)
                .setParameter("projekt_id", projekt_id);
        return (Personen) query.getSingleResult();
    }

    public List<Personen> getPersonenOfProject(Long projekt_id) {
        Query query = this.getEntityManager().createQuery("select distinct p from Personen p join Einsaetze e on p.personen_id = e.einsaetze_id.personen_id.personen_id where e.einsaetze_id.projekte_id.projekt_id = :projekt_id", Personen.class)
                .setParameter("projekt_id", projekt_id);
        return query.getResultList();
    }

    public Long getProjekteAnzahl() {
        Query query = this.getEntityManager().createQuery("select count(p) from Projekte p", Long.class);
        return (Long) query.getSingleResult();
    }

    public List<Meilensteine> getMeilensteineOfProject(Long projekt_id) {
        Query query = this.getEntityManager().createQuery("select m from Meilensteine m where m.projekt_id.projekt_id = :projekt_id", Meilensteine.class)
                .setParameter("projekt_id", projekt_id);
        return query.getResultList();
    }

}