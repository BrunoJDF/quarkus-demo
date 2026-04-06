package org.bruno.user.domain;

import java.time.OffsetDateTime;

public class User {
  private Long id;
  private String username;
  private String name;
  private String email;
  private UserType type;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;

  public User(
    Long id, String username, String name, String email, UserType type,
    OffsetDateTime createdAt, OffsetDateTime updatedAt
  ) {
    this.id = id;
    this.username = username;
    this.name = name;
    this.email = email;
    this.type = type;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public User(String username, String name, String email, UserType type,
    OffsetDateTime createdAt, OffsetDateTime updatedAt
  ) {
    this.username = username;
    this.name = name;
    this.email = email;
    this.type = type;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UserType getType() {
    return type;
  }

  public void setType(UserType type) {
    this.type = type;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

}
