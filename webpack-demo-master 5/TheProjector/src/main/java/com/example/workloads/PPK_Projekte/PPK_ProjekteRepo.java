package com.example.workloads.PPK_Projekte;

import com.example.workloads.Einsaetze.Einsaetze;
import com.example.workloads.PPK.PPK;
import com.example.workloads.PPK.PPKRepo;
import com.example.workloads.Projekte.Projekte;
import com.example.workloads.Projekte.ProjekteRepo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class PPK_ProjekteRepo implements PanacheRepository<PPK_Projekte> {

    private final PPKRepo ppkRepo;
    private final ProjekteRepo projekteRepo;

    public PPK_ProjekteRepo(PPKRepo ppkRepo, ProjekteRepo projekteRepo) {
        this.ppkRepo = ppkRepo;
        this.projekteRepo = projekteRepo;
    }

    public List<PPK_Projekte> getById(Long ppk_id, Long projekte_id) {
        PPK ppk = new PPK();
        Projekte projekte = new Projekte();
        try {
            ppk = ppkRepo.findById(ppk_id);
        }catch (Exception exception){
            ppk = new PPK();
        }
        try {
            projekte = projekteRepo.findById(projekte_id);
        }catch (Exception exception){
            projekte = new Projekte();
        }
        Query query = this.getEntityManager().createQuery("select p from PPK_Projekte p where p.ppk_projekte_id.ppk_id = :ppk_id and p.ppk_projekte_id.projekte_id = :projekte_id", PPK_Projekte.class)
                .setParameter("ppk_id", ppk)
                .setParameter("projekte_id", projekte);
        return query.getResultList();
    }

    public void update(PPK_Projekte ppk_projekte) {
        this.getEntityManager().merge(ppk_projekte);
    }

}