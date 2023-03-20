package com.example.api;

import com.example.model.FreieFolienDTO;
import com.example.workloads.FreieFolien.FreieFolien;
import com.example.workloads.FreieFolien.FreieFolienRepo;
import com.example.workloads.Personen.Personen;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Base64;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/freiefolien")
@RolesAllowed("admin")
public class FreieFolienResource {

    private final FreieFolienRepo freieFolienRepo;

    public FreieFolienResource(FreieFolienRepo freieFolienRepo) {
        this.freieFolienRepo = freieFolienRepo;
    }

    @GET
    @Path("/all")
    public Response getAll() {
        var all = this.freieFolienRepo.listAll();
        return Response.ok(all).build();
    }

    @GET
    @Path("/getByID/{id}")
    public Response getById(@PathParam("id") Long id) {
        var found = this.freieFolienRepo.findById(id);
        return Response.ok(found).build();
    }

    @POST
    @Path("/add")
    @Transactional
    public Response add(FreieFolienDTO freieFolienDTO) {
        byte[] bild = null;
        bild = Base64.getEncoder().encode(freieFolienDTO.getBild().getBytes());
        FreieFolien freieFolien = new FreieFolien();
        try {
            freieFolien.setTitel(freieFolienDTO.getTitel());
            freieFolien.setUntertitel(freieFolienDTO.getUntertitel());
            freieFolien.setBeschreibung(freieFolienDTO.getBeschreibung());
            freieFolien.setFreitext(freieFolienDTO.getFreitext());
            freieFolien.setUpload(bild);
            freieFolien.setPpk_projekte_id(freieFolienDTO.getPpk_projekte_id());
            freieFolienRepo.persist(freieFolien);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(freieFolien).build();
    }

    @PUT
    @Path("/update")
    @Transactional
    public Response update(FreieFolien freieFolien) {
        try {
            freieFolienRepo.update(freieFolien);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(freieFolien).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        try {
            freieFolienRepo.deleteById(id);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(id).build();
    }
}