package org.bruno.user.application.input;

import org.bruno.user.domain.User;

public record UserInput(Long id, String name, String email) {

  public User toDomain() {
    return new User(id, name, email);
  }
}
