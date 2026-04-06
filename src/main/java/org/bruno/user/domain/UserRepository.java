package org.bruno.user.domain;

public interface UserRepository {
  void save(User user);

  User getById(Long id);
}
