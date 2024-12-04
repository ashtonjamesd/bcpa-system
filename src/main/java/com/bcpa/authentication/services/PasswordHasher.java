package com.bcpa.authentication.services;

import org.mindrot.jbcrypt.BCrypt;

public final class PasswordHasher {
    public final String hash(final String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public final boolean check(final String password, final String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}