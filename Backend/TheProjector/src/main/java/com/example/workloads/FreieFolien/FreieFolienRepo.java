package com.example.workloads.FreieFolien;

import com.example.model.FreieFolienDTO;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

@ApplicationScoped
public class FreieFolienRepo implements PanacheRepository<FreieFolien> {

    public void insert(FreieFolienDTO freieFolienDTO) {
        byte[] bild = null;
        bild = Base64.getEncoder().encode(freieFolienDTO.getBild().getBytes());
        //bild = Base64.getDecoder().decode(new String(name).getBytes("UTF-8"));
        System.out.println(bild);
        Query query = this.getEntityManager().createNativeQuery("insert into FreieFolien (titel, untertitel, beschreibung, freitext, upload, ppk_projekte_id) values(:titel, :untertitel, :beschreibung, :freitext, :upload, :ppk_projekte_id)")
                .setParameter("titel", freieFolienDTO.getTitel())
                .setParameter("untertitel", freieFolienDTO.getUntertitel())
                .setParameter("beschreibung", freieFolienDTO.getBeschreibung())
                .setParameter("freitext", freieFolienDTO.getFreitext())
                .setParameter("upload", bild)
                .setParameter("ppk_projekte_id", freieFolienDTO.getPpk_projekte_id());
        query.executeUpdate();
    }

    public void update(FreieFolien freieFolien) {
        this.getEntityManager().merge(freieFolien);
    }

}