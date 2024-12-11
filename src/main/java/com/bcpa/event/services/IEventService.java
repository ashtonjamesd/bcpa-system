package com.bcpa.event.services;

import java.util.List;

import com.bcpa.app.utils.Result;
import com.bcpa.event.models.Event;

public interface IEventService {
    public Result<Boolean> createEvent(Event event);
    public Result<List<Event>> getAllEvents();
}
