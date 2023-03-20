package com.example.workloads.Einsaetze;

import com.example.workloads.Personen.Personen;
import com.example.workloads.Personen.PersonenRepo;
import com.example.workloads.Projekte.Projekte;
import com.example.workloads.Projekte.ProjekteRepo;
import com.example.workloads.Rollen.Rollen;
import com.example.workloads.Rollen.RollenRepo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class EinsaetzeRepo implements PanacheRepository<Einsaetze> {

    private final RollenRepo rollenRepo;
    private final PersonenRepo personenRepo;
    private final ProjekteRepo projekteRepo;

    public EinsaetzeRepo(RollenRepo rollenRepo, PersonenRepo personenRepo, ProjekteRepo projekteRepo) {
        this.rollenRepo = rollenRepo;
        this.personenRepo = personenRepo;
        this.projekteRepo = projekteRepo;
    }

    public List<Einsaetze> getById(Long rollen_id, Long personen_id, Long projekte_id) {
        Rollen rolle = new Rollen();
        Personen person = new Personen();
        Projekte projekt = new Projekte();
        try {
            rolle = rollenRepo.findById(rollen_id);
        }catch (Exception exception) {
            rolle = new Rollen();
        }
        try {
            person = personenRepo.findById(personen_id);
        }catch (Exception exception) {
            person = new Personen();
        }
        try {
            projekt = projekteRepo.findById(projekte_id);
        }catch (Exception exception) {
            projekt = new Projekte();
        }

        Query query = this.getEntityManager().createQuery("select e from Einsaetze e where e.einsaetze_id.rollen_id = :rollen_id and e.einsaetze_id.personen_id = :personen_id and e.einsaetze_id.projekte_id = :projekte_id", Einsaetze.class)
                .setParameter("rollen_id", rolle)
                .setParameter("personen_id", person)
                .setParameter("projekte_id", projekt);
        return query.getResultList();
    }

    public void update(Einsaetze einsaetze) {
        this.getEntityManager().merge(einsaetze);
    }

    public List<Einsaetze> getEinsaetzePerPerson(Long personen_id) {
        Query query = this.getEntityManager().createQuery("select e from Einsaetze e where e.einsaetze_id.personen_id = :personen_id", Einsaetze.class)
                .setParameter("personen_id", personenRepo.findById(personen_id));
        return query.getResultList();
    }

    public Long countProjects(Long personen_id) {
        Query query = this.getEntityManager().createQuery("select count(distinct e.einsaetze_id.personen_id.personen_id) from Einsaetze e where e.einsaetze_id.personen_id = :personen_id", Long.class)
                .setParameter("personen_id", personenRepo.findById(personen_id));
        return (Long) query.getSingleResult();
    }

    public Long countProjektmanager(Long personen_id) {
        Query query = this.getEntityManager().createQuery("select count(e) from Einsaetze e where e.einsaetze_id.personen_id = :personen_id and e.einsaetze_id.rollen_id.rollen_id = 1", Long.class)
                .setParameter("personen_id", personenRepo.findById(personen_id));
        return (Long) query.getSingleResult();
    }

    public Long countFachkoordinator(Long personen_id) {
        Query query = this.getEntityManager().createQuery("select count(e) from Einsaetze e where e.einsaetze_id.personen_id = :personen_id and e.einsaetze_id.rollen_id.rollen_id = 2", Long.class)
                .setParameter("personen_id", personenRepo.findById(personen_id));
        return (Long) query.getSingleResult();
    }
}