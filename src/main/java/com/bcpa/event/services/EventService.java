package com.bcpa.event.services;

import java.util.List;

import com.bcpa.app.utils.Result;
import com.bcpa.event.models.Event;
import com.bcpa.event.repositories.IEventRepository;

public class EventService implements IEventService 
{
    private final IEventRepository _eventRepository;

    public EventService(final IEventRepository eventRepository) 
    {
        _eventRepository = eventRepository;
    }

    @Override
    public final Result<Boolean> createEvent(final Event event) 
    {
        try 
        {
            final var result = _eventRepository.addEvent(event);
            return Result.Ok(result.isSuccess);
        }
        catch (Exception ex) 
        {
            return Result.Err(ex.getMessage());
        }
    }

    @Override
    public final Result<List<Event>> getAllEvents() 
    {
        try 
        {
            final var events = _eventRepository.getEvents().value;
            return Result.Ok(events);
        }
        catch (Exception ex) 
        {
            return Result.Err(ex.getMessage());
        }
    }
}
