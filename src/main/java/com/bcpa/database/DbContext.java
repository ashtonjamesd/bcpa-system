package com.bcpa.database;

import java.util.ArrayList;
import java.util.List;

import com.bcpa.authentication.models.User;

/**
 * This is a pseudo-implementation of a database context, created specifically
 * for simulation and testing purposes.
 * 
 * The data is very real, it is just stored in memory.
 */
public class DbContext 
{
    public DbContext() 
    {
        
    }

    public List<User> users = new ArrayList<>();
}