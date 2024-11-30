package com.bcpa.app.views.Home;

import com.bcpa.app.services.IIOReader;
import com.bcpa.app.views.PageView;
import com.bcpa.authentication.mappers.UserRoleMapper;
import com.bcpa.authentication.models.User;

public final class HomeView extends PageView {
    private final User _user;

    public HomeView(IIOReader ioReader, User user) 
    {
        _ioReader = ioReader;
        _user = user;
    }

    @Override
    public void show() {

        while (true) {
            _ioReader.clear();

            _ioReader.write(">- Home View -<");
            _ioReader.write(String.format("Currently logged in as: %s Role: %s", _user.username, UserRoleMapper.map(_user.role)));
    
            break;
        }
    }
}
