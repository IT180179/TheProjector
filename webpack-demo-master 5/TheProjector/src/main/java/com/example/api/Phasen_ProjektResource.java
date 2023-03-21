package com.example.api;

import com.example.workloads.Phasen_Projekt.Phasen_Projekt;
import com.example.workloads.Phasen_Projekt.Phasen_ProjektRepo;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/phasen_projekt")
@RolesAllowed("admin")
public class Phasen_ProjektResource {

    private final Phasen_ProjektRepo phasen_projektRepo;

    public Phasen_ProjektResource(Phasen_ProjektRepo phasen_projektRepo) {
        this.phasen_projektRepo = phasen_projektRepo;
    }

    @GET
    @Path("/all")
    public Response getAll() {
        var all = this.phasen_projektRepo.listAll();
        return Response.ok(all).build();
    }

    @GET
    @Path("/getByID/{id}")
    public Response getById(@PathParam("id") Long id) {
        var found = this.phasen_projektRepo.findById(id);
        return Response.ok(found).build();
    }

    @POST
    @Path("/add")
    @Transactional
    public Response add(Phasen_Projekt phasen_projekt) {
        try {
            phasen_projektRepo.persist(phasen_projekt);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(phasen_projekt).build();
    }

    @PUT
    @Path("/update")
    @Transactional
    public Response update(Phasen_Projekt phasen_projekt) {
        try {
            phasen_projektRepo.update(phasen_projekt);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(phasen_projekt).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        try {
            phasen_projektRepo.deleteById(id);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(id).build();
    }
}