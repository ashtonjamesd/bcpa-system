package com.bcpa.app.views.EventDetails;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bcpa.App;
import com.bcpa.app.views.Events.EventViewController;
import com.bcpa.app.views.Events.EventsView;
import com.bcpa.app.views.ShowBooking.ShowBookingView;
import com.bcpa.app.views.ShowBooking.ShowBookingViewController;
import com.bcpa.app.views.ViewManager.IViewManager;
import com.bcpa.authentication.models.User;
import com.bcpa.event.models.Event;
import com.bcpa.event.models.Show;

public final class EventDetailsViewController
{
    private final IViewManager _viewManager;

    public boolean isBackRequested = false;

    public EventDetailsViewController(final IViewManager viewManager)
    {
        _viewManager = viewManager;
    }

    private String display(Event event)
    {
        _viewManager.ioReader().clear();

        final String title = _viewManager.widgetService().toTitle("Event Details") + "\n";

        final var eventTitle = "\nTitle:       " + event.getEventName();
        final var eventDesc = "\nDescription: " + event.getDescription();
        final var eventCategory = "\nCategory:    " + event.getCategory();

        final var shows = event.getShows();

        String showsTitle = "";
        String showsStr = "";

        if (shows != null) 
        {
            showsTitle = "\n\nShows (" + shows.size() + "):";
            for (final Show show : shows)
            {
                showsStr += "\n  - " + show.getVenue() + " | " + formatShowDate(show.getDateTime());
                showsStr += "\n      Max Seats: " + show.getMaxSeats();
            }
        }

        final var eventStr = title + eventTitle + eventDesc + eventCategory;
        final var showStr = showsTitle + showsStr;
        
        return eventStr + showStr;
    }

    public void run(Event event, User user)
    {
        final var displayStr = display(event);

        while (true)
        {
            final var options = List.of("Book Show", "Search", "Back");
            final var option = _viewManager.widgetService().menuOptions(displayStr, options);

            if (option.equals("Book Show"))
            {
                onBookShow(event, null);
            }
            else if (option.equals("Search"))
            {
                onSearch(event);
            }
            else if (option.equals("Back"))
            {
                onBack(user);
            }

            break;
        }
    }

    private void onBookShow(Event event, Show show)
    {
        if (show != null)
        {
            _viewManager.setActiveView(new ShowBookingView(App.container.resolve(ShowBookingViewController.class), show));
            isBackRequested = true;
            return;
        }

        final var selectedShow = _viewManager.widgetService().menuOptions("Select a show", event.getShows(), Show::getVenue);
        _viewManager.setActiveView(new ShowBookingView(App.container.resolve(ShowBookingViewController.class), selectedShow));
        isBackRequested = true;
    }

    private void onSearch(Event event)
    {
        _viewManager.ioReader().clear();
        final var query = _viewManager.ioReader().read("Enter a search term: ");
        
        final var shows = event.getShows();
        
        final List<Show> matchingShows = new ArrayList<>();
        for (final Show show : shows)
        {
            if (show.getVenue().contains(query))
            {
                matchingShows.add(show);
            }
        }

        final var matchStr = "Matching Shows for '" + query + "'";
        final var selectedShow = _viewManager.widgetService().menuOptions(matchStr, matchingShows, Show::getVenue);

        onBookShow(event, selectedShow);
    }

    private void onBack(User user) 
    {
        _viewManager.setActiveView(new EventsView(App.container.resolve(EventViewController.class), user));
        isBackRequested = true;
    }

    private String formatShowDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy HH:mm");
        return sdf.format(date);
    }
}

