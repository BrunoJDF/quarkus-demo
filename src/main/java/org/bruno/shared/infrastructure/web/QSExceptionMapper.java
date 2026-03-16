package org.bruno.shared.infrastructure.web;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.bruno.shared.application.ErrorResponse;
import org.bruno.shared.domain.exception.QSException;
import org.bruno.shared.infrastructure.ErrorStatusFactory;

@Provider
public class QSExceptionMapper implements ExceptionMapper<QSException> {

  @Override
  public Response toResponse(QSException e) {
    return Response.status(ErrorStatusFactory.getResponseStatus(e))
      .entity(ErrorResponse.errorFactory(e))
      .build();
  }
}
