package com.bcpa.authentication.repositories;

import java.util.List;

import com.bcpa.app.utils.Result;
import com.bcpa.authentication.models.User;

public interface IUserRepository 
{
    public Result<List<User>> getUsers();
    public Result<Boolean> createUser(final User user);
}
