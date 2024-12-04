package com.bcpa.app.views.Login;
import com.bcpa.app.utils.Result;
import com.bcpa.app.views.PageView;
import com.bcpa.app.views.Events.EventsView;
import com.bcpa.app.views.ViewManager.IViewManager;
import com.bcpa.authentication.models.User;
import com.bcpa.authentication.services.IAuthService;
import com.bcpa.event.services.IEventService;

public final class LoginView extends PageView
{
    private final IViewManager _viewManager;
    private final IEventService _eventService;

    private final IAuthService _auth;

    public LoginView(final IViewManager viewManager, final IEventService eventService, final IAuthService auth) 
    {
        _viewManager = viewManager;
        _auth = auth;
        _eventService = eventService;
    }

    @Override
    public final void show()
    {
        while (true) {
            _viewManager.iioReader().clear();

            System.out.println("\n< == BCPA Ticket System == >");
            System.out.println("\n\tLog In");

            try 
            {
                final String username = _viewManager.iioReader().read("\nUsername: ");
                final String password = _viewManager.iioReader().read("Password: ");

                final Result<User> result = _auth.LogInUser(username, password);
                
                final User user = result.value;
                if (user != null) {
                    _viewManager.iioReader().write("\nLogin Success. Loading home page...");
                    Thread.sleep(1500);

                    _viewManager.setActiveView(new EventsView(_viewManager, _eventService, user));
                    break;
                }
            }   
            catch (Exception ex) 
            {

            }
        }
    }
}
