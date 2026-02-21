package org.bruno.shared.domain.exception;

import jakarta.ws.rs.core.Response;

@SuppressWarnings("java:S2166")
public class QSNotFoundException extends QSException {
  public QSNotFoundException(String message) {
    super(Response.Status.NOT_FOUND, message);
  }
}
