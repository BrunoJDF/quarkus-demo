package org.bruno.shared.infrastructure;

import jakarta.ws.rs.core.Response;
import org.bruno.shared.domain.exception.QSException;

public class ErrorStatusFactory {

  private ErrorStatusFactory() {
  }

  public static Response.Status getResponseStatus(QSException e) {
    return switch (e.getStatus()) {
      case BAD_REQUEST -> Response.Status.BAD_REQUEST;
      case NOT_FOUND -> Response.Status.NOT_FOUND;
      case INTERNAL_SERVER_ERROR -> Response.Status.INTERNAL_SERVER_ERROR;
    };
  }
}
