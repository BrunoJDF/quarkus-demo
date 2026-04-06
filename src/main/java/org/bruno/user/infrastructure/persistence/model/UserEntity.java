
package org.bruno.user.infrastructure.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.bruno.user.domain.User;

@Entity
@Table(name = UserEntity.SQLUser.TABLE_NAME)
public class UserEntity {

  @Id
  @Column(name = UserEntity.SQLUser.ID, nullable = false)
  public Long id;

  @Column(name = UserEntity.SQLUser.NAME)
  public String name;

  @Column(name = UserEntity.SQLUser.EMAIL)
  public String email;

  public UserEntity(Long id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
  }

  private UserEntity(User domain) {
    this.id = domain.getId();
    this.name = domain.getName();
    this.email = domain.getEmail();
  }

  public static class SQLUser {
    static final String TABLE_NAME = "user";

    private SQLUser() {
      throw new IllegalStateException("Utility class");
    }

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
  }

  public static UserEntity fromDomain(User user) {
    if (user == null) {
      return null;
    }
    return new UserEntity(user);
  }

  public User toDomain() {
    return new User(id, name, email);
  }
}
