package com.bcpa.app.views.EventDetails.EventDetailsViewFactory;

import com.bcpa.app.views.EventDetails.EventDetailsView;
import com.bcpa.authentication.models.User;
import com.bcpa.event.models.Event;

public interface IEventDetailsViewFactory {
    public EventDetailsView create(final Event event, final User user);
}
