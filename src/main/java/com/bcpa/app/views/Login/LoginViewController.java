package com.bcpa.app.views.Login;

import com.bcpa.App;
import com.bcpa.app.utils.Result;
import com.bcpa.app.views.Events.EventViewController;
import com.bcpa.app.views.Events.EventsView;
import com.bcpa.app.views.Home.HomeView;
import com.bcpa.app.views.ViewManager.IViewManager;
import com.bcpa.authentication.models.User;
import com.bcpa.authentication.services.IAuthService;

public final class LoginViewController 
{
    private final IViewManager _viewManager;
    private final IAuthService _authService;


    public LoginViewController(final IViewManager viewManager, final IAuthService authService)
    {
        _viewManager = viewManager;
        _authService = authService;
    }

    public final boolean tryLogin(String username, String password) 
    {
        final Result<User> result = _authService.loginUser(username, password);
                
        final User user = result.value;
        if (user != null) {
            _viewManager.widgetService().showLoadingIcon("\nLogin Success. Loading home page.");
            _viewManager.setActiveView(new EventsView(App.container.resolve(EventViewController.class), user));
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

