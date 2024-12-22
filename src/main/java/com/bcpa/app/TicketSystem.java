package com.bcpa.app;

import com.bcpa.App;
import com.bcpa.app.utils.AppMode;
import com.bcpa.app.views.PageView;
import com.bcpa.app.views.Home.HomeView;
import com.bcpa.app.views.ViewManager.IViewManager;
import com.bcpa.logger.ConsoleLogger;
import com.bcpa.logger.ILogger;

public final class TicketSystem
{
    // this has to be global to ensure the integrity of the view structure between page instances
    public static PageView GlobalActivePage = null;

    private final IViewManager _viewManager;
    private final ILogger _logger;

    private AppMode _mode;
    public static boolean isExitRequested = false;

    public TicketSystem(final IViewManager viewManager, final ConsoleLogger logger)
    {
        _viewManager = viewManager;
        _logger = logger;
    }

    public final void run()
    {
        if (_mode == null)
        {
            _logger.LogError("App mode was net set before app.run was called.");
            return;
        }

        try
        {
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
        catch (Exception ex)
        {
            _logger.LogError(ex.getMessage());
        }
    }

    public final void setMode(final AppMode mode)
    {
        _mode = mode;
    }
}