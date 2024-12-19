package com.bcpa.app.views.Login;

import com.bcpa.app.utils.Result;
import com.bcpa.app.views.Events.EventsView;
import com.bcpa.app.views.Home.HomeView;
import com.bcpa.app.views.ViewManager.IViewManager;
import com.bcpa.authentication.models.User;
import com.bcpa.authentication.services.IAuthService;
import com.bcpa.event.factories.IEventFactory;
import com.bcpa.event.services.IEventService;

public final class LoginViewController 
{
    private final IViewManager _viewManager;
    private final IAuthService _authService;

    private final IEventService _eventService;
    private final IEventFactory _eventFactory;

    public LoginViewController(final IViewManager viewManager, final IAuthService authService, final IEventService eventService, final IEventFactory eventFactory)
    {
        _viewManager = viewManager;
        _authService = authService;
        _eventService = eventService;
        _eventFactory = eventFactory;
    }

    public final boolean tryLogin(String username, String password) 
    {
        final Result<User> result = _authService.loginUser(username, password);
                
        final User user = result.value;
        if (user != null) {
            _viewManager.widgetService().showLoadingIcon("\nLogin Success. Loading home page.");
            _viewManager.setActiveView(new EventsView(_viewManager, _eventService, _authService, user, _eventFactory));
            return true;
        } 
        else 
        {
            _viewManager.ioReader().write("\nLogin Failed. Invalid Credentials.");
            _viewManager.ioReader().read("\nPress Enter to continue.");
            _viewManager.setActiveView(HomeView.class);
            return false;
        }
    }
}

