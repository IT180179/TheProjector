package com.example.api;

import com.aayushatharva.brotli4j.decoder.DecoderJNI;
import com.example.workloads.Abteilungen.Abteilungen;
import com.example.workloads.Abteilungen.AbteilungenRepo;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.resource.spi.SecurityPermission;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/abteilungen")
@RolesAllowed({"admin", "user"})
public class AbteilungenResource {

    private final AbteilungenRepo abteilungenRepo;

    public AbteilungenResource(AbteilungenRepo abteilungenRepo) {
        this.abteilungenRepo = abteilungenRepo;
    }

    @GET
    @Path("/countAllArbeitsstunden/{id}")
    @RolesAllowed("admin")
    public Response countAllArbeitsstunden(@PathParam("id") Long id) {
        var allArbeitsstunden = this.abteilungenRepo.countAllArbeitsstunden(id);
        return Response.ok(allArbeitsstunden).build();
    }

    @GET
    @Path("/countArbeitsstunden/{abteilungs_id}/{projekt_id}")
    @RolesAllowed("admin")
    public Response countArbeitsstunden(@PathParam("abteilungs_id") Long abteilungs_id, @PathParam("projekt_id") Long projekt_id) {
        var arbeitsstunden = this.abteilungenRepo.countArbeitsstunden(abteilungs_id, projekt_id);
        return Response.ok(arbeitsstunden).build();
    }

    @GET
    @Path("/getAbteilungenOfBereich/{id}")
    public Response getAbteilungenOfBereich(@PathParam("id") Long id) {
        var foundAbteilungen = this.abteilungenRepo.getAbteilungenOfBereich(id);
        return Response.ok(foundAbteilungen).build();
    }

    @GET
    @Path("/getPersonenOfAbteilung/{id}")
    @RolesAllowed("admin")
    public Response getPersonenOfAbteilung(@PathParam("id") Long id) {
        var foundPersonen = this.abteilungenRepo.getPersonenOfAbteilung(id);
        return Response.ok(foundPersonen).build();
    }

    @GET
    @Path("/all")
    public Response getAll() {
        var all = this.abteilungenRepo.listAll();
        return Response.ok(all).build();
    }

    @GET
    @Path("/getByID/{id}")
    public Response getById(@PathParam("id") Long id) {
        var found = this.abteilungenRepo.findById(id);
        return Response.ok(found).build();
    }

    @POST
    @Path("/add")
    @Transactional
    public Response add(Abteilungen abteilungen) {
        if (abteilungen == null){
            return Response.status(404).build();
        }
        try {
            abteilungenRepo.persist(abteilungen);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(abteilungen).build();
    }

    @PUT
    @Path("/update")
    @Transactional
    public Response update(Abteilungen abteilungen) {
        try {
            abteilungenRepo.update(abteilungen);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(abteilungen).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        try {
            abteilungenRepo.deleteById(id);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(id).build();
    }
}