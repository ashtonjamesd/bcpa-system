package com.bcpa.authentication.mappers;

import com.bcpa.authentication.models.UserRole;

public class UserRoleMapper {
    public static String map(UserRole role) {
        return switch (role) {
            case Customer     -> "Customer";
            case Agent        -> "Agent";
            case VenueManager -> "Venue Manager";
            default           -> "Unknown Role";
        };
    }
}
