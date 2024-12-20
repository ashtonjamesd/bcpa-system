package com.bcpa.app.views.Profile;

import java.util.List;

import com.bcpa.App;
import com.bcpa.app.views.Events.EventViewController;
import com.bcpa.app.views.Events.EventsView;
import com.bcpa.app.views.ViewManager.IViewManager;
import com.bcpa.authentication.mappers.UserRoleMapper;
import com.bcpa.authentication.models.User;
import com.bcpa.authentication.services.IAuthService;

public final class ProfileViewController
{
    private final IViewManager _viewManager;
    private final IAuthService _authService;

    public boolean isBackRequested = false;

    public ProfileViewController(final IViewManager viewManager, final IAuthService authService)
    {
        _viewManager = viewManager;
        _authService = authService;
    }

    public void displayProfile(User user)
    {
        _viewManager.ioReader().clear();

        final String title = _viewManager.widgetService().toTitle("BCPA User Profile");
        _viewManager.ioReader().write(title);

        final String details = switch (user.role) {
            case Customer     -> getCustomerDetails(user);
            case Agent        -> getAgentDetails(user);
            case VenueManager -> getVenueManagerDetails(user);
            default           -> "";
        };

        final var options = List.of("Change Password", "Back");
        final var option = _viewManager.widgetService().menuOptions(details, options);
        
        if (option == "Change Password") 
        {
            onChangePassword(user);
            _viewManager.setActiveView(new EventsView(App.container.resolve(EventViewController.class), user));
        }
        else if (option == "Back") 
        {
            isBackRequested = true;
            _viewManager.setActiveView(new EventsView(App.container.resolve(EventViewController.class), user));
        }
    }

    private final void onChangePassword(User user)
    {
        while (true) 
        {
            _viewManager.ioReader().clear();
            _viewManager.ioReader().write("\n");

            final String oldPassword = _viewManager.ioReader().read("  Old Password: ");
            final String newPassword = _viewManager.ioReader().read("\n  New Password: ");
            final String confirmNewPassword = _viewManager.ioReader().read("  Confirm New Password: ");

            if (!newPassword.equals(confirmNewPassword)) 
            {
                _viewManager.ioReader().write("\nNew passwords do not match.");
                _viewManager.ioReader().readKey();
                continue;
            }

            final var result = _authService.loginUser(user.username, oldPassword);
            if (result.value == null) 
            {
                _viewManager.ioReader().write("\nFailed to change password, incorrect details.");
                _viewManager.ioReader().readKey();

                isBackRequested = true;
                break;
            }
            else 
            {
                _viewManager.ioReader().write("\nPassword changed successfully.");
                _viewManager.widgetService().showLoadingIcon("Returning to profile");

                isBackRequested = true;
                break;
            }
        }
    }

    private final String getCustomerDetails(User user)
    {
        return "";
    }
    
    private final String getAgentDetails(User user)
    {
        return "";
    }

    private final String getVenueManagerDetails(User user)
    {
        final String username = "Username: " + user.username;
        final String role = "\nRole:       " + UserRoleMapper.map(user.role);
        
        return username + role;
    }
}

