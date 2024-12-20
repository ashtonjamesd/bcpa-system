package com.bcpa.app.views.Events;

import com.bcpa.app.views.PageView;
import com.bcpa.authentication.models.User;

public final class EventsView extends PageView
{
    private final EventViewController _controller;

    private final User _user;

    public EventsView(final EventViewController controller, final User user)
    {
        _controller = controller;
        _user = user;
    }

    @Override
    public final void show()
    {
        while (!_controller.isBackRequested)
        {
            _controller.run(_user);
        }
    }
}
