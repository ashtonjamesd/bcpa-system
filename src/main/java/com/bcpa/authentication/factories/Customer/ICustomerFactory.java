package com.bcpa.authentication.factories.Customer;

import com.bcpa.authentication.models.Customer;

public interface ICustomerFactory {
    public Customer create(final String username, final String password, final String address);
}
