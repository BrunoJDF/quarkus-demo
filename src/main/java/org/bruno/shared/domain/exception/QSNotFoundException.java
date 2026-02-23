package org.bruno.shared.domain.exception;

public class QSNotFoundException extends QSException {
  public QSNotFoundException(String message) {
    super(ErrorStatus.NOT_FOUND, message);
  }
}
