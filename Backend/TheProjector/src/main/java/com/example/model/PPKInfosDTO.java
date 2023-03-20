package com.example.model;

import com.example.workloads.Gaeste.Gaeste;
import com.example.workloads.Projekte.Projekte;

import java.time.LocalDate;
import java.util.List;

public class PPKInfosDTO {

    private Long ppk_id;
    private LocalDate datum;
    private List<Projekte> projekteList;
    private List<Gaeste> gaesteList;

    public PPKInfosDTO(Long ppk_id, LocalDate datum, List<Projekte> projekteList, List<Gaeste> gaesteList) {
        this.ppk_id = ppk_id;
        this.datum = datum;
        this.projekteList = projekteList;
        this.gaesteList = gaesteList;
    }

    public Long getPpk_id() {
        return ppk_id;
    }

    public void setPpk_id(Long ppk_id) {
        this.ppk_id = ppk_id;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public List<Projekte> getProjekteList() {
        return projekteList;
    }

    public void setProjekteList(List<Projekte> projekteList) {
        this.projekteList = projekteList;
    }

    public List<Gaeste> getGaesteList() {
        return gaesteList;
    }

    public void setGaesteList(List<Gaeste> gaesteList) {
        this.gaesteList = gaesteList;
    }
}