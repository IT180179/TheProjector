package com.example.api;

import com.example.workloads.Texte_Daten.Texte_Daten;
import com.example.workloads.Texte_Daten.Texte_DatenRepo;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/texte_daten")
@RolesAllowed("admin")
public class Texte_DatenResource {

    private final Texte_DatenRepo texte_datenRepo;

    public Texte_DatenResource(Texte_DatenRepo texte_datenRepo) {
        this.texte_datenRepo = texte_datenRepo;
    }

    @GET
    @Path("/all")
    public Response getAll() {
        var allPersonen = this.texte_datenRepo.listAll();
        return Response.ok(allPersonen).build();
    }

    @GET
    @Path("/getByID/{id}")
    public Response getById(@PathParam("id") Long id) {
        var found = this.texte_datenRepo.findById(id);
        return Response.ok(found).build();
    }

    @POST
    @Path("/add")
    @Transactional
    public Response add(Texte_Daten texte_daten) {
        try {
            texte_datenRepo.persist(texte_daten);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(texte_daten).build();
    }

    @PUT
    @Path("/update")
    @Transactional
    public Response update(Texte_Daten texte_daten) {
        try {
            texte_datenRepo.update(texte_daten);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(texte_daten).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        try {
            texte_datenRepo.deleteById(id);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(id).build();
    }
}