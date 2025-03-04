package com.awa;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@RegisterRestClient(baseUri = "https://api.booking.com/")
public interface BookingApiClient {

    @GET
    @Path("/reservations")
    String getBookings(); // Este método llama a la API de reservas de Booking
}
