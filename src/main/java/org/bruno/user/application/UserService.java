package org.bruno.user.application;

import jakarta.enterprise.context.ApplicationScoped;
import org.bruno.user.application.input.UserInput;
import org.bruno.user.application.response.UserResponse;
import org.bruno.user.domain.User;
import org.bruno.user.domain.UserRepository;

@ApplicationScoped
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void save(UserInput input) {
    User domain = input.toDomain();
    userRepository.save(domain);
  }

  public UserResponse getById(Long id) {
    User user = userRepository.getById(id);
    return UserResponse.fromDomain(user);
  }
}
