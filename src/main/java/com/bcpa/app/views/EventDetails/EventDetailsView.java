package com.bcpa.app.views.EventDetails;

import com.bcpa.app.views.PageView;
import com.bcpa.authentication.models.User;
import com.bcpa.event.models.Event;

public final class EventDetailsView extends PageView
{
    private final EventDetailsViewController _controller;

    private final User _user;
    private final Event _event;

    public EventDetailsView(final EventDetailsViewController controller, final User user, final Event event) 
    {
        _controller = controller;
        _user = user;
        _event = event;
    }

    @Override
    public void show()
    {
        while (!_controller.isBackRequested)
        {
            _controller.run(_event, _user);
        }
    }
}
