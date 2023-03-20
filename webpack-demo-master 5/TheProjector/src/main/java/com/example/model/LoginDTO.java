package com.example.model;

import com.example.workloads.Personen.Personen;
import com.example.workloads.Rollen.Rollen;

import java.util.List;

public class LoginDTO {

    private Personen personen;

    private List<String> rollen;
    private Boolean login;

    public LoginDTO(Personen personen, List<String> rollen, Boolean login) {
        this.personen = personen;
        this.rollen = rollen;
        this.login = login;
    }

    public Personen getPersonen() {
        return personen;
    }

    public void setPersonen(Personen personen) {
        this.personen = personen;
    }

    public List<String> getRollen() {
        return rollen;
    }

    public void setRollen(List<String> rollen) {
        this.rollen = rollen;
    }

    public Boolean getLogin() {
        return login;
    }

    public void setLogin(Boolean login) {
        this.login = login;
    }
}