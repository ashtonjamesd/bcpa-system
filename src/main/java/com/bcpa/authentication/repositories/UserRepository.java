package com.bcpa.authentication.repositories;

import java.util.List;

import com.bcpa.app.utils.Result;
import com.bcpa.authentication.models.User;
import com.bcpa.authentication.models.VenueManager;
import com.bcpa.authentication.services.PasswordHasher;
import com.bcpa.database.DbContext;

public final class UserRepository implements IUserRepository 
{
    private final DbContext _db;
    private final PasswordHasher _hasher;

    public UserRepository(final DbContext db, final PasswordHasher hasher) {
        _db = db;
        _hasher = hasher;
    }

    @Override
    public final Result<List<User>> getUsers()
    {
        try
        {
            createUser(new VenueManager("admin", "admin")); // remove this later
            return Result.Ok(_db.users);
        }
        catch (Exception ex)
        {
            return Result.Err(ex.getMessage());
        }
    }

    @Override
    public final Result<Boolean> createUser(final User newUser)
    {
        try
        {
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
