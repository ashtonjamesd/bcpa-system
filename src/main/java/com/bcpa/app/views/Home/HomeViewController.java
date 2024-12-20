package com.bcpa.app.views.Home;

import java.util.List;

import com.bcpa.app.TicketSystem;
import com.bcpa.app.views.Help.HelpView;
import com.bcpa.app.views.Login.LoginView;
import com.bcpa.app.views.Register.RegisterView;
import com.bcpa.app.views.ViewManager.IViewManager;

public final class HomeViewController 
{
    private final IViewManager _viewManager;

    public boolean isBackRequested = false;

    public HomeViewController(IViewManager viewManager)
    {
        _viewManager = viewManager;
    }

    public void run()
    {
        _viewManager.ioReader().clear();

        final String title = _viewManager.widgetService().toTitle("BCPA Ticket System Home");
        _viewManager.ioReader().write(title);
        
        final List<String> viewOptions = List.of("Login", "Register", "Help", "Exit");
        final String option = _viewManager.widgetService().menuOptions(title, viewOptions);

        if (option == "Exit") {
            isBackRequested = true;
            TicketSystem.isExitRequested = true;
            return;
        }

        final var nextView = switch (option) {
            case "Login"    -> LoginView.class;
            case "Register" -> RegisterView.class;
            case "Help"     -> HelpView.class;
            default         -> null;
        };

        if (nextView != null) {
            _viewManager.setActiveView(nextView);
            isBackRequested = true;
        }
    }
}
