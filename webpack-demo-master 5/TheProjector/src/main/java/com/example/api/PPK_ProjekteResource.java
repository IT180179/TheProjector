package com.example.api;

import com.example.workloads.PPK_Projekte.PPK_Projekte;
import com.example.workloads.PPK_Projekte.PPK_ProjekteRepo;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/ppk_projekte")
@RolesAllowed("admin")
public class PPK_ProjekteResource {

    private final PPK_ProjekteRepo ppk_projekteRepo;

    public PPK_ProjekteResource(PPK_ProjekteRepo ppk_projekteRepo) {
        this.ppk_projekteRepo = ppk_projekteRepo;
    }

    @GET
    @Path("/all")
    public Response getAll() {
        var allPersonen = this.ppk_projekteRepo.listAll();
        return Response.ok(allPersonen).build();
    }

    @GET
    @Path("/getByID/{ppk_id}/{projekte_id}")
    public Response getById(@PathParam("ppk_id") Long ppk_id, @PathParam("projekte_id") Long projekte_id) {
        var found = this.ppk_projekteRepo.getById(ppk_id, projekte_id);
        return Response.ok(found).build();
    }

    @POST
    @Path("/add")
    @Transactional
    public Response add(PPK_Projekte ppk_projekte) {
        var checkKey = this.ppk_projekteRepo.getById(ppk_projekte.getPpk_projekte_id().getPpk_id().getPpk_id(), ppk_projekte.getPpk_projekte_id().getProjekte_id().getProjekt_id());
        if(!checkKey.isEmpty()) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity("Error or already inserted!").build();
        }
        try {
            ppk_projekteRepo.persist(ppk_projekte);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(ppk_projekte).build();
    }

    @PUT
    @Path("/update")
    @Transactional
    public Response update(PPK_Projekte ppk_projekte) {
        try {
            ppk_projekteRepo.update(ppk_projekte);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(ppk_projekte).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        try {
            ppk_projekteRepo.deleteById(id);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(id).build();
    }
}