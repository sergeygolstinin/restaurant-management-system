package com.sergeygolstinin.restaurant.app;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import java.net.URI;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static final String BASE_URI = "http://localhost:8080/";

    public static void main(String[] args) {
        final ResourceConfig resourceConfig = new ResourceConfig()
                .packages("com.sergeygolstinin.restaurant.controller")
                .register(JacksonFeature.class)
                .register(RolesAllowedDynamicFeature.class)
                .property(ServerProperties.TRACING, "ALL");

        final HttpServer[] serverWrapper = new HttpServer[1];

        try {
            logger.info("Starting server...");
            serverWrapper[0] = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), resourceConfig, false);
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                logger.info("Stopping server...");
                if (serverWrapper[0] != null) {
                    serverWrapper[0].shutdownNow();
                }
                logger.info("Server stopped.");
            }));
            serverWrapper[0].start();
            logger.info("Server started. Press Ctrl+C to stop.");
            Thread.currentThread().join();
        } catch (Exception e) {
            logger.severe("Error starting server: " + e.getMessage());
            if (serverWrapper[0] != null) {
                serverWrapper[0].shutdownNow();
            }
        }
    }
}
