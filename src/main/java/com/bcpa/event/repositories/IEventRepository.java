package com.bcpa.event.repositories;

import java.util.List;

import com.bcpa.app.utils.Result;
import com.bcpa.event.models.Event;

public interface IEventRepository {
    public Result<Boolean> addEvent(Event event);
    public Result<List<Event>> getEvents();
}
