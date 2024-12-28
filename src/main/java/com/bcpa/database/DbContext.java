package com.bcpa.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.bcpa.authentication.models.Customer;
import com.bcpa.authentication.models.User;
import com.bcpa.authentication.repositories.UserRepository;
import com.bcpa.authentication.services.PasswordHasher;
import com.bcpa.event.enums.SeatStatus;
import com.bcpa.event.models.Event;
import com.bcpa.event.models.Seat;
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
            final var event = new Event(null, null, null);
            event.setEventName("Event " + i);
            event.setCategory("Test");
            event.setDescription("This is a new event");

            for (int j = 0; j < showsToSeed; j++) {
                final var show = new Show();
                show.setMaxSeats(rnd.nextInt(50));
                show.setVenue("Example Venue " + j + 1);

                show.setDateTime(new Date());
                event.addShow(show);

                for (int row = 0; row < rnd.nextInt(3, 7); row++) {
                    for (int col = 0; col < rnd.nextInt(10, 22); col++) {
                        String position = String.format("%c%d", 'A' + row, col + 1);

                        final int seatStatusIdx = rnd.nextInt(4);

                        SeatStatus status = SeatStatus.Open;
                        if (seatStatusIdx == 0) status = SeatStatus.Held;
                        if (seatStatusIdx == 1) status = SeatStatus.Booked;
                        if (seatStatusIdx == 2) status = SeatStatus.Open;

                        show.getSeats().add(new Seat(position, row, col, status, 29.99));
                    }
                }
            }

            UserRepository repo = new UserRepository(this, new PasswordHasher());
            repo.createUser(new Customer("a", "a", "address"));
            
            events.add(event);
        }
    }

    public final List<User> users = new ArrayList<>();
    public final List<Event> events = new ArrayList<>();
}