package com.bcpa.app;

import com.bcpa.App;
import com.bcpa.app.utils.AppMode;
import com.bcpa.app.views.PageView;
import com.bcpa.app.views.Home.HomeView;
import com.bcpa.app.views.ViewManager.IViewManager;

public final class TicketSystem
{
    // this has to be global to ensure the integrity of the view structure between page instances
    public static PageView GlobalActivePage = null;

    private final IViewManager _viewManager;

    private AppMode _mode;
    public static boolean isExitRequested = false;

    public TicketSystem(final IViewManager viewManager)
    {
        _viewManager = viewManager;
    }

    public final void run()
    {
        if (_mode == null)
        {
            System.err.println("App mode was net set before app.run was called.");
            return;
        }

        // The initial active page for the application is the home page.
        _viewManager.setActiveView(App.container.resolve(HomeView.class));

        while (true)
        {
            if (isExitRequested) {
                _viewManager.ioReader().write("Thank you for using the BCPA Ticket System.");
                break;
            }

            _viewManager.getActiveView().show();
        }
    }

    public final void setMode(final AppMode mode) 
    {
        _mode = mode;
    }
}