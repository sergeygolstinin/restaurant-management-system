package com.sergeygolstinin.restaurant.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sergeygolstinin.restaurant.model.MenuItem;
import com.sergeygolstinin.restaurant.service.MenuItemService;

import jakarta.inject.Inject;

@Path("/menuItems")
public class MenuItemController {
    private static final Logger log = LoggerFactory.getLogger(MenuItemController.class);

    @Inject
    private MenuItemService menuItemService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMenuItem(@PathParam("id") Long id) {
        log.debug("Request to get menu item with id: {}", id);
        try {
            MenuItem menuItem = menuItemService.getMenuItemById(id);
            if (menuItem == null) {
                log.warn("Menu item not found for id: {}", id);
                return Response.status(Response.Status.NOT_FOUND).entity("Menu item not found").build();
            }
            return Response.ok(menuItem).build();
        } catch (Exception e) {
            log.error("Error retrieving menu item with id: {}", id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error retrieving the menu item").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMenuItem(MenuItem menuItem) {
        log.debug("Request to create a new menu item");
        try {
            MenuItem savedMenuItem = menuItemService.createMenuItem(menuItem);
            return Response.status(Response.Status.CREATED).entity(savedMenuItem).build();
        } catch (Exception e) {
            log.error("Error creating the menu item", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error creating the menu item").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMenuItem(@PathParam("id") Long id, MenuItem menuItem) {
        log.debug("Request to update menu item with id: {}", id);
        try {
            menuItem.setId(id); // Ensure the ID is set correctly
            MenuItem updatedMenuItem = menuItemService.updateMenuItem(menuItem);
            if (updatedMenuItem == null) {
                log.warn("Failed to update menu item for id: {}", id);
                return Response.status(Response.Status.NOT_FOUND).entity("Menu item not found").build();
            }
            return Response.ok(updatedMenuItem).build();
        } catch (Exception e) {
            log.error("Error updating menu item with id: {}", id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error updating the menu item").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteMenuItem(@PathParam("id") Long id) {
        log.debug("Request to delete menu item with id: {}", id);
        try {
            menuItemService.deleteMenuItem(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            log.error("Error deleting menu item with id: {}", id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error deleting the menu item").build();
        }
    }
}
