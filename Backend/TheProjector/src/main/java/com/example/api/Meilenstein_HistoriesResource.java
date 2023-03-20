package com.example.api;

import com.example.workloads.Meilenstein_Histories.Meilenstein_Histories;
import com.example.workloads.Meilenstein_Histories.Meilenstein_HistoriesRepo;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/meilenstein_histories")
//@RolesAllowed({"admin", "user"})
public class Meilenstein_HistoriesResource {

    private final Meilenstein_HistoriesRepo meilenstein_historiesRepo;

    public Meilenstein_HistoriesResource(Meilenstein_HistoriesRepo meilenstein_historiesRepo) {
        this.meilenstein_historiesRepo = meilenstein_historiesRepo;
    }

    @GET
    @Path("/getHistory/{id}")
    public Response getHistory(@PathParam("id") Long id) {
        var history = this.meilenstein_historiesRepo.getHistory(id);
        return Response.ok(history).build();
    }

    @GET
    @Path("/all")
    public Response getAll() {
        var all = this.meilenstein_historiesRepo.listAll();
        return Response.ok(all).build();
    }

    @GET
    @Path("/getByID/{id}")
    public Response getById(@PathParam("id") Long id) {
        var found = this.meilenstein_historiesRepo.findById(id);
        return Response.ok(found).build();
    }

    @POST
    @Path("/add")
    @Transactional
    public Response add(Meilenstein_Histories meilenstein_histories) {
        try {
            meilenstein_historiesRepo.persist(meilenstein_histories);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(meilenstein_histories).build();
    }

    @PUT
    @Path("/update")
    @Transactional
    public Response update(Meilenstein_Histories meilenstein_histories) {
        try {
            meilenstein_historiesRepo.update(meilenstein_histories);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(meilenstein_histories).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        try {
            meilenstein_historiesRepo.deleteById(id);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(id).build();
    }

    @DELETE
    @Path("/deletePerMeilensteinId/{id}")
    @Transactional
    public Response deletePerMeilensteinId(@PathParam("id") Long id) {
        try {
            meilenstein_historiesRepo.deletePerMeilensteinId(id);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(id).build();
    }
}