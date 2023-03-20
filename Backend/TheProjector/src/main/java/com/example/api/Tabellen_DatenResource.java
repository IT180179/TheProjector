package com.example.api;

import com.example.workloads.Tabellen_Daten.Tabellen_Daten;
import com.example.workloads.Tabellen_Daten.Tabellen_DatenRepo;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/tabellen_daten")
@RolesAllowed("admin")
public class Tabellen_DatenResource {

    private final Tabellen_DatenRepo tabellen_datenRepo;

    public Tabellen_DatenResource(Tabellen_DatenRepo tabellen_datenRepo) {
        this.tabellen_datenRepo = tabellen_datenRepo;
    }

    @GET
    @Path("/all")
    public Response getAll() {
        var allPersonen = this.tabellen_datenRepo.listAll();
        return Response.ok(allPersonen).build();
    }

    @GET
    @Path("/getByID/{id}")
    public Response getById(@PathParam("id") Long id) {
        var found = this.tabellen_datenRepo.findById(id);
        return Response.ok(found).build();
    }

    @POST
    @Path("/add")
    @Transactional
    public Response add(Tabellen_Daten tabellen_daten) {
        try {
            tabellen_datenRepo.persist(tabellen_daten);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(tabellen_daten).build();
    }

    @PUT
    @Path("/update")
    @Transactional
    public Response update(Tabellen_Daten tabellen_daten) {
        try {
            tabellen_datenRepo.update(tabellen_daten);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(tabellen_daten).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        try {
            tabellen_datenRepo.deleteById(id);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(id).build();
    }
}