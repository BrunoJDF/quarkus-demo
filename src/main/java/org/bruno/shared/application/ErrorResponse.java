package org.bruno.shared.application;

import org.bruno.shared.domain.exception.QSException;

import java.time.ZonedDateTime;

public record ErrorResponse(
  String message,
  ZonedDateTime timestamp
) {
  public static ErrorResponse errorFactory(QSException e) {
    return new ErrorResponse(e.getMessage(), e.getTimestamp());
  }

  public static ErrorResponse errorFactory(String message) {
    return new ErrorResponse(message, ZonedDateTime.now());
  }
}
