package org.bruno.user.infrastructure.persistence;

import jakarta.enterprise.context.ApplicationScoped;

import org.bruno.shared.domain.exception.QSNotFoundException;
import org.bruno.user.domain.User;
import org.bruno.user.domain.UserRepository;
import org.bruno.user.infrastructure.persistence.model.UserEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository, PanacheRepository<UserEntity> {

  @Override
  public void save(User user) {
    UserEntity entity = UserEntity.fromDomain(user);
    persist(entity);
  }

  @Override
  public User getById(Long id) {
    return findByIdOptional(id)
        .map(UserEntity::toDomain)
        .orElseThrow(() -> new QSNotFoundException("User not found with id: " + id));
  }

}
