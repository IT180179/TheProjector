package com.example.api;

import com.example.workloads.PPK.PPK;
import com.example.workloads.PPK.PPKRepo;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/ppk")
//@RolesAllowed("admin")
public class PPKRessource {

    private final PPKRepo ppkRepo;

    public PPKRessource(PPKRepo ppkRepo) {
        this.ppkRepo = ppkRepo;
    }

    @GET
    @Path("/getGaesteOfPPK/{ppk_id}")
    public Response getGaesteOfPPK(@PathParam("ppk_id") Long ppk_id) {
        var gaeste = this.ppkRepo.getGaesteOfPPK(ppk_id);
        return Response.ok(gaeste).build();
    }

    @GET
    @Path("/all")
    public Response getAll() {
        var allPersonen = this.ppkRepo.getAllNewSorted();
        return Response.ok(allPersonen).build();
    }

    @GET
    @Path("/getByID/{id}")
    public Response getById(@PathParam("id") Long id) {
        var found = this.ppkRepo.findById(id);
        return Response.ok(found).build();
    }

    @GET
    @Path("/getNextPPK")
    public Response getNextPPK() {
        var nextPPK = this.ppkRepo.getNextPPK();
        return Response.ok(nextPPK).build();
    }

    @GET
    @Path("/getNextPPKWithProjektAndGaeste")
    public Response getNextPPKWithProjektAndGaeste() {
        var nextPPKWithProjekt = this.ppkRepo.getNextPPKWithProjektAndGaeste();
        return Response.ok(nextPPKWithProjekt).build();
    }

    @POST
    @Path("/add")
    @Transactional
    public Response add(PPK ppk) {
        try {
            ppkRepo.persist(ppk);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(ppk).build();
    }

    @PUT
    @Path("/update")
    @Transactional
    public Response update(PPK ppk) {
        try {
            ppkRepo.update(ppk);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(ppk).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        try {
            ppkRepo.deleteById(id);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(id).build();
    }
}
