package com.bcpa.app;
import com.bcpa.App;
import com.bcpa.app.utils.AppMode;
import com.bcpa.app.views.Login.LoginView;
import com.bcpa.app.views.ViewManager.IViewManager;
import com.bcpa.authentication.services.IAuthService;
import com.bcpa.database.DbContext;
import com.bcpa.event.services.IEventService;

public final class TicketSystem
{
    private final IViewManager _viewManager;

    private AppMode _mode;

    private final IAuthService _authService;
    private final IEventService _eventService;

    public TicketSystem(final IViewManager viewManager, final IAuthService authService, final IEventService eventService)
    {
        _viewManager = viewManager;
        _authService = authService;
        _eventService = eventService;
    }

    public final void run() 
    {
        if (_mode == null) 
        {
            System.err.println("App mode was net set before app.run was called.");
            return;
        }

        // The initial active page for the application is the login page.
        _viewManager.setActiveView(new LoginView(_viewManager, _eventService, _authService));

        while (true) 
        {
            _viewManager.getActiveView().show();
        }
    }

    public final void setMode(final AppMode mode) 
    {
        _mode = mode;
    }
}