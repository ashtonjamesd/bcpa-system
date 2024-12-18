package com.bcpa.authentication.factories.Customer;

import com.bcpa.authentication.models.Customer;

public final class CustomerFactory implements ICustomerFactory {

    @Override
    public final Customer create(final String username, final String password, final String address) {
        return new Customer(
            username,
            password,
            address
        );
    }
}
