package com.bcpa.app.views.EventDetails;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.bcpa.app.views.PageView;
import com.bcpa.app.views.Events.EventsView;
import com.bcpa.app.views.ViewManager.IViewManager;
import com.bcpa.authentication.models.User;
import com.bcpa.event.factories.IEventFactory;
import com.bcpa.event.models.Event;
import com.bcpa.event.models.Show;
import com.bcpa.event.services.IEventService;

public final class EventDetailsView extends PageView 
{
    private final IViewManager _viewManager;
    private IEventService _eventService;
    private IEventFactory _eventFactory;

    private final User _user;
    private final Event _event;

    public EventDetailsView(final IViewManager viewManager, final IEventService eventService, final User user, final Event event, final IEventFactory eventFactory) 
    {
        _viewManager = viewManager;
        _eventService = eventService;
        _user = user;
        _event = event;
        _eventFactory = eventFactory;
    }

    @Override
    public void show() 
    {
        boolean isBackRequested = false;

        while (true) 
        {
            if (isBackRequested) 
            {
                _viewManager.setActiveView(new EventsView(_viewManager, _eventService, _eventFactory, _user));
                break;
            }

            _viewManager.ioReader().clear();

            final String title = _viewManager.widgetService().toTitle("Event Details") + "\n";

            final var eventTitle = "\nTitle:       " + _event.getEventName();
            final var eventDesc = "\nDescription: " + _event.getDescription();
            final var eventCategory = "\nCategory:    " + _event.getCategory();

            final var shows = _event.getShows();

            String showsTitle = "";
            String showsStr = "";

            if (shows != null) 
            {
                showsTitle = "\n\nShows (" + shows.size() + "):";
                for (final Show show : shows) 
                {
                    showsStr += "\n  - " + show.getVenue() + " | " + FormatShowDate(show.getDateTime());
                    showsStr += "\n      Max Seats: " + show.getMaxSeats();
                }
            }

            final var eventStr = title + eventTitle + eventDesc + eventCategory;
            final var showStr = showsTitle + showsStr;

            while (true) 
            {
                final var options = List.of("Book Show", "Search", "Back");
                final var option = _viewManager.widgetService().menuOptions(eventStr + showStr, options);

                if (option.equals("Book Show")) {

                } else if (option.equals("Search")) {

                } else if (option.equals("Back")) {
                    isBackRequested = true;
                    break;
                }
            }
        }
    }

    private String FormatShowDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy HH:mm");
        return sdf.format(date);
    }
}
