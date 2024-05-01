package com.sergeygolstinin.restaurant.controller;

import com.sergeygolstinin.restaurant.model.User;
import com.sergeygolstinin.restaurant.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Inject
    private UserService userService;

    public UserController() {
        // This empty constructor might be required depending on your DI framework setup
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") Long id) {
        if (id == null || id <= 0) {
            log.error("Invalid user ID provided: {}", id);
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("Invalid user ID provided.").build();
        }
        try {
            User user = userService.getUserById(id);
            if (user == null) {
                log.warn("User not found for ID: {}", id);
                return Response.status(Response.Status.NOT_FOUND).entity("User not found.").build();
            }
            return Response.ok(user).build();
        } catch (Exception e) {
            log.error("Error retrieving user with ID: {}", id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error retrieving user.").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        try {
            User newUser = userService.createUser(user);
            return Response.status(Response.Status.CREATED).entity(newUser).build();
        } catch (Exception e) {
            log.error("Error creating user", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error creating user.").build();
        }
    }
    
    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        try {
            userService.deleteUser(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            log.error("Error deleting user with ID: {}", id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error deleting user.").build();
        }
    }

}