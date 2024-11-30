package com.bcpa.app.views.Login;
import com.bcpa.app.services.IIOReader;
import com.bcpa.app.utils.Result;
import com.bcpa.app.views.PageView;
import com.bcpa.app.views.Home.HomeView;
import com.bcpa.app.views.ViewManager.IViewManager;
import com.bcpa.authentication.models.User;
import com.bcpa.authentication.services.IAuthService;

public final class LoginView extends PageView 
{
    private final IViewManager _viewManager;

    private final IAuthService _auth;

    public LoginView(IViewManager viewManager, IAuthService auth, IIOReader ioReader) 
    {
        _viewManager = viewManager;
        _auth = auth;
        _ioReader = ioReader;
    }

    @Override
    public void show()
    {
        while (true) {
            _ioReader.clear();

            System.out.println("\n< == BCPA Ticket System == >");
            System.out.println("\n\tLog In");

            try 
            {
                String username = _ioReader.read("\nUsername: ");
                String password = _ioReader.read("Password: ");

                Result<User> result = _auth.LogInUser(username, password);
                
                User user = result.value;
                if (user != null) {
                    _ioReader.write("\nLogin Success. Loading home page...");
                    Thread.sleep(1500);

                    _viewManager.setActiveView(new HomeView(_ioReader, user));
                    break;
                }
            }   
            catch (Exception ex) 
            {

            }
        }
    }
}
