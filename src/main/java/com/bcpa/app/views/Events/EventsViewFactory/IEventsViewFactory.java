package com.bcpa.app.views.Events.EventsViewFactory;

import com.bcpa.app.views.Events.EventsView;
import com.bcpa.authentication.models.User;

public interface IEventsViewFactory {
    public EventsView create(final User user);
}