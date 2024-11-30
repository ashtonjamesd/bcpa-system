package com.bcpa.authentication.models;

public final class Agent extends User 
{
    
    public Agent(String username, String password) {
        role = UserRole.Agent;

        this.username = username;
        this.password = password;
    }
}