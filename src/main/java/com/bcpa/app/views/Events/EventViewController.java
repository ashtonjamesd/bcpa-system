package com.bcpa.app.views.Events;

import java.util.List;

import com.bcpa.App;
import com.bcpa.app.views.EventDetails.EventDetailsView;
import com.bcpa.app.views.EventDetails.EventDetailsViewController;
import com.bcpa.app.views.Home.HomeView;
import com.bcpa.app.views.Profile.ProfileView;
import com.bcpa.app.views.Profile.ProfileViewController;
import com.bcpa.app.views.ViewManager.IViewManager;
import com.bcpa.authentication.mappers.UserRoleMapper;
import com.bcpa.authentication.models.User;
import com.bcpa.event.models.Event;
import com.bcpa.event.services.IEventService;

public final class EventViewController
{
    private final IViewManager _viewManager;
    private final IEventService _eventService;

    public boolean isBackRequested  = false;

    public EventViewController(final IViewManager viewManager, final IEventService eventService)
    {
        _viewManager = viewManager;
        _eventService = eventService;
    }

    public void run(User user)
    {
        _viewManager.ioReader().clear();

        final String currentlyLoggedInAs = String.format("\nCurrently logged in as '%s' - %s", user.username, UserRoleMapper.map(user.role));
        final String title = _viewManager.widgetService().toTitle("Upcoming Events") + "\n";

        final var eventsResult = _eventService.getAllEvents();

        while (true) 
        {
            final var options = List.of("Select Event", "View Profile", "Logout");
            final var option = _viewManager.widgetService().menuOptions(title + currentlyLoggedInAs, options);
            
            if (option == "Select Event")
            {
                break;
            }
            else if (option == "View Profile")
            {
                isBackRequested = true;
                _viewManager.setActiveView(new ProfileView(App.container.resolve(ProfileViewController.class), user));
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
                _viewManager.setActiveView(new EventDetailsView(App.container.resolve(EventDetailsViewController.class), user, selectedEvent));
                isBackRequested = true;
                break;
            }
        }
    }
}

