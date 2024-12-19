package com.bcpa.app.views.Home;

import java.util.List;

import com.bcpa.app.TicketSystem;
import com.bcpa.app.views.PageView;
import com.bcpa.app.views.Login.LoginView;
import com.bcpa.app.views.Register.RegisterView;
import com.bcpa.app.views.ViewManager.IViewManager;

public final class HomeView extends PageView
{
    private final IViewManager _viewManager;

    public HomeView(final IViewManager viewManager)
    {
        _viewManager = viewManager;
    }

    @Override
    public final void show()
    {
        while (true)
        {
            _viewManager.ioReader().clear();

            final String title = _viewManager.widgetService().toTitle("BCPA Ticket System Home");
            _viewManager.ioReader().write(title);
            
            final List<String> viewOptions = List.of("Login", "Register", "Exit");
            final String option = _viewManager.widgetService().menuOptions(title, viewOptions);

            if (option == "Exit") {
                TicketSystem.isExitRequested = true;
                break;
            }

            final var nextView = switch (option) {
                case "Login"    -> LoginView.class;
                case "Register" -> RegisterView.class;
                default         -> null;
            };
    
            if (nextView != null) {
                _viewManager.setActiveView(nextView);
                break;
            }
        }
    }
}
