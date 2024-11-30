package com.bcpa.authentication.services;

import com.bcpa.app.utils.Result;
import com.bcpa.authentication.models.User;

public interface IAuthService
{
    public Result<User> LogInUser(String username, String password);
}
