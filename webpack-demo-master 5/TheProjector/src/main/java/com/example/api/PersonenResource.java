package com.example.api;

import com.example.model.CredentialsDTO;
import com.example.model.LoginDTO;
import com.example.workloads.Personen.Personen;
import com.example.workloads.Personen.PersonenRepo;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.json.Json;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.http.HttpClient;
import java.time.Instant;
import java.util.Set;
import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.jwt.Claims;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/personen")
@RolesAllowed({"admin", "user"})
public class PersonenResource {

    private final PersonenRepo personenRepo;

    @Inject
    @ConfigProperty(name="smallrye.jwt.new-token.lifespan")
    long lifespan;

    public PersonenResource(PersonenRepo personenRepo) {
        this.personenRepo = personenRepo;
    }

    @GET
    @Path("/search/{username}")
    public Response search(@PathParam("username") String username) {
        var foundPerson = this.personenRepo.search(username);
        return Response.ok(foundPerson).build();
    }

    /*@GET
    @Path("login/{username}/{passwort}")
    @PermitAll
    public Response login(@PathParam("username") String username, @PathParam("passwort") String passwort) {
        var login = this.personenRepo.login(username, passwort);
        long exp = Instant.now().getEpochSecond() + lifespan;
        String token = Jwt
                .claim(Claims.upn.name(), username)
                .groups(Set.of("Projektmanager", "Fachkoordinator", "Mitarbeiter", "Stakeholder", "Auftraggeber"))
                .sign();
        String entity = Json.createObjectBuilder()
                .add("token", token)
                .add("expires_at", exp)
                .build().toString();
        return Response.ok(login).build();
    }*/

    @GET
    @Path("getPersonByUsername/{username}")
    public Response getPersonByUsername(@PathParam("username") String username) {
        var foundPersonByUsername = this.personenRepo.getPersonByUsername(username);
        return Response.ok(foundPersonByUsername).build();
    }

    @POST
    @Path("login/")
    @PermitAll
    public Response login(CredentialsDTO credentialsDTO) {
        LoginDTO anz = personenRepo.login(credentialsDTO.getUsername(), credentialsDTO.getPasswort());
        if(!anz.getLogin()) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        long exp = Instant.now().getEpochSecond() + lifespan;

        String token = "";

        if(anz.getRollen().contains("Projektmanager") || anz.getRollen().contains("Fachkoordinator")) {
            token = Jwt
                    .claim(Claims.upn.name(), credentialsDTO.getUsername())
                    .groups("admin")
                    .sign();
        } else {
            token = Jwt
                    .claim(Claims.upn.name(), credentialsDTO.getUsername())
                    .groups("user")
                    .sign();
        }
        String entity = Json.createObjectBuilder()
                .add("token", token)
                .add("expires_at", exp)
                .build().toString();

        return Response.ok().entity(entity).build();
    }

    @GET
    @Path("/all")
    public Response getAll() {
        var allPersonen = this.personenRepo.listAll();
        return Response.ok(allPersonen).build();
    }

    @GET
    @Path("/getPersonAndArbeitsaufwand")
    @RolesAllowed("admin")
    public Response getPersonAndArbeitsaufwand() {
        var allPersonenAndArbeitsaufwand = this.personenRepo.getPersonAndArbeitsaufwand();
        return Response.ok(allPersonenAndArbeitsaufwand).build();
    }

    @GET
    @Path("/getPersonAndArbeitszeit")
    @RolesAllowed("admin")
    public Response getPersonAndArbeitszeit() {
        var allPersonenAndArbeitszeit = this.personenRepo.getPersonAndArbeitszeit();
        return Response.ok(allPersonenAndArbeitszeit).build();
    }

    @GET
    @Path("/getPersonAndArbeitsaufwandPerProjekt/{projekt_id}")
    @RolesAllowed("admin")
    public Response getPersonAndArbeitsaufwandPerProjekt(@PathParam("projekt_id") Long projekt_id) {
        var allPersonenAndArbeitsaufwandPerProjekt = this.personenRepo.getPersonAndArbeitsaufwandPerProjekt(projekt_id);
        return Response.ok(allPersonenAndArbeitsaufwandPerProjekt).build();
    }

    @GET
    @Path("/getPersonAndArbeitszeitPerProjekt/{projekt_id}")
    @RolesAllowed("admin")
    public Response getPersonAndArbeitszeitPerProjekt(@PathParam("projekt_id") Long projekt_id) {
        var allPersonenAndArbeitszeitPerProjekt = this.personenRepo.getPersonAndArbeitszeitPerProjekt(projekt_id);
        return Response.ok(allPersonenAndArbeitszeitPerProjekt).build();
    }

    @GET
    @Path("/getByID/{id}")
    public Response getById(@PathParam("id") Long id) {
        var found = this.personenRepo.findById(id);
        return Response.ok(found).build();
    }

    @POST
    @Path("/add")
    @Transactional
    @RolesAllowed("admin")
    public Response add(Personen personen) {
        try {
            personenRepo.insert(personen);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(personen).build();
    }

    @PUT
    @Path("/update")
    @Transactional
    public Response update(Personen personen) {
        try {
            personenRepo.update(personen);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(personen).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    @RolesAllowed("admin")
    public Response delete(@PathParam("id") Long id) {
        try {
            personenRepo.deleteById(id);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(id).build();
    }
}