package com.bcpa.app.views.Login;

import com.bcpa.app.views.PageView;
import com.bcpa.app.views.ViewManager.IViewManager;

public final class LoginView extends PageView
{
    private final LoginViewController _controller;
    private final IViewManager _viewManager;

    public LoginView(final IViewManager viewManager, final LoginViewController controller) 
    {
        _controller = controller;
        _viewManager = viewManager;
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

                _controller.tryLogin(username, password);
                break;
            }   
            catch (final Exception ex)
            {
                // punish user for causing an exception to be thrown
                // Thread.sleep(9999 * (1000));
            }
        }
    }
}
