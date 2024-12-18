package com.bcpa.event.models;
import java.util.Date;

public final class Show {
    private Date dateTime;
    private String venue;
    private int maxSeats;

    public final Date getDateTime() {
        return dateTime;
    }

    public final void setDateTime(final Date dateTime) {
        this.dateTime = dateTime;
    }

    public final String getVenue() {
        return venue;
    }

    public final void setVenue(final String venue) {
        this.venue = venue;
    }

    public final int getMaxSeats() {
        return maxSeats;
    }

    public final void setMaxSeats(final int maxSeats) {
        this.maxSeats = maxSeats;
    }
}
