package com.bcpa.app.views.Events;

import java.util.List;

import com.bcpa.app.views.PageView;
import com.bcpa.app.views.EventDetails.EventDetailsView;
import com.bcpa.app.views.Home.HomeView;
import com.bcpa.app.views.Profile.ProfileView;
import com.bcpa.app.views.ViewManager.IViewManager;
import com.bcpa.authentication.mappers.UserRoleMapper;
import com.bcpa.authentication.models.User;
import com.bcpa.authentication.services.IAuthService;
import com.bcpa.event.factories.IEventFactory;
import com.bcpa.event.models.Event;
import com.bcpa.event.services.IEventService;

public final class EventsView extends PageView
{
    private final IViewManager _viewManager;

    private final IEventService _eventService;
    private final IEventFactory _eventFactory;
    private final IAuthService _authService;

    private final User _user;

    public EventsView(final IViewManager viewManager, final IEventService eventService, final IEventFactory eventFactory, final IAuthService authService, final User user)
    {
        _viewManager = viewManager;
        _eventService = eventService;
        _eventFactory = eventFactory;
        _authService = authService;
        _user = user;
    }

    @Override
    public final void show()
    {
        boolean isBackRequested = false;

        while (!isBackRequested)
        {
            _viewManager.ioReader().clear();

            final String currentlyLoggedInAs = String.format("\nCurrently logged in as '%s' - %s", _user.username, UserRoleMapper.map(_user.role));
            final String title = _viewManager.widgetService().toTitle("Upcoming Events") + "\n";

            final var eventsResult = _eventService.getAllEvents();
            if (!eventsResult.isSuccess)
            {
                // log error
            }

            while (true) 
            {
                final var options = List.of("Select Show", "View Profile", "Logout");
                final var option = _viewManager.widgetService().menuOptions(title + currentlyLoggedInAs, options);
                
                if (option == "Select Show")
                {
                    break;
                }
                else if (option == "View Profile")
                {
                    isBackRequested = true;
                    _viewManager.setActiveView(new ProfileView(_viewManager, _authService, _user));
                    break;
                }
                else if (option == "Logout") 
                {
                    isBackRequested = true;
                    _viewManager.setActiveView(HomeView.class);
                    break;
                }
            }

            while (true)
            {
                if (isBackRequested) break;

                final var selectedEvent = _viewManager.widgetService().menuOptions(title + currentlyLoggedInAs, eventsResult.value, Event::getEventName);
                final var isYes = _viewManager.widgetService().getChoice("Selected: " + selectedEvent.getEventName());

                if (isYes)
                {
                    _viewManager.setActiveView(new EventDetailsView(_viewManager, _eventService, _user, selectedEvent, _eventFactory));
                    isBackRequested = true;
                    break;
                }
            }
        }
    }
}
