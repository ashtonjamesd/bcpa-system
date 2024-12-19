package com.bcpa.app.views.EventDetails.EventDetailsViewFactory;

import com.bcpa.app.views.EventDetails.EventDetailsView;
import com.bcpa.app.views.Events.EventsViewFactory.IEventsViewFactory;
import com.bcpa.app.views.ViewManager.IViewManager;
import com.bcpa.authentication.models.User;
import com.bcpa.event.factories.IEventFactory;
import com.bcpa.event.models.Event;
import com.bcpa.event.services.IEventService;

public final class EventDetailsViewFactory implements IEventDetailsViewFactory {
    private final IViewManager _viewManager;
    private final IEventService _eventService;
    private final IEventFactory _eventFactory;

    public EventDetailsViewFactory(
        IViewManager viewManager,
        IEventService eventService,
        IEventFactory eventFactory) {
        _viewManager = viewManager;
        _eventService = eventService;
        _eventFactory = eventFactory;
    }

    @Override
    public EventDetailsView create(Event event, User user) {
        return new EventDetailsView(
            _viewManager,
            _eventService,
            user,
            event,
            _eventFactory
        );
    }
}
