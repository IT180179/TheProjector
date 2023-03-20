package com.example.api;

import com.example.workloads.Logos_Daten.Logos_Daten;
import com.example.workloads.Logos_Daten.Logos_DatenRepo;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/logos_daten")
@RolesAllowed("admin")
public class Logos_DatenResource {

    private final Logos_DatenRepo logos_datenRepo;

    public Logos_DatenResource(Logos_DatenRepo logos_datenRepo) {
        this.logos_datenRepo = logos_datenRepo;
    }

    @GET
    @Path("/all")
    public Response getAll() {
        var all = this.logos_datenRepo.listAll();
        return Response.ok(all).build();
    }

    @GET
    @Path("/getByID/{id}")
    public Response getById(@PathParam("id") Long id) {
        var found = this.logos_datenRepo.findById(id);
        return Response.ok(found).build();
    }

    @POST
    @Path("/add")
    @Transactional
    public Response add(Logos_Daten logos_daten) {
        try {
            logos_datenRepo.persist(logos_daten);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(logos_daten).build();
    }

    @PUT
    @Path("/update")
    @Transactional
    public Response update(Logos_Daten logos_daten) {
        try {
            logos_datenRepo.update(logos_daten);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(logos_daten).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        try {
            logos_datenRepo.deleteById(id);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(id).build();
    }
}