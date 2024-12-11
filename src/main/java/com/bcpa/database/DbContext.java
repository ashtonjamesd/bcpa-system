package com.bcpa.database;

import java.util.ArrayList;
import java.util.List;

import com.bcpa.authentication.models.User;
import com.bcpa.event.models.Event;

/**
 * This is a pseudo-implementation of a database context, created specifically
 * for simulation and testing purposes.
 * 
 * A SQL database was not set up as it would not contribute to the overall
 * goal of the assignment, which is to demonstrate various OOP concepts.
 * 
 * The data is real, it is just stored in memory.
 */
public final class DbContext 
{
    public DbContext() 
    {
        for (int i = 0; i < 10; i++) {
            var event = new Event();
            event.setEventName("Event " + i);
            event.setCategory("Test");
            event.setDescription("This is a new event");

            events.add(event);
        }
    }

    public final List<User> users = new ArrayList<>();
    public final List<Event> events = new ArrayList<>();
}