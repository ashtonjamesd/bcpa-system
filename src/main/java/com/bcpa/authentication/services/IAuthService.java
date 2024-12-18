package com.bcpa.authentication.services;

import com.bcpa.app.utils.Result;
import com.bcpa.authentication.models.User;

public interface IAuthService
{
    public Result<User> loginUser(String username, String password);
    public Result<User> registerUser(User user);
}
