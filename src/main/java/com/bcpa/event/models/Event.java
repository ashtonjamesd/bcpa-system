package com.bcpa.event.models;

import java.util.ArrayList;
import java.util.List;

public final class Event {
    private String name;
    private String description;
    private String category;
    private List<Show> shows = new ArrayList<Show>();

    public final void addShow(Show show) {
        shows.add(show);
    }

    public final String getEventName() {
        return name;
    }

    public final void setEventName(final String name) {
        this.name = name;
    }

    public final String getDescription() {
        return description;
    }

    public final void setDescription(final String description) {
        this.description = description;
    }

    public final String getCategory() {
        return category;
    }

    public final void setCategory(final String category) {
        this.category = category;
    }

    public final List<Show> getShows() {
        return shows;
    }
}