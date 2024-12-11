package com.bcpa.app.views.Events;

import com.bcpa.app.views.PageView;
import com.bcpa.app.views.EventDetails.EventDetailsView;
import com.bcpa.app.views.ViewManager.IViewManager;
import com.bcpa.authentication.mappers.UserRoleMapper;
import com.bcpa.authentication.models.User;
import com.bcpa.event.models.Event;
import com.bcpa.event.services.IEventService;

public final class EventsView extends PageView
{
    private final IViewManager _viewManager;

    private final IEventService _eventService;
    private final User _user;

    public EventsView(final IViewManager viewManager, final IEventService eventService, final User user)
    {
        _viewManager = viewManager;
        _eventService = eventService;
        _user = user;
    }

    @Override
    public final void show()
    {
        while (true)
        {
            _viewManager.ioReader().clear();

            _viewManager.ioReader().write(">- Upcoming Events -<");
            _viewManager.ioReader().write(String.format("Currently logged in as: %s Role: %s", _user.username, UserRoleMapper.map(_user.role)));

            final var result = _eventService.getAllEvents();

            while (true)
            {
                var selectedEvent = _viewManager.widgetService().menuOptions(result.value, Event::getEventName);
                var isYes = _viewManager.widgetService().getChoice("Selected: " + selectedEvent.getEventName());

                if (isYes)
                {
                    _viewManager.setActiveView(new EventDetailsView(_viewManager, selectedEvent));
                    break;
                }
            }

            break;
        }
    }
}
