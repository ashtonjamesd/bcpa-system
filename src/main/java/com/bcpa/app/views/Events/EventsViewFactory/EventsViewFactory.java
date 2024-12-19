package com.bcpa.app.views.Events.EventsViewFactory;

import com.bcpa.app.views.Events.EventsView;
import com.bcpa.app.views.ViewManager.IViewManager;
import com.bcpa.authentication.models.User;
import com.bcpa.authentication.services.IAuthService;
import com.bcpa.event.factories.IEventFactory;
import com.bcpa.event.services.IEventService;

public class EventsViewFactory implements IEventsViewFactory {
    private final IViewManager _viewManager;
    private final IEventService _eventService;
    private final IAuthService _authService;
    private final IEventFactory _eventFactory;

    public EventsViewFactory(
        IViewManager viewManager,
        IEventService eventService,
        IAuthService authService,
        IEventFactory eventFactory
    ) {
        _viewManager = viewManager;
        _eventService = eventService;
        _authService = authService;
        _eventFactory = eventFactory;
    }

    @Override
    public EventsView create(User user) {
        return new EventsView(
            _viewManager,
            _eventService,
            _authService,
            user,
            _eventFactory
        );
    }
}


