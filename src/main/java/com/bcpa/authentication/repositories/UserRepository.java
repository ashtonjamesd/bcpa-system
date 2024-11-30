package com.bcpa.authentication.repositories;

import java.util.List;

import com.bcpa.app.utils.Result;
import com.bcpa.authentication.models.User;
import com.bcpa.authentication.services.PasswordHasher;
import com.bcpa.database.DbContext;

public final class UserRepository implements IUserRepository 
{
    private final DbContext _db;

    private final PasswordHasher _hasher;

    public UserRepository(DbContext db, PasswordHasher hasher) {
        _db = db;
        _hasher = hasher;
    }

    @Override
    public Result<List<User>> GetUsers()
    {
        try
        {
            return Result.Ok(_db.users);
        }
        catch (Exception ex)
        {
            return Result.Err(ex.getMessage());
        }
    }

    @Override
    public Result<Boolean> CreateUser(User newUser)
    {
        try
        {
            boolean userExists = false;
            for (User user : _db.users) {
                if (!user.username.equals(newUser.username)) continue;

                userExists = true;
                break;
            }

            if (userExists) 
                return Result.Err("User with that username already exists.");

            newUser.password = _hasher.hash(newUser.password);
            _db.users.add(newUser);

            return Result.Ok(true);
        }
        catch (Exception ex)
        {
            return Result.Err(ex.getMessage());
        }
    }
}
