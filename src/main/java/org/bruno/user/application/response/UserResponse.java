package org.bruno.user.application.response;

import org.bruno.user.domain.User;

public record UserResponse(String id, String name, String email) {

    public static UserResponse fromDomain(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromDomain'");
    }

}
