package org.bruno.shared.infrastructure;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.bruno.shared.application.ErrorResponse;
import org.bruno.shared.domain.CategoryErrorEnum;
import org.bruno.shared.domain.exception.QSException;

@Provider
public class QSExceptionMapper implements ExceptionMapper<QSException> {

  @Override
  public Response toResponse(QSException e) {
    CategoryErrorEnum category = CategoryErrorEnum.getByCode(e.getStatus().getStatusCode());
    return Response.status(e.getStatus())
      .entity(new ErrorResponse(category, e.getMessage(), e.getTimestamp()))
      .build();
  }
}
