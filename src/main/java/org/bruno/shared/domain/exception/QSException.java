package org.bruno.shared.domain.exception;

import java.time.ZonedDateTime;

public class QSException extends RuntimeException {
  private final ErrorStatus status;
  private final String message;
  private final ZonedDateTime timestamp;

  public QSException(ErrorStatus status, String message) {
    super(message);
    this.status = status;
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

  public ErrorStatus getStatus() {
    return status;
  }
}
