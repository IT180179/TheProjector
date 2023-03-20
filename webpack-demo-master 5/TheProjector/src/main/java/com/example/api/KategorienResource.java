package com.example.api;

import com.example.workloads.Kategorien.Kategorien;
import com.example.workloads.Kategorien.KategorienRepo;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/kategorien")
@RolesAllowed("admin")
public class KategorienResource {

    private final KategorienRepo kategorienRepo;

    public KategorienResource(KategorienRepo kategorienRepo) {
        this.kategorienRepo = kategorienRepo;
    }

    @GET
    @Path("/all")
    public Response getAll() {
        var all = this.kategorienRepo.listAll();
        return Response.ok(all).build();
    }

    @GET
    @Path("/getByID/{id}")
    public Response getById(@PathParam("id") Long id) {
        var found = this.kategorienRepo.findById(id);
        return Response.ok(found).build();
    }

    @POST
    @Path("/add")
    @Transactional
    public Response add(Kategorien kategorien) {
        try {
            kategorienRepo.persist(kategorien);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(kategorien).build();
    }

    @PUT
    @Path("/update")
    @Transactional
    public Response update(Kategorien kategorien) {
        try {
            kategorienRepo.update(kategorien);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(kategorien).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        try {
            kategorienRepo.deleteById(id);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(id).build();
    }
}