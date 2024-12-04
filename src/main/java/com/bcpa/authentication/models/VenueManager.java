package com.bcpa.authentication.models;

public final class VenueManager extends User 
{
    public VenueManager(final String username, final String password) {
        role = UserRole.VenueManager;

        this.username = username;
        this.password = password;
    }
}