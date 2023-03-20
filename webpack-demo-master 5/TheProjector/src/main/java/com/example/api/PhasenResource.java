package com.example.api;

import com.example.workloads.Phasen.Phasen;
import com.example.workloads.Phasen.PhasenRepo;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/phasen")
@RolesAllowed("admin")
public class PhasenResource {

    private final PhasenRepo phasenRepo;

    public PhasenResource(PhasenRepo phasenRepo) {
        this.phasenRepo = phasenRepo;
    }

    @GET
    @Path("/all")
    public Response getAll() {
        var all = this.phasenRepo.listAll();
        return Response.ok(all).build();
    }

    @GET
    @Path("/getByID/{id}")
    public Response getById(@PathParam("id") Long id) {
        var found = this.phasenRepo.findById(id);
        return Response.ok(found).build();
    }

    @POST
    @Path("/add")
    @Transactional
    public Response add(Phasen phasen) {
        try {
            phasenRepo.persist(phasen);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(phasen).build();
    }

    @PUT
    @Path("/update")
    @Transactional
    public Response update(Phasen phasen) {
        try {
            phasenRepo.update(phasen);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(phasen).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        try {
            phasenRepo.deleteById(id);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(id).build();
    }
}