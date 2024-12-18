package com.bcpa.app.views.Home;

import java.util.List;
import com.bcpa.app.views.PageView;
import com.bcpa.app.views.Login.LoginView;
import com.bcpa.app.views.Register.RegisterView;
import com.bcpa.app.views.ViewManager.IViewManager;
import com.bcpa.authentication.factories.Customer.ICustomerFactory;
import com.bcpa.authentication.services.IAuthService;
import com.bcpa.event.services.IEventService;

public final class HomeView extends PageView
{
    private final IViewManager _viewManager;
    private final IEventService _eventService;
    private final IAuthService _authService;

    private final ICustomerFactory _customerFactory;

    public HomeView(final IViewManager viewManager, final IEventService eventService, final IAuthService authService, final ICustomerFactory customerFactory)
    {
        _viewManager = viewManager;
        _eventService = eventService;
        _authService = authService;
        _customerFactory = customerFactory;
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

            if (option == "Exit") break;
            
            PageView page = switch (option) {
                case "Login"    -> new LoginView(_viewManager, _eventService, _authService, _customerFactory);
                case "Register" -> new RegisterView(_viewManager, _customerFactory, _authService, _eventService);
                default -> null;
            };

            if (page != null) {
                _viewManager.setActiveView(page);
                break;
            }
        }
    }
}
