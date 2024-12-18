package com.bcpa.app;

import com.bcpa.app.utils.AppMode;
import com.bcpa.app.views.Home.HomeView;
import com.bcpa.app.views.ViewManager.IViewManager;
import com.bcpa.authentication.factories.Customer.ICustomerFactory;
import com.bcpa.authentication.services.IAuthService;
import com.bcpa.event.services.IEventService;

public final class TicketSystem
{
    private final IViewManager _viewManager;
    private final IEventService _eventService;
    private final IAuthService _authService;
    private final ICustomerFactory _customerFactory;
    

    private AppMode _mode;
    private boolean isExitRequested = false;

    public TicketSystem(final IViewManager viewManager, final IEventService eventService, final IAuthService authService, final ICustomerFactory customerFactory)
    {
        _viewManager = viewManager;
        _eventService = eventService;
        _authService = authService;
        _customerFactory = customerFactory;
    }

    public final void run()
    {
        if (_mode == null)
        {
            System.err.println("App mode was net set before app.run was called.");
            return;
        }

        // The initial active page for the application is the home page.
        _viewManager.setActiveView(new HomeView(_viewManager, _eventService, _authService, _customerFactory));

        while (true)
        {
            if (isExitRequested) break;
            // System.out.println(_viewManager.getActiveView());
            // _viewManager.widgetService().wait_();
            _viewManager.getActiveView().show();
        }
    }

    public final void setMode(final AppMode mode) 
    {
        _mode = mode;
    }
}