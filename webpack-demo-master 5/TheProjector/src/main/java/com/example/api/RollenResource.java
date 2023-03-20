package com.example.api;

import com.example.workloads.Rollen.Rollen;
import com.example.workloads.Rollen.RollenRepo;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/rollen")
@RolesAllowed({"admin", "user"})
public class RollenResource {

    private final RollenRepo rollenRepo;

    public RollenResource(RollenRepo rollenRepo) {
        this.rollenRepo = rollenRepo;
    }

    @GET
    @Path("/all")
    public Response getAll() {
        var allPersonen = this.rollenRepo.listAll();
        return Response.ok(allPersonen).build();
    }

    @GET
    @Path("/getByID/{id}")
    public Response getById(@PathParam("id") Long id) {
        var found = this.rollenRepo.findById(id);
        return Response.ok(found).build();
    }

    @POST
    @Path("/add")
    @Transactional
    public Response add(Rollen rollen) {
        try {
            rollenRepo.persist(rollen);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(rollen).build();
    }

    @PUT
    @Path("/update")
    @Transactional
    public Response update(Rollen rollen) {
        try {
            rollenRepo.update(rollen);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(rollen).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        try {
            rollenRepo.deleteById(id);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(id).build();
    }
}