package org.bruno.client.domain;

public enum ClientStatus {
  ACTIVE(1, "Activo"),
  INACTIVE(2, "Inactivo");

  private final int priority;
  private final String description;

  ClientStatus(int priority, String description) {
    this.priority = priority;
    this.description = description;
  }

  public int getPriority() {
    return priority;
  }

  public String getDescription() {
    return description;
  }
}
