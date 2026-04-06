package org.bruno.user.infrastructure.web.request;

import org.bruno.user.application.input.UserInput;
import org.bruno.user.domain.UserType;

public record UserRequest(String username, String name, String email) {

  public UserInput toInput() {
    return new UserInput(username, name, email, UserType.EMPLOYEE);
  }

}
