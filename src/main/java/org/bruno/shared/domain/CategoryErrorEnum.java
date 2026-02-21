package org.bruno.shared.domain;

public enum CategoryErrorEnum {
  NOT_FOUND("Category not found"),
  ALREADY_EXISTS("Category already exists"),
  INVALID_DATA("Invalid category data");

  private final String message;

  CategoryErrorEnum(String message) {
    this.message = message;
  }

  public static CategoryErrorEnum getByCode(int statusCode) {
    return switch (statusCode) {
      case 404 -> NOT_FOUND;
      case 409 -> ALREADY_EXISTS;
      case 400 -> INVALID_DATA;
      default -> throw new IllegalArgumentException("Unknown status code: " + statusCode);
    };
  }

  public String getMessage() {
    return message;
  }
}
