package com.bcpa.authentication.services;

import java.util.List;

import com.bcpa.app.utils.Result;
import com.bcpa.authentication.models.User;
import com.bcpa.authentication.repositories.IUserRepository;

public final class AuthService implements IAuthService
{
    private final IUserRepository _userRepository;
    private final PasswordHasher _hasher;

    public AuthService(final PasswordHasher hasher, final IUserRepository userRepository) {
        _hasher = hasher;
        _userRepository = userRepository;
    }

    @Override
    public final Result<User> LogInUser(final String username, final String password) 
    {
        try 
        {
            final List<User> users = _userRepository.getUsers().value;

            for (final User user : users) {
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