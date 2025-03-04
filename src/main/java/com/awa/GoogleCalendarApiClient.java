package com.awa;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.Consumes;

@RegisterRestClient(baseUri = "https://www.googleapis.com/calendar/v3/")
public interface GoogleCalendarApiClient {

    @POST
    @Path("/calendars/primary/events")
    @Consumes(MediaType.APPLICATION_JSON)
    void createEvent(String eventData); // Este método crea eventos en Google Calendar
}
