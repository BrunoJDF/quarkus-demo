package org.bruno.user.application.input;

import org.bruno.user.domain.User;

public record UserInput(Long id, String username, String name, String email, String type, String createdAt,
    String updatedAt) {

  public User toDomain() {
    return new User(id, username, name, email, type, createdAt, updatedAt);
  }
}
