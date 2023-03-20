package com.example.api;

import com.example.workloads.PPKategorien.PPKategorie;
import com.example.workloads.PPKategorien.PPKategorieRepo;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/ppkategorien")
@RolesAllowed("admin")
public class PPKategorienResource {

    private final PPKategorieRepo ppKategorieRepo;

    public PPKategorienResource(PPKategorieRepo ppKategorieRepo) {
        this.ppKategorieRepo = ppKategorieRepo;
    }

    @GET
    @Path("/all")
    public Response getAll() {
        var allPersonen = this.ppKategorieRepo.listAll();
        return Response.ok(allPersonen).build();
    }

    @GET
    @Path("/getByID/{id}")
    public Response getById(@PathParam("id") Long id) {
        var found = this.ppKategorieRepo.findById(id);
        return Response.ok(found).build();
    }

    @POST
    @Path("/add")
    @Transactional
    public Response add(PPKategorie ppKategorie) {
        try {
            ppKategorieRepo.persist(ppKategorie);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(ppKategorie).build();
    }

    @PUT
    @Path("/update")
    @Transactional
    public Response update(PPKategorie ppKategorie) {
        try {
            ppKategorieRepo.update(ppKategorie);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(ppKategorie).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        try {
            ppKategorieRepo.deleteById(id);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(id).build();
    }
}