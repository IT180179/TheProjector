package com.example.workloads.Ressourcen;

import com.example.workloads.PPK.PPK;
import com.example.workloads.PPK_Projekte.PPK_Projekte;
import com.example.workloads.PPK_Projekte.PPK_ProjekteRepo;
import com.example.workloads.Personen.Personen;
import com.example.workloads.Personen.PersonenRepo;
import com.example.workloads.Projekte.Projekte;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class RessourcenRepo implements PanacheRepository<Ressourcen> {

    private final PersonenRepo personenRepo;
    private final PPK_ProjekteRepo ppk_projekteRepo;

    public RessourcenRepo(PersonenRepo personenRepo, PPK_ProjekteRepo ppk_projekteRepo) {
        this.personenRepo = personenRepo;
        this.ppk_projekteRepo = ppk_projekteRepo;
    }

    public List<Ressourcen> getById(Long ppk_id, Long personen_id, Long projekte_id) {
        PPK_Projekte ppk_projekte = new PPK_Projekte();
        Personen personen = new Personen();
        try {
            ppk_projekte = this.ppk_projekteRepo.getById(ppk_id, projekte_id).get(0);
        }catch (Exception exception) {
            ppk_projekte = new PPK_Projekte();
        }
        try {
            personen = this.personenRepo.findById(personen_id);
        }catch (Exception exception) {
            personen = new Personen();
        }
        Query query = this.getEntityManager().createQuery("select r from Ressourcen r where r.ressourcen_id.personen_id = :personen_id and r.ressourcen_id.ppk_projekte_id = :ppk_projekte_id", Ressourcen.class)
                .setParameter("ppk_projekte_id", ppk_projekte)
                .setParameter("personen_id", personen);
        return query.getResultList();
    }

    public void update(Ressourcen ressourcen) {
        this.getEntityManager().merge(ressourcen);
    }

    public Long getArbeitsaufwand(Long person_id) {
        Query query = this.getEntityManager().createQuery("select sum(r.aufwand) from Ressourcen r where r.ressourcen_id.personen_id = :personen_id", Long.class)
                .setParameter("personen_id", personenRepo.findById(person_id));
        return (Long) query.getSingleResult();
    }

}