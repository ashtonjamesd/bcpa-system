package com.bcpa.authentication.models;

public final class VenueManager extends User 
{

    public VenueManager(String username, String password) {
        role = UserRole.VenueManager;

        this.username = username;
        this.password = password;
    }
}