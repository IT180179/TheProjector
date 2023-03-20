package com.example.api;

import com.example.workloads.Titel_Daten.Titel_Daten;
import com.example.workloads.Titel_Daten.Titel_DatenRepo;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/titel_daten")
@RolesAllowed("admin")
public class Titel_DatenResource {

    private final Titel_DatenRepo titel_datenRepo;

    public Titel_DatenResource(Titel_DatenRepo titel_datenRepo) {
        this.titel_datenRepo = titel_datenRepo;
    }

    @GET
    @Path("/all")
    public Response getAll() {
        var allPersonen = this.titel_datenRepo.listAll();
        return Response.ok(allPersonen).build();
    }

    @GET
    @Path("/getByID/{id}")
    public Response getById(@PathParam("id") Long id) {
        var found = this.titel_datenRepo.findById(id);
        return Response.ok(found).build();
    }

    @POST
    @Path("/add")
    @Transactional
    public Response add(Titel_Daten titel_daten) {
        try {
            titel_datenRepo.persist(titel_daten);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(titel_daten).build();
    }

    @PUT
    @Path("/update")
    @Transactional
    public Response update(Titel_Daten titel_daten) {
        try {
            titel_datenRepo.update(titel_daten);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(titel_daten).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        try {
            titel_datenRepo.deleteById(id);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(id).build();
    }
}