package com.example.api;

import com.example.workloads.Gaeste.Gaeste;
import com.example.workloads.Gaeste.GaesteRepo;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/gaeste")
@RolesAllowed("admin")
public class GaesteResource {

    private final GaesteRepo gaesteRepo;

    public GaesteResource(GaesteRepo gaesteRepo) {
        this.gaesteRepo = gaesteRepo;
    }

    @GET
    @Path("/all")
    public Response getAll() {
        var all = this.gaesteRepo.listAll();
        return Response.ok(all).build();
    }

    @GET
    @Path("/getByID/{id}")
    public Response getById(@PathParam("id") Long id) {
        var found = this.gaesteRepo.findById(id);
        return Response.ok(found).build();
    }

    @POST
    @Path("/add")
    @Transactional
    public Response add(Gaeste gaeste) {
        try {
            gaesteRepo.persist(gaeste);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(gaeste).build();
    }

    @PUT
    @Path("/update")
    @Transactional
    public Response update(Gaeste gaeste) {
        try {
            gaesteRepo.update(gaeste);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(gaeste).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        try {
            gaesteRepo.deleteById(id);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(id).build();
    }
}