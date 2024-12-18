package com.bcpa.app.views.Login;

import com.bcpa.app.utils.Result;
import com.bcpa.app.views.PageView;
import com.bcpa.app.views.Events.EventsView;
import com.bcpa.app.views.Home.HomeView;
import com.bcpa.app.views.ViewManager.IViewManager;
import com.bcpa.authentication.factories.Customer.ICustomerFactory;
import com.bcpa.authentication.models.User;
import com.bcpa.authentication.services.IAuthService;
import com.bcpa.event.services.IEventService;

public final class LoginView extends PageView
{
    private final IViewManager _viewManager;
    private final IEventService _eventService;
    private final ICustomerFactory _customerFactory;

    private final IAuthService _auth;

    public LoginView(final IViewManager viewManager, final IEventService eventService, final IAuthService auth, final ICustomerFactory customerFactory) 
    {
        _viewManager = viewManager;
        _auth = auth;
        _eventService = eventService;
        _customerFactory = customerFactory;
    }

    @Override
    public final void show()
    {
        while (true) 
        {
            _viewManager.ioReader().clear();

            final String title = _viewManager.widgetService().toTitle("BCPA Ticket System Login");
            _viewManager.ioReader().write(title);

            try 
            {
                final String username = _viewManager.ioReader().read("\n  Username: ");
                final String password = _viewManager.ioReader().read("  Password: ");

                final Result<User> result = _auth.loginUser(username, password);
                
                final User user = result.value;
                if (user != null) {
                    _viewManager.widgetService().showLoadingIcon("\nLogin Success. Loading home page.");
                    _viewManager.setActiveView(new EventsView(_viewManager, _eventService, user));
                    break;
                } 
                else 
                {
                    _viewManager.ioReader().write("\nLogin Failed. Invalid Credentials.");
                    _viewManager.ioReader().read("\nPress Enter to continue.");
                    _viewManager.setActiveView(new HomeView(_viewManager, _eventService, _auth, _customerFactory));
                    break;
                }
            }   
            catch (final Exception ex)
            {
                // punish user for causing an exception to be thrown
                // Thread.sleep(9999 * (1000));
            }
        }
    }
}
