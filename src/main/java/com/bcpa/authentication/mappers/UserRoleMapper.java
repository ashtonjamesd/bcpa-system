package com.bcpa.authentication.mappers;

import com.bcpa.authentication.models.UserRole;

public final class UserRoleMapper {
    public final static String map(final UserRole role) {
        return switch (role) {
            case Customer     -> "Customer";
            case Agent        -> "Agent";
            case VenueManager -> "Venue Manager";
            default           -> "Unknown Role";
        };
    }
}
