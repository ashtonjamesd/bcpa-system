package com.bcpa.event.factories;

import com.bcpa.event.models.Event;

public class EventFactory implements IEventFactory 
{
    @Override
    public final Event create(final String name)
    {
        return new Event(
            name, 
            "", 
            ""
        );
    }
}
