
package org.bruno.user.infrastructure.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.bruno.user.domain.User;
import org.bruno.user.domain.UserType;

import java.time.OffsetDateTime;

@Entity
@Table(name = UserEntity.SQLUser.TABLE_NAME)
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;

  @Column(name = UserEntity.SQLUser.USERNAME)
  public String username;

  @Column(name = UserEntity.SQLUser.NAME)
  public String name;

  @Column(name = UserEntity.SQLUser.EMAIL)
  public String email;

  @Enumerated(EnumType.STRING)
  @Column(name = UserEntity.SQLUser.TYPE)
  public UserType type;

  @Column(name = UserEntity.SQLUser.CREATED_AT)
  public OffsetDateTime createdAt;

  @Column(name = UserEntity.SQLUser.UPDATED_AT)
  public OffsetDateTime updatedAt;

  public UserEntity() {
  }

  private UserEntity(User domain) {
    this.id = domain.getId();
    this.username = domain.getUsername();
    this.name = domain.getName();
    this.email = domain.getEmail();
    this.type = domain.getType();
    this.createdAt = domain.getCreatedAt();
    this.updatedAt = domain.getUpdatedAt();
  }

  public static class SQLUser {
    static final String TABLE_NAME = "user";

    private SQLUser() {
      throw new IllegalStateException("Utility class");
    }

    public static final String ID = "id";
    public static final String USERNAME = "username";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String TYPE = "type";
    public static final String CREATED_AT = "created_at";
    public static final String UPDATED_AT = "updated_at";
  }

  public static UserEntity fromDomain(User user) {
    if (user == null) {
      throw new IllegalArgumentException("User cannot be null");
    }
    return new UserEntity(user);
  }

  public User toDomain() {
    return new User(id, username, name, email, type, createdAt, updatedAt);
  }
}
