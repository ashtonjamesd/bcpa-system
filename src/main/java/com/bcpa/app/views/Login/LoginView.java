package com.bcpa.app.views.Login;
import com.bcpa.App;
import com.bcpa.app.utils.Result;
import com.bcpa.app.views.PageView;
import com.bcpa.app.views.Events.EventsView;
import com.bcpa.app.views.ViewManager.IViewManager;
import com.bcpa.authentication.models.User;
import com.bcpa.authentication.models.VenueManager;
import com.bcpa.authentication.repositories.UserRepository;
import com.bcpa.authentication.services.IAuthService;
import com.bcpa.database.DbContext;
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
            _viewManager.ioReader().clear();

            System.out.println("\n< == BCPA Ticket System == >");

            var db = App.container.resolve(DbContext.class);
            System.out.println("\n\t Log In" + "(" + db.users.size() + ")");

            try 
            {
                final String username = _viewManager.ioReader().read("\nUsername: ");
                final String password = _viewManager.ioReader().read("Password: ");

                final Result<User> result = _auth.LogInUser(username, password);
                
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
