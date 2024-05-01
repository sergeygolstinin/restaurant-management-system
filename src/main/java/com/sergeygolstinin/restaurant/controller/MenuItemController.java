package com.sergeygolstinin.restaurant.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import com.sergeygolstinin.restaurant.model.MenuItem;
import com.sergeygolstinin.restaurant.service.MenuItemService;

import jakarta.inject.Inject;

@Path("/menuItems")
public class MenuItemController {
    @Inject
    private MenuItemService menuItemService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMenuItem(@PathParam("id") Long id) {
        try {
            MenuItem menuItem = menuItemService.findMenuItem(id);
            if (menuItem == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Menu item not found").build();
            }
            return Response.ok(menuItem).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving the menu item").build();
        }
    }

    // Add other endpoints as needed
}
