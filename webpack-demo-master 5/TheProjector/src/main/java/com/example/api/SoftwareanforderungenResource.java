package com.example.api;

import com.example.workloads.Phasen_Projekt.Phasen_ProjektRepo;
import com.example.workloads.Softwareanforderungen.Softwareanforderungen;
import com.example.workloads.Softwareanforderungen.SoftwareanforderungenRepo;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/softwareanforderungen")
@RolesAllowed("admin")
public class SoftwareanforderungenResource {

    private final SoftwareanforderungenRepo softwareanforderungenRepo;
    private final Phasen_ProjektRepo phasen_projektRepo;

    public SoftwareanforderungenResource(SoftwareanforderungenRepo softwareanforderungenRepo, Phasen_ProjektRepo phasen_projektRepo) {
        this.softwareanforderungenRepo = softwareanforderungenRepo;
        this.phasen_projektRepo = phasen_projektRepo;
    }

    @GET
    @Path("/all")
    public Response getAll() {
        var all = this.softwareanforderungenRepo.listAll();
        return Response.ok(all).build();
    }

    @GET
    @Path("/getByID/{id}")
    public Response getById(@PathParam("id") Long id) {
        var found = this.softwareanforderungenRepo.findById(id);
        return Response.ok(found).build();
    }

    @GET
    @Path("/maxId")
    public Response getMaxId() {
        var maxId = this.softwareanforderungenRepo.getMaxId();
        return Response.ok(maxId).build();
    }

    @POST
    @Path("/add/{status1}/{status2}/{status3}/{status4}/{status5}/{status6}/{status7}")
    @Transactional
    public Response add(Softwareanforderungen softwareanforderungen,@PathParam("status1") int status1,@PathParam("status2") int status2,@PathParam("status3") int status3,@PathParam("status4") int status4,@PathParam("status5") int status5,@PathParam("status6") int status6,@PathParam("status7") int status7) {
        try {
            softwareanforderungenRepo.persist(softwareanforderungen);
            phasen_projektRepo.insert(status1, status2, status3, status4, status5, status6, status7);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(softwareanforderungen).build();
    }

    @PUT
    @Path("/update")
    @Transactional
    public Response update(Softwareanforderungen softwareanforderungen) {
        try {
            softwareanforderungenRepo.update(softwareanforderungen);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(softwareanforderungen).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        try {
            softwareanforderungenRepo.deleteById(id);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(id).build();
    }
}