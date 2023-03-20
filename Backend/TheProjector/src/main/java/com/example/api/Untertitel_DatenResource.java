package com.example.api;

import com.example.workloads.Untertitel_Daten.Untertitel_Daten;
import com.example.workloads.Untertitel_Daten.Untertitel_DatenRepo;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/untertitel_daten")
@RolesAllowed("admin")
public class Untertitel_DatenResource {

    private final Untertitel_DatenRepo untertitel_datenRepo;

    public Untertitel_DatenResource(Untertitel_DatenRepo untertitel_datenRepo) {
        this.untertitel_datenRepo = untertitel_datenRepo;
    }

    @GET
    @Path("/all")
    public Response getAll() {
        var allPersonen = this.untertitel_datenRepo.listAll();
        return Response.ok(allPersonen).build();
    }

    @GET
    @Path("/getByID/{id}")
    public Response getById(@PathParam("id") Long id) {
        var found = this.untertitel_datenRepo.findById(id);
        return Response.ok(found).build();
    }

    @POST
    @Path("/add")
    @Transactional
    public Response add(Untertitel_Daten untertitel_daten) {
        try {
            untertitel_datenRepo.persist(untertitel_daten);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(untertitel_daten).build();
    }

    @PUT
    @Path("/update")
    @Transactional
    public Response update(Untertitel_Daten untertitel_daten) {
        try {
            untertitel_datenRepo.update(untertitel_daten);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(untertitel_daten).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        try {
            untertitel_datenRepo.deleteById(id);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(id).build();
    }
}