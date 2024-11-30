package com.bcpa.app;

import com.bcpa.app.services.IIOReader;
import com.bcpa.app.utils.AppMode;
import com.bcpa.app.views.PageView;
import com.bcpa.app.views.Login.LoginView;
import com.bcpa.app.views.ViewManager.IViewManager;
import com.bcpa.authentication.services.IAuthService;

public final class TicketSystem
{
    private final IViewManager _viewManager;

    private AppMode _mode;

    private final IAuthService _auth;
    private final IIOReader _inputReader;

    public TicketSystem(IViewManager viewManager, IAuthService auth, IIOReader inputReader)
    {
        _viewManager = viewManager;
        _auth = auth;
        _inputReader = inputReader;
    }

    public void run() 
    {
        if (_mode == null) {
            System.err.println("App mode was net set before app.run was called.");
            return;
        }

        // The initial active page for the application is the login page.
        _viewManager.setActiveView(new LoginView(_viewManager, _auth, _inputReader));

        while (true) {
            PageView view = _viewManager.getActiveView();
            view.show();
        }
    }

    public void setMode(AppMode mode) {
        _mode = mode;
    }
}
