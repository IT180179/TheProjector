package com.example.api;

import com.example.workloads.Ressourcen.Ressourcen;
import com.example.workloads.Ressourcen.RessourcenRepo;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/ressourcen")
@RolesAllowed("admin")
public class RessourcenResource {

    private final RessourcenRepo ressourcenRepo;

    public RessourcenResource(RessourcenRepo ressourcenRepo) {
        this.ressourcenRepo = ressourcenRepo;
    }

    @GET
    @Path("/all")
    public Response getAll() {
        var allPersonen = this.ressourcenRepo.listAll();
        return Response.ok(allPersonen).build();
    }

    @GET
    @Path("/getByID/{personen_id}/{projekte_id}/{ppk_id}")
    public Response getById(@PathParam("ppk_id") Long ppk_id, @PathParam("personen_id") Long personen_id, @PathParam("projekte_id") Long projekte_id) {
        var found = this.ressourcenRepo.getById(ppk_id, personen_id, projekte_id);
        return Response.ok(found).build();
    }

    @POST
    @Path("/add")
    @Transactional
    public Response add(Ressourcen ressourcen) {
        var checkKey = this.ressourcenRepo.getById(ressourcen.getRessourcen_id().getPpk_projekte_id().getPpk_projekte_id().getPpk_id().getPpk_id(), ressourcen.getRessourcen_id().getPersonen_id().getPersonen_id(), ressourcen.getRessourcen_id().getPpk_projekte_id().getPpk_projekte_id().getProjekte_id().getProjekt_id());
        if(!checkKey.isEmpty()) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity("Error or already inserted!").build();
        }
        try {
            ressourcenRepo.persist(ressourcen);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(ressourcen).build();
    }

    @PUT
    @Path("/update")
    @Transactional
    public Response update(Ressourcen ressourcen) {
        try {
            ressourcenRepo.update(ressourcen);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(ressourcen).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        try {
            ressourcenRepo.deleteById(id);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(id).build();
    }
}