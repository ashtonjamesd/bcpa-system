package com.bcpa.app.views.Profile;

import java.util.List;

import com.bcpa.app.views.PageView;
import com.bcpa.app.views.Home.HomeView;
import com.bcpa.app.views.ViewManager.IViewManager;
import com.bcpa.authentication.mappers.UserRoleMapper;
import com.bcpa.authentication.models.User;
import com.bcpa.authentication.services.IAuthService;

public final class ProfileView extends PageView
{
    private final IViewManager _viewManager;
    private final IAuthService _authService;
    private final User _user;

    public ProfileView(final IViewManager viewManager, final IAuthService authService, final User user)
    {
        _viewManager = viewManager;
        _authService = authService;
        _user = user;
    }

    @Override
    public final void show()
    {
        boolean isBackRequested = false;

        while (true)
        {
            if (isBackRequested) 
            {
                _viewManager.setActiveView(HomeView.class);
            }

            _viewManager.ioReader().clear();

            final String title = _viewManager.widgetService().toTitle("BCPA User Profile");
            _viewManager.ioReader().write(title);

            final String details = switch (_user.role) {
                case Customer     -> getCustomerDetails();
                case Agent        -> getAgentDetails();
                case VenueManager -> getVenueManagerDetails();
                default           -> "";
            };

            final var options = List.of("Change Password", "Back");
            final var option = _viewManager.widgetService().menuOptions(details, options);
            
            if (option == "Change Password") 
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
                    }
                    else 
                    {
                        final var result = _authService.loginUser(_user.username, oldPassword);
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
            }
            else if (option == "Back") 
            {
                _viewManager.setActiveView(HomeView.class);
                break;
            }
        }
    }

    private final String getCustomerDetails() {
        return "";
    }
    
    private final String getAgentDetails() {
        return "";
    }

    private final String getVenueManagerDetails() {
        final String username = "Username: " + _user.username;
        final String role = "\nRole:       " + UserRoleMapper.map(_user.role);
        
        return username + role;
    }
}
