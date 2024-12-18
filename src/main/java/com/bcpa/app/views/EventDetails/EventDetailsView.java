package com.bcpa.app.views.EventDetails;

import com.bcpa.app.views.PageView;
import com.bcpa.app.views.ViewManager.IViewManager;
import com.bcpa.event.models.Event;
import com.bcpa.event.models.Show;

public final class EventDetailsView extends PageView
{
    private final IViewManager _viewManager;
    private final Event _event;

    public EventDetailsView(IViewManager viewManager, Event event) 
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

            _viewManager.ioReader().write(">- Event Details -<\n");
            _viewManager.ioReader().write(_event.getEventName());
            _viewManager.ioReader().write(_event.getDescription());
            _viewManager.ioReader().write(_event.getCategory());

            final var shows = _event.getShows();
            if (shows != null) {
                System.out.println("\nShows:\n");
                for (final Show show: shows)
                {
                    System.out.println("\n  " + show.getVenue());
                    System.out.println("    Date:     " + show.getDateTime());
                    System.out.println("    Max Seats: " + show.getMaxSeats());
                }
            }

            _viewManager.widgetService().wait_();
        }
    }
}
