package com.bcpa.event.repositories;

import java.util.List;

import com.bcpa.app.utils.Result;
import com.bcpa.database.DbContext;
import com.bcpa.event.models.Event;

public final class EventRespository implements IEventRepository
{
    private final DbContext _db;

    public EventRespository(final DbContext db)
    {
        _db = db;
    }

    @Override
    public final Result<Boolean> addEvent(final Event event)
    {
        try 
        {
            _db.events.add(event);
            return Result.Ok(true);
        }
        catch (Exception ex) 
        {
            return Result.Err(ex.getMessage());
        }
    }

    @Override
    public final Result<List<Event>> getEvents() 
    {
        try 
        {
            return Result.Ok(_db.events);
        }
        catch (Exception ex) 
        {
            return Result.Err(ex.getMessage());
        }
    }
}
