package com.example.api;

import com.example.workloads.Bereiche.Bereiche;
import com.example.workloads.Bereiche.BereicheRepo;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/bereiche")
@RolesAllowed({"admin", "user"})
public class BereicheResource {

    private final BereicheRepo bereicheRepo;

    public BereicheResource(BereicheRepo bereicheRepoImpl) {
        this.bereicheRepo = bereicheRepoImpl;
    }

    @GET
    @Path("/countAllArbeitsstunden/{id}")
    @RolesAllowed("admin")
    public Response countAllArbeitsstunden(@PathParam("id") Long id) {
        var allArbeitsstunden = this.bereicheRepo.countAllArbeitsstunden(id);
        return Response.ok(allArbeitsstunden).build();
    }

    @GET
    @Path("/countArbeitsstunden/{bereichs_id}/{projekt_id}")
    @RolesAllowed("admin")
    public Response countArbeitsstunden(@PathParam("bereichs_id") Long bereichs_id, @PathParam("projekt_id") Long projekte_id) {
        var arbeitsstunden = this.bereicheRepo.countArbeitsstunden(bereichs_id, projekte_id);
        return Response.ok(arbeitsstunden).build();
    }

    @GET
    @Path("/all")
    public Response getAll() {
        var all = this.bereicheRepo.listAll();
        return Response.ok(all).build();
    }

    @GET
    @Path("/getByID/{id}")
    public Response getById(@PathParam("id") Long id) {
        var found = this.bereicheRepo.findById(id);
        return Response.ok(found).build();
    }

    @POST
    @Path("/add")
    @Transactional
    public Response add(Bereiche bereiche) {
        try {
            bereicheRepo.persist(bereiche);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(bereiche).build();
    }

    @PUT
    @Path("/update")
    @Transactional
    public Response update(Bereiche bereiche) {
        try {
            bereicheRepo.update(bereiche);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(bereiche).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        try {
            bereicheRepo.deleteById(id);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(id).build();
    }
}