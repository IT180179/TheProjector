package com.example.api;

import com.example.workloads.Abteilungen.Abteilungen;
import com.example.workloads.Arbeitszeiten.Arbeitszeiten;
import com.example.workloads.Arbeitszeiten.ArbeitszeitenRepo;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/arbeitszeiten")
@RolesAllowed({"admin", "user"})
public class ArbeitszeitenResource {

    private final ArbeitszeitenRepo arbeitszeitenRepo;

    public ArbeitszeitenResource(ArbeitszeitenRepo arbeitszeitenRepo) {
        this.arbeitszeitenRepo = arbeitszeitenRepo;
    }

    @GET
    @Path("/getArbeitszeitenPerDate/{start_date}/{end_date}")
    public Response getArbeitszeitenPerDate(@PathParam("start_date") String start_date, @PathParam("end_date") String end_date) {
        var allArbeitszeitPerDate = this.arbeitszeitenRepo.getArbeitszeitenPerDate(start_date, end_date);
        return Response.ok(allArbeitszeitPerDate).build();
    }

    @GET
    @Path("/countArbeitszeitPerPerson/{personen_id}")
    public Response countArbeitszeitPerPerson(@PathParam("personen_id") Long personen_id) {
        var allArbeitszeit = this.arbeitszeitenRepo.countArbeitszeitPerPerson(personen_id);
        return Response.ok(allArbeitszeit).build();
    }

    @GET
    @Path("/countArbeitszeitPerPersonPerProjekt/{personen_id}/{projekt_id}")
    public Response countArbeitszeitPerPersonPerProjekt(@PathParam("personen_id") Long personen_id, @PathParam("projekt_id") Long projekt_id) {
        var allArbeitszeitPerProjekt = this.arbeitszeitenRepo.countArbeitszeitPerPersonPerProjekt(personen_id, projekt_id);
        return Response.ok(allArbeitszeitPerProjekt).build();
    }

    @GET
    @Path("/countArbeitszeitPerPersonPerProjektPerRolle/{personen_id}/{projekt_id}/{rollen_id}")
    public Response countArbeitszeitPerPersonPerProjektPerRolle(@PathParam("personen_id") Long personen_id, @PathParam("projekt_id") Long projekt_id, @PathParam("rollen_id") Long rollen_id) {
        var allArbeitszeitPerPersonPerProjektPerRolle = this.arbeitszeitenRepo.countArbeitszeitPerPersonPerProjektPerRolle(personen_id, projekt_id, rollen_id);
        return Response.ok(allArbeitszeitPerPersonPerProjektPerRolle).build();
    }

    @GET
    @Path("/getArbeitszeitenPerPerson/{personen_id}")
    public Response getArbeitszeitenPerPerson(@PathParam("personen_id") Long personen_id) {
        var allArbeitszeiten = this.arbeitszeitenRepo.getArbeitszeitenPerPerson(personen_id);
        return Response.ok(allArbeitszeiten).build();
    }

    @GET
    @Path("/getArbeitszeitenPerProjekt/{projekte_id}")
    public Response getArbeitszeitenPerProjekt(@PathParam("projekte_id") Long projekte_id) {
        var allArbeitszeitenPerProjekt = this.arbeitszeitenRepo.getArbeitszeitenPerProjekt(projekte_id);
        return Response.ok(allArbeitszeitenPerProjekt).build();
    }

    @GET
    @Path("/getArbeitszeitenPerPersonPerProjekt/{personen_id}/{projekt_id}")
    public Response getArbeitszeitenPerPersonPerProjekt(@PathParam("personen_id") Long personen_id, @PathParam("projekt_id") Long projekt_id) {
        var allArbeitszeitenPerProjekt = this.arbeitszeitenRepo.getArbeitszeitenPerPersonPerProjekt(personen_id, projekt_id);
        return Response.ok(allArbeitszeitenPerProjekt).build();
    }

    @GET
    @Path("/all")
    public Response getAll() {
        var all = this.arbeitszeitenRepo.listAll();
        return Response.ok(all).build();
    }

    @GET
    @Path("/getByID/{id}")
    public Response getById(@PathParam("id") Long id) {
        var found = this.arbeitszeitenRepo.findById(id);
        return Response.ok(found).build();
    }

    @POST
    @Path("/add")
    @Transactional
    public Response add(Arbeitszeiten arbeitszeiten) {
        try {
            arbeitszeitenRepo.persist(arbeitszeiten);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(arbeitszeiten).build();
    }

    @PUT
    @Path("/update")
    @Transactional
    public Response update(Arbeitszeiten arbeitszeiten) {
        try {
            arbeitszeitenRepo.update(arbeitszeiten);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(arbeitszeiten).build();
    }

    @DELETE
    @Path("/delete{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        try {
            arbeitszeitenRepo.deleteById(id);
        } catch (Exception exception) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
        }
        return Response.ok(id).build();
    }
}