package org.bruno.shared.domain.exception;

import org.bruno.shared.domain.ErrorStatus;

public class QSNotFoundException extends QSException {
  public QSNotFoundException(String message) {
    super(ErrorStatus.NOT_FOUND, message);
  }
}
