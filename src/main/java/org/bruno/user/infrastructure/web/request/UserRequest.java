package org.bruno.user.infrastructure.web.request;

import org.bruno.user.application.input.UserInput;

public record UserRequest(Long id, String name, String email) {

  public UserInput toInput() {
    return new UserInput(id, name, email);
  }

}
