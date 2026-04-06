package org.bruno.user.application.input;

import org.bruno.user.domain.User;
import org.bruno.user.domain.UserType;

import java.time.OffsetDateTime;

public record UserInput(String username, String name, String email, UserType type) {

  public User toDomain() {
    return new User(username, name, email, type, OffsetDateTime.now(), OffsetDateTime.now());
  }
}
