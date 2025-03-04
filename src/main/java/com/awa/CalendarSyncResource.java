package com.awa;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import io.quarkus.scheduler.Scheduled;

import java.util.logging.Logger;

@Path("/sync")
public class CalendarSyncResource {

    private static final Logger LOGGER = Logger.getLogger(CalendarSyncResource.class.getName());

    @RestClient
    BookingApiClient bookingApiClient;

    @RestClient
    GoogleCalendarApiClient googleCalendarApiClient;

    @GET
    public Response syncNow() {
        performSync();
        return Response.ok("Sincronización completada manualmente").build();
    }

    @Scheduled(every = "1h") // Sincronización programada cada hora
    public void scheduledSync() {
        LOGGER.info("Iniciando sincronización programada...");
        performSync();
    }

    private void performSync() {
        try {
            // Obtener reservas de Booking
            String bookingEvents = bookingApiClient.getBookings();
            LOGGER.info("Datos obtenidos de Booking: " + bookingEvents);

            // Pasar los datos al calendario de Google
            googleCalendarApiClient.createEvent(bookingEvents);
            LOGGER.info("Datos sincronizados con Google Calendar.");
        } catch (Exception e) {
            LOGGER.severe("Error durante la sincronización: " + e.getMessage());
        }
    }
}
