package com.bcpa.authentication.services;

import com.bcpa.app.utils.Result;
import com.bcpa.authentication.models.User;
import com.bcpa.database.DbContext;

public final class AuthService implements IAuthService
{
    private final DbContext _db;
    private final PasswordHasher _hasher;

    public AuthService(DbContext db, PasswordHasher hasher) {
        _db = db;
        _hasher = hasher;
    }

    @Override
    public Result<User> LogInUser(String username, String password) 
    {
        try 
        {
            for (User user : _db.users) {
                if (!user.username.equals(username)) continue;

                if (_hasher.check(password, user.password)) 
                    return Result.Ok(user);
            }
    
            return Result.Ok(null);
        }
        catch (Exception ex) 
        {
            return Result.Err(ex.getMessage());
        }
    }
}