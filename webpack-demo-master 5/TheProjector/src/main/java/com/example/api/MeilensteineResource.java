package com.example.api;

import com.example.workloads.Meilensteine.Meilensteine;
import com.example.workloads.Meilensteine.MeilensteineRepo;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/meilensteine")
@RolesAllowed({"admin", "user"})
public class MeilensteineResource {

    private final MeilensteineRepo meilensteineRepo;

    public MeilensteineResource(MeilensteineRepo meilensteineRepo) {
        this.meilensteineRepo = meilensteineRepo;
    }

    @GET
    @Path("/all")
    public Response getAll() {
        var all = this.meilensteineRepo.getAllSorted();
        return Response.ok(all).build();
    }

    @GET
    @Path("/getByID/{id}")
    public Response getById(@PathParam("id") Long id) {
        var found = this.meilensteineRepo.findById(id);
        return Response.ok(found).build();
    }

    @POST
    @Path("/add")
    @Transactional
    public Response add(Meilensteine meilensteine) {
        try {
            meilensteineRepo.persist(meilensteine);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(meilensteine).build();
    }

    @PUT
    @Path("/update")
    @Transactional
    public Response update(Meilensteine meilensteine) {
        try {
            meilensteineRepo.update(meilensteine);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(meilensteine).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        try {
            meilensteineRepo.deleteById(id);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(id).build();
    }
}
