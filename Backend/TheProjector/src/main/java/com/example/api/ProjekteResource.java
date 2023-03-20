package com.example.api;

import antlr.Token;
import com.example.workloads.Personen.Personen;
import com.example.workloads.Projekte.Projekte;
import com.example.workloads.Projekte.ProjekteRepo;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/projekte")
@RolesAllowed({"admin", "user"})
public class ProjekteResource {

    private final ProjekteRepo projekteRepo;

    public ProjekteResource(ProjekteRepo projekteRepo) {
        this.projekteRepo = projekteRepo;
    }

    @GET
    @Path("/search/{name}")
    @RolesAllowed("admin")
    public Response search(@PathParam("name") String name) {
        var foundProjekt = this.projekteRepo.search(name);
        return Response.ok(foundProjekt).build();
    }

    @GET
    @Path("/getProjectsOfPerson/{id}")
    public Response getProjectsOfPerson(@PathParam("id") Long id) {
        var allProjekte = this.projekteRepo.getProjectsOfPerson(id);
        return Response.ok(allProjekte).build();
    }

    @GET
    @Path("/getProjektmanager/{id}")
    public Response getProjektmanager(@PathParam("id") Long id) {
        var projektmanager = this.projekteRepo.getProjektmanager(id);
        return Response.ok(projektmanager).build();
    }

    @GET
    @Path("/getFachkoordinator/{id}")
    public Response getFachkoordinator(@PathParam("id") Long id) {
        var projektmanager = this.projekteRepo.getFachkoordinator(id);
        return Response.ok(projektmanager).build();
    }

    @GET
    @Path("/getPersonenOfProject/{id}")
    public Response getPersonenOfProject(@PathParam("id") Long id) {
        var mitarbeiter = this.projekteRepo.getPersonenOfProject(id);
        return Response.ok(mitarbeiter).build();
    }

    @GET
    @Path("/getProjekteAnzahl")
    public Response getProjekteAnzahl() {
        var anzahlProjekte = this.projekteRepo.getProjekteAnzahl();
        return Response.ok(anzahlProjekte).build();
    }

    @GET
    @Path("/getMeilensteineOfProject/{id}")
    public Response getMeilensteineOfProject(@PathParam("id") Long id) {
        var meilensteine = this.projekteRepo.getMeilensteineOfProject(id);
        return Response.ok(meilensteine).build();
    }

    @GET
    @Path("/all")
    public Response getAll() {
        var all = this.projekteRepo.listAll();
        return Response.ok(all).build();
    }

    @GET
    @Path("/getByID/{id}")
    public Response getById(@PathParam("id") Long id) {
        var found = this.projekteRepo.findById(id);
        return Response.ok(found).build();
    }

    @POST
    @Path("/add")
    @Transactional
    @RolesAllowed("admin")
    public Response add(Projekte projekte) {
        try {
            projekteRepo.persist(projekte);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(projekte).build();
    }

    @PUT
    @Path("/update")
    @Transactional
    @RolesAllowed("admin")
    public Response update(Projekte projekte) {
        try {
            projekteRepo.update(projekte);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(projekte).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    @RolesAllowed("admin")
    public Response delete(@PathParam("id") Long id) {
        try {
            projekteRepo.deleteById(id);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(id).build();
    }
}