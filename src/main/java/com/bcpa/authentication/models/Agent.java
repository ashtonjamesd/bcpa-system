package com.bcpa.authentication.models;

public final class Agent extends User
{
    public Agent(final String username, final String password) {
        role = UserRole.Agent;

        this.username = username;
        this.password = password;
    }
}