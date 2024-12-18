package com.bcpa.authentication.factories.Customer;

import com.bcpa.authentication.models.Customer;

public interface ICustomerFactory {
    public Customer create(String username, String password, String address);
}
