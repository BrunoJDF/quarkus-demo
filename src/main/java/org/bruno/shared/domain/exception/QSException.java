package org.bruno.shared.domain.exception;

import jakarta.ws.rs.core.Response;

import java.time.ZonedDateTime;

public class QSException extends RuntimeException {
  private final Response.Status status;
  private final String message;
  private final ZonedDateTime timestamp;

  public QSException(Response.Status status, String message) {
    super(message);
    this.status = status;
    this.message = message;
    this.timestamp = ZonedDateTime.now();
  }

  public Response.Status getStatus() {
    return status;
  }

  @Override
  public String getMessage() {
    return message;
  }

  public ZonedDateTime getTimestamp() {
    return timestamp;
  }
}
