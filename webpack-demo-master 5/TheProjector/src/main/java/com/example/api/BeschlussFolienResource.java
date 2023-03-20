package com.example.api;

import com.example.workloads.BeschlussFolien.BeschlussFolien;
import com.example.workloads.BeschlussFolien.BeschlussFolienRepo;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/beschlussfolien")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed("admin")
public class BeschlussFolienResource {

    private final BeschlussFolienRepo beschlussFolienRepo;

    public BeschlussFolienResource(BeschlussFolienRepo beschlussFolienRepo) {
        this.beschlussFolienRepo = beschlussFolienRepo;
    }

    @GET
    @Path("/all")
    public Response getAll() {
        var all = this.beschlussFolienRepo.listAll();
        return Response.ok(all).build();
    }

    @GET
    @Path("/getByID/{id}")
    public Response getById(@PathParam("id") Long id) {
        var found = this.beschlussFolienRepo.findById(id);
        return Response.ok(found).build();
    }

    @POST
    @Path("/add")
    @Transactional
    public Response add(BeschlussFolien beschlussFolien) {
        try {
            beschlussFolienRepo.persist(beschlussFolien);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(beschlussFolien).build();
    }

    @PUT
    @Path("/update")
    @Transactional
    public Response update(BeschlussFolien beschlussFolien) {
        try {
            beschlussFolienRepo.update(beschlussFolien);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(beschlussFolien).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        try {
            beschlussFolienRepo.deleteById(id);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(id).build();
    }
}