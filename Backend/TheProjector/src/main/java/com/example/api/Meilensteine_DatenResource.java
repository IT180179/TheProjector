package com.example.api;

import com.example.workloads.Meilensteine_Daten.Meilensteine_Daten;
import com.example.workloads.Meilensteine_Daten.Meilensteine_DatenRepo;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/meilensteine_daten")
@RolesAllowed("admin")
public class Meilensteine_DatenResource {

    private final Meilensteine_DatenRepo meilensteine_datenRepo;

    public Meilensteine_DatenResource(Meilensteine_DatenRepo meilensteine_datenRepo) {
        this.meilensteine_datenRepo = meilensteine_datenRepo;
    }

    @GET
    @Path("/all")
    public Response getAll() {
        var all = this.meilensteine_datenRepo.listAll();
        return Response.ok(all).build();
    }

    @GET
    @Path("/getByID/{id}")
    public Response getById(@PathParam("id") Long id) {
        var found = this.meilensteine_datenRepo.findById(id);
        return Response.ok(found).build();
    }

    @POST
    @Path("/add")
    @Transactional
    public Response add(Meilensteine_Daten meilensteine_daten) {
        try {
            meilensteine_datenRepo.persist(meilensteine_daten);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(meilensteine_daten).build();
    }

    @PUT
    @Path("/update")
    @Transactional
    public Response update(Meilensteine_Daten meilensteine_daten) {
        try {
            meilensteine_datenRepo.update(meilensteine_daten);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(meilensteine_daten).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        try {
            meilensteine_datenRepo.deleteById(id);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(id).build();
    }
}