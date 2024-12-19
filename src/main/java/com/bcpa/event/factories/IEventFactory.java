package com.bcpa.event.factories;

import com.bcpa.event.models.Event;

public interface IEventFactory 
{
    public Event create(final String name);
}
