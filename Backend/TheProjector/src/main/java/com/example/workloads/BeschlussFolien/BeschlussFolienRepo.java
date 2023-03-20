package com.example.workloads.BeschlussFolien;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BeschlussFolienRepo implements PanacheRepository<BeschlussFolien> {

    public void update(BeschlussFolien beschlussFolien) {
        this.getEntityManager().merge(beschlussFolien);
    }

}