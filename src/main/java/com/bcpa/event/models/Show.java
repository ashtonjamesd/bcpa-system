package com.bcpa.event.models;

import java.time.LocalDateTime;

public final class Show {
    private LocalDateTime dateTime;
    private String venue;
    private int maxSeats;

    public final LocalDateTime getDateTime() {
        return dateTime;
    }

    public final void setDateTime(final LocalDateTime dateTime) {
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
