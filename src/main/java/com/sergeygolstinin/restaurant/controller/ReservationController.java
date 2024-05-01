package com.sergeygolstinin.restaurant.controller;

import com.sergeygolstinin.restaurant.dao.ReservationRepository;
import com.sergeygolstinin.restaurant.model.Reservation;
import com.sergeygolstinin.restaurant.service.ReservationService;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.inject.Inject;

@Path("/reservations")
public class ReservationController {
    private ReservationService reservationService;
    
    @Inject
    public ReservationController(EntityManager entityManager, ReservationRepository reservationRepository) {
        this.reservationService = new ReservationService(entityManager, reservationRepository);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createReservation(Reservation reservation) {
        try {
            Reservation createdReservation = reservationService.createReservation(reservation);
            return Response.status(Response.Status.CREATED).entity(createdReservation).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error creating reservation").build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReservationById(@PathParam("id") Long id) {
        try {
            Reservation reservation = reservationService.getReservationById(id);
            if (reservation == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Reservation not found").build();
            }
            return Response.ok(reservation).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving reservation").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateReservation(@PathParam("id") Long id, Reservation reservation) {
        try {
            reservation.setId(id); // Ensure the ID is set correctly
            Reservation updatedReservation = reservationService.updateReservation(reservation);
            return Response.ok(updatedReservation).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error updating reservation").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteReservation(@PathParam("id") Long id) {
        try {
            reservationService.deleteReservation(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error deleting reservation").build();
        }
    }
}
