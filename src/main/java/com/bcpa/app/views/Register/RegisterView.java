package com.bcpa.app.views.Register;

import com.bcpa.App;
import com.bcpa.app.views.PageView;
import com.bcpa.app.views.Home.HomeView;
import com.bcpa.app.views.Login.LoginView;
import com.bcpa.app.views.ViewManager.IViewManager;
import com.bcpa.authentication.factories.Customer.ICustomerFactory;
import com.bcpa.authentication.models.Customer;
import com.bcpa.authentication.services.IAuthService;
import com.bcpa.event.services.IEventService;

public final class RegisterView extends PageView
{
    private final IViewManager _viewManager;
    private final ICustomerFactory _customerFactory;
    private final IEventService _eventService;

    private final IAuthService _auth;

    public RegisterView(final IViewManager viewManager, final ICustomerFactory customerFactory, final IAuthService auth, final IEventService eventService) 
    {
        _viewManager = viewManager;
        _auth = auth;
        _customerFactory = customerFactory;
        _eventService = eventService;
    }

    @Override
    public final void show()
    {
        while (true) 
        {
            _viewManager.ioReader().clear();

            final String title = _viewManager.widgetService().toTitle("BCPA Ticket System Register");
            _viewManager.ioReader().write(title);

            _viewManager.ioReader().write("\nCreate new user:");
            
            final String username = _viewManager.ioReader().read("\n  Username: ");
            final String password = _viewManager.ioReader().read("  Password: ");
            final String address = _viewManager.ioReader().read("  Address: ");
            
            final Customer user = _customerFactory.create(username, password, address);
            final var result = _auth.registerUser(user);

            if (result.isSuccess) 
            {
                _viewManager.widgetService().showLoadingIcon("\nUser successfully created, navigating to login page");
                _viewManager.setActiveView(new LoginView(_viewManager, _eventService, _auth, _customerFactory));
                break;
            }
            else
            {
                _viewManager.ioReader().write("\nUnable to create user: " + result.error);
                _viewManager.ioReader().readKey();
                _viewManager.setActiveView(App.container.resolve(HomeView.class));
                break;
            }
        }
    }
}
