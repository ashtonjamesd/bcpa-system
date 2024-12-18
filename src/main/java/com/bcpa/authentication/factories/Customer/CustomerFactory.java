package com.bcpa.authentication.factories.Customer;

import com.bcpa.authentication.models.Customer;

public final class CustomerFactory implements ICustomerFactory {

    @Override
    public Customer create(String username, String password, String address) {
        return new Customer(
            username,
            password,
            address
        );
    }
}
