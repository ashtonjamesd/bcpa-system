package com.bcpa.app.views.EventDetails;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.bcpa.app.views.PageView;
import com.bcpa.app.views.ViewManager.IViewManager;
import com.bcpa.event.models.Event;
import com.bcpa.event.models.Show;

public final class EventDetailsView extends PageView
{
    private final IViewManager _viewManager;
    private final Event _event;

    public EventDetailsView(final IViewManager viewManager, final Event event) 
    {
        _viewManager = viewManager;
        _event = event;
    }
    
    @Override
    public void show() 
    {
        while (true)
        {
            _viewManager.ioReader().clear();

            final String title = _viewManager.widgetService().toTitle("Event Details");
            _viewManager.ioReader().write(title + "\n");

            _viewManager.ioReader().write("Title:       " + _event.getEventName());
            _viewManager.ioReader().write("Description: " + _event.getDescription());
            _viewManager.ioReader().write("Category:    " + _event.getCategory());

            final var shows = _event.getShows();
            if (shows != null) 
            {
                System.out.println("\nShows " + "(" + shows.size() + "):");
                for (final Show show: shows)
                {
                    System.out.println("\n  " + show.getVenue() + " | " + FormatShowDate(show.getDateTime()));
                    System.out.println("    Max Seats: " + show.getMaxSeats());
                }
            }

            _viewManager.widgetService().wait_();
        }
    }

    private String FormatShowDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy HH:mm");
        return sdf.format(date);
    }
}
