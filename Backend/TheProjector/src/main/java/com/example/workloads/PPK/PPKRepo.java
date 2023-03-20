package com.example.workloads.PPK;

import com.example.model.PPKInfosDTO;
import com.example.workloads.Gaeste.Gaeste;
import com.example.workloads.Projekte.Projekte;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class PPKRepo implements PanacheRepository<PPK> {

    public void update(PPK ppk) {
        this.getEntityManager().merge(ppk);
    }

    public LocalDate getNextPPK() {
        Query query = this.getEntityManager().createQuery("select min(p.datum) from PPK p", LocalDate.class);
        return (LocalDate) query.getSingleResult();
    }

    public PPKInfosDTO getNextPPKWithProjektAndGaeste() {
        Query queryDatum = this.getEntityManager().createQuery("select min(p.datum) from PPK p", LocalDate.class);
        Query query = this.getEntityManager().createQuery("select p from PPK p where p.datum = :datum", PPK.class)
                .setParameter("datum", queryDatum.getSingleResult());
        PPK ppk = (PPK) query.getSingleResult();
        return new PPKInfosDTO(ppk.getPpk_id(), ppk.getDatum(), getProjekteOfPPK(ppk.getPpk_id()), getGaesteOfPPK(ppk.getPpk_id()));
    }

    public List<Projekte> getProjekteOfPPK(Long id) {
        Query query = this.getEntityManager().createQuery("select p from Projekte p join PPK_Projekte pp on p.projekt_id = pp.ppk_projekte_id.projekte_id.projekt_id " +
                        "where pp.ppk_projekte_id.ppk_id.ppk_id = :id", Projekte.class)
                .setParameter("id", id);
        return query.getResultList();
    }

    public List<Gaeste> getGaesteOfPPK(Long ppk_id) {
        Query query = this.getEntityManager().createQuery("select g from Gaeste g where g.ppk_id.ppk_id = :ppk_id", Gaeste.class)
                .setParameter("ppk_id", ppk_id);
        return query.getResultList();
    }

}