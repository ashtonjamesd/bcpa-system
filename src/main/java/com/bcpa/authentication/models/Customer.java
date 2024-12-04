package com.bcpa.authentication.models;

public final class Customer extends User 
{
    public String address;

    public Customer(final String username, final String password, final String address) {
        role = UserRole.Customer;

        this.username = username;
        this.password = password;
        this.address = address;
    }
}