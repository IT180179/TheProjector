package com.example.api;

import com.example.workloads.Folien.Folien;
import com.example.workloads.Folien.FolienRepo;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/folien")
@RolesAllowed("admin")
public class FolienResource {

    private final FolienRepo folienRepo;

    public FolienResource(FolienRepo folienRepo) {
        this.folienRepo = folienRepo;
    }

    @GET
    @Path("/all")
    public Response getAll() {
        var all = this.folienRepo.listAll();
        return Response.ok(all).build();
    }

    @GET
    @Path("/getByID/{id}")
    public Response getById(@PathParam("id") Long id) {
        var found = this.folienRepo.findById(id);
        return Response.ok(found).build();
    }

    @POST
    @Path("/add")
    @Transactional
    public Response add(Folien folien) {
        try {
            folienRepo.persist(folien);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(folien).build();
    }

    @PUT
    @Path("/update")
    @Transactional
    public Response update(Folien folien) {
        try {
            folienRepo.update(folien);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(folien).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        try {
            folienRepo.deleteById(id);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(id).build();
    }
}