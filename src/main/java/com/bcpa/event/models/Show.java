package com.bcpa.event.models;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class Show
{
    private Date dateTime;
    private String venue;
    private int maxSeats;
    private final List<Seat> seats;

    public Show()
    {
        this.seats = new ArrayList<>();
    }

    public final List<Seat> getSeats()
    {
        return seats;
    }

    public final Date getDateTime() 
    {
        return dateTime;
    }

    public final void setDateTime(final Date dateTime) 
    {
        this.dateTime = dateTime;
    }

    public final String getVenue() 
    {
        return venue;
    }

    public final void setVenue(final String venue) 
    {
        this.venue = venue;
    }

    public final int getMaxSeats() 
    {
        return maxSeats;
    }

    public final void setMaxSeats(final int maxSeats) 
    {
        this.maxSeats = maxSeats;
    }
}
