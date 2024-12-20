package com.bcpa.app.views.Profile;

import com.bcpa.app.views.PageView;
import com.bcpa.authentication.models.User;

public final class ProfileView extends PageView
{
    private final ProfileViewController _controller;

    private final User _user;

    public ProfileView(final ProfileViewController controller, final User user)
    {
        _controller = controller;
        _user = user;
    }

    @Override
    public final void show()
    {
        while (!_controller.isBackRequested) 
        {
            _controller.displayProfile(_user);
        }
    }
}
