package com.example.api;

import com.example.workloads.Einsaetze.Einsaetze;
import com.example.workloads.Einsaetze.EinsaetzeId;
import com.example.workloads.Einsaetze.EinsaetzeRepo;
import com.example.workloads.Personen.Personen;
import com.example.workloads.Projekte.Projekte;
import com.example.workloads.Rollen.Rollen;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/einsaetze")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({"admin", "user"})
public class EinsaetzeResource {

    private final EinsaetzeRepo einsaetzeRepo;

    public EinsaetzeResource(EinsaetzeRepo einsaetzeRepo) {
        this.einsaetzeRepo = einsaetzeRepo;
    }

    @GET
    @Path("/countProjects/{id}")
    public Response countProjects(@PathParam("id") Long id) {
        var amountProjects = this.einsaetzeRepo.countProjects(id);
        return Response.ok(amountProjects).build();
    }

    @GET
    @Path("/countProjektmanager/{id}")
    public Response countProjektmanager(@PathParam("id") Long id) {
        var amountProjektmanager = this.einsaetzeRepo.countProjektmanager(id);
        return Response.ok(amountProjektmanager).build();
    }

    @GET
    @Path("/countFachkoordinator/{id}")
    public Response countFachkoordinator(@PathParam("id") Long id) {
        var amountFachkoordinator = this.einsaetzeRepo.countFachkoordinator(id);
        return Response.ok(amountFachkoordinator).build();
    }

    @GET
    @Path("/getEinsaetzePerPerson/{id}")
    public Response getEinsaetzePerPerson(@PathParam("id") Long id) {
        var einsaetze = this.einsaetzeRepo.getEinsaetzePerPerson(id);
        return Response.ok(einsaetze).build();
    }

    @GET
    @Path("/all")
    public Response getAll() {
        var all = this.einsaetzeRepo.listAll();
        return Response.ok(all).build();
    }

    @GET
    @Path("/getByID/{rollen_id}/{personen_id}/{projekte_id}")
    public Response getById(@PathParam("rollen_id") Long rollen_id, @PathParam("personen_id") Long personen_id, @PathParam("projekte_id") Long projekte_id) {
        var found = this.einsaetzeRepo.getById(rollen_id, personen_id, projekte_id);
        return Response.ok(found).build();
    }

    @POST
    @Path("/add")
    @Transactional
    public Response add(Einsaetze einsaetze) {
        var checkKey = this.einsaetzeRepo.getById(einsaetze.getEinsaetze_id().getRollen_id().getRollen_id(), einsaetze.getEinsaetze_id().getPersonen_id().getPersonen_id(), einsaetze.getEinsaetze_id().getProjekte_id().getProjekt_id());
        if(!checkKey.isEmpty()) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity("Error or already inserted!").build();
        }
        try {
            einsaetzeRepo.persist(einsaetze);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(einsaetze).build();
    }

    @PUT
    @Path("/update")
    @Transactional
    public Response update(Einsaetze einsaetze) {
        try {
            einsaetzeRepo.update(einsaetze);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(einsaetze).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        try {
            einsaetzeRepo.deleteById(id);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(id).build();
    }
}