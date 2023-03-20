package com.example.workloads.Personen;

import com.example.model.LoginDTO;
import com.example.model.PersonenAufwandDTO;
import com.example.workloads.Rollen.Rollen;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.jasypt.util.password.StrongPasswordEncryptor;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class PersonenRepo implements PanacheRepository<Personen> {

    StrongPasswordEncryptor strongPasswordEncryptor = new StrongPasswordEncryptor();

    public List<Personen> search(String username) {
        Query query = this.getEntityManager().createQuery("select p from Personen p where lower(p.username) like lower(:username)", Personen.class)
                .setParameter("username", username + "%");
        return query.getResultList();
    }

    public void insert(Personen personen) {
        String encryptedPassword = strongPasswordEncryptor.encryptPassword(personen.getPasswort());
        Query query = this.getEntityManager().createNativeQuery("insert into Personen (vorname, nachname, username, passwort, rechte, abteilungs_id_abteilungs_id) " +
                        "values(:vorname, :nachname, :username, :passwort, :rechte, :abteilungs_id)", Personen.class)
                .setParameter("vorname", personen.getVorname())
                .setParameter("nachname", personen.getNachname())
                .setParameter("username", personen.getUsername())
                .setParameter("passwort", encryptedPassword)
                .setParameter("rechte", personen.getRechte())
                .setParameter("abteilungs_id", personen.getAbteilungs_id().getAbteilungs_id());
        query.executeUpdate();
    }

    public Personen getPersonByUsername(String username) {
        Query query = this.getEntityManager().createQuery("select p from Personen p where p.username = :username", Personen.class)
                .setParameter("username", username);
        return (Personen) query.getSingleResult();
    }

    public List<String> getRollenPerPerson(Long id) {
        Query query = this.getEntityManager().createQuery("select r.name from Rollen r join Einsaetze e on r.rollen_id = e.einsaetze_id.rollen_id.rollen_id where e.einsaetze_id.personen_id.personen_id = :id", String.class)
                .setParameter("id", id);
        return query.getResultList();
    }

    public LoginDTO login(String username, String passwort) {
        Boolean passwortCheck = false;
        Boolean usernameCheck = false;
        Personen personen = new Personen();
        Query query = this.getEntityManager().createQuery("select p from Personen p where p.username = :username", Personen.class)
                .setParameter("username", username);
        try {
            personen = (Personen) query.getSingleResult();
        }catch (Exception exception) {
            System.out.println("User not found!");
        }
        if(Objects.equals(personen.getUsername(), username)) {
            usernameCheck = true;
        }else{
            return new LoginDTO(personen, getRollenPerPerson(personen.getPersonen_id()), false);
        }
        if(strongPasswordEncryptor.checkPassword(passwort, personen.getPasswort())) {
            passwortCheck = true;
        }else{
            return new LoginDTO(personen, getRollenPerPerson(personen.getPersonen_id()), false);
        }
        if(usernameCheck && passwortCheck == true) {
            return new LoginDTO(personen, getRollenPerPerson(personen.getPersonen_id()), true);
        }
        return new LoginDTO(personen, getRollenPerPerson(personen.getPersonen_id()), false);
    }

    public void update(Personen personen) {
        this.getEntityManager().merge(personen);
    }

    public List<PersonenAufwandDTO> getPersonAndArbeitsaufwand() {
        TypedQuery<PersonenAufwandDTO> query = this.getEntityManager().createQuery("select distinct new com.example.model.PersonenAufwandDTO(p.personen_id, p.vorname, p.nachname, p.username, p.passwort, p.rechte, e.arbeitsstunden, a, r) from Personen p " +
                "join Einsaetze e on p.personen_id = e.einsaetze_id.personen_id.personen_id join Abteilungen a on p.abteilungs_id.abteilungs_id = a.abteilungs_id join Rollen r on e.einsaetze_id.rollen_id.rollen_id = r.rollen_id", PersonenAufwandDTO.class);
        return query.getResultList();
    }

    public List<PersonenAufwandDTO> getPersonAndArbeitszeit() {
        TypedQuery<PersonenAufwandDTO> query = this.getEntityManager().createQuery("select distinct new com.example.model.PersonenAufwandDTO(p.personen_id, p.vorname, p.nachname, p.username, p.passwort, p.rechte, sum(az.arbeitszeit), a, r) from Personen p " +
                "join Einsaetze e on p.personen_id = e.einsaetze_id.personen_id.personen_id join Abteilungen a on p.abteilungs_id.abteilungs_id = a.abteilungs_id join Rollen r on e.einsaetze_id.rollen_id.rollen_id = r.rollen_id " +
                "join Arbeitszeiten az on p.personen_id = az.personen_id.personen_id group by p,a,r", PersonenAufwandDTO.class);
        return query.getResultList();
    }

    public List<PersonenAufwandDTO> getPersonAndArbeitsaufwandPerProjekt(Long projekt_id) {
        TypedQuery<PersonenAufwandDTO> query = this.getEntityManager().createQuery("select distinct new com.example.model.PersonenAufwandDTO(p.personen_id, p.vorname, p.nachname, p.username, p.passwort, p.rechte, e.arbeitsstunden, a, r) from Personen p " +
                "join Einsaetze e on p.personen_id = e.einsaetze_id.personen_id.personen_id join Abteilungen a on p.abteilungs_id.abteilungs_id = a.abteilungs_id join Rollen r on e.einsaetze_id.rollen_id.rollen_id = r.rollen_id " +
                        "where e.einsaetze_id.projekte_id.projekt_id = :projekt_id", PersonenAufwandDTO.class)
                .setParameter("projekt_id", projekt_id);
        return query.getResultList();
    }

    public List<PersonenAufwandDTO> getPersonAndArbeitszeitPerProjekt(Long projekt_id) {
        TypedQuery<PersonenAufwandDTO> query = this.getEntityManager().createQuery("select distinct new com.example.model.PersonenAufwandDTO(p.personen_id, p.vorname, p.nachname, p.username, p.passwort, p.rechte, sum(az.arbeitszeit), a, r) from Personen p " +
                        "join Einsaetze e on p.personen_id = e.einsaetze_id.personen_id.personen_id join Abteilungen a on p.abteilungs_id.abteilungs_id = a.abteilungs_id join Rollen r on e.einsaetze_id.rollen_id.rollen_id = r.rollen_id " +
                        "join Arbeitszeiten az on p.personen_id = az.personen_id.personen_id where az.projekte_id.projekt_id = :projekt_id group by p,a,r", PersonenAufwandDTO.class)
                .setParameter("projekt_id", projekt_id);
        return query.getResultList();
    }
}