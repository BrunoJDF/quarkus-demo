package org.bruno.shared.domain.exception;

import java.time.ZonedDateTime;

public class QSException extends RuntimeException {
  private final String message;
  private final ZonedDateTime timestamp;

  public QSException(String message) {
    super(message);
    this.message = message;
    this.timestamp = ZonedDateTime.now();
  }

  @Override
  public String getMessage() {
    return message;
  }

  public ZonedDateTime getTimestamp() {
    return timestamp;
  }
}
