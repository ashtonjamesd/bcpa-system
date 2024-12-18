package com.bcpa.database;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.bcpa.authentication.models.User;
import com.bcpa.event.models.Event;
import com.bcpa.event.models.Show;

/**
 * This is a pseudo-implementation of a database context, created specifically
 * for simulation and testing purposes.
 * 
 * A SQL database was not set up as it would not contribute to the overall
 * goal of the assignment, which is to demonstrate various OOP concepts.
 * 
 */
public final class DbContext implements IDbContext
{
    public DbContext() 
    {
        final var rnd = new Random();
        
        final int eventsToSeed = 5;
        final int showsToSeed = 3;

        for (int i = 0; i < eventsToSeed; i++) {
            final var event = new Event();
            event.setEventName("Event " + i);
            event.setCategory("Test");
            event.setDescription("This is a new event");

            for (int j = 0; j < showsToSeed; j++) {
                final var show = new Show();
                show.setMaxSeats(rnd.nextInt(50));
                show.setVenue("Example Venue " + j + 1);

                show.setDateTime(new Date());
                event.addShow(show);
            }
            
            events.add(event);
        }
    }

    public final List<User> users = new ArrayList<>();
    public final List<Event> events = new ArrayList<>();
}