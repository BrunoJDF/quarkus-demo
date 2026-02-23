package org.bruno.shared.infrastructure;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.bruno.shared.application.ErrorResponse;

@Provider
public class QSNotFoundMapper implements ExceptionMapper<NotFoundException> {
  @Override
  public Response toResponse(NotFoundException e) {
    return Response.status(Response.Status.NOT_FOUND)
      .entity(ErrorResponse.errorFactory(e.getMessage()))
      .build();
  }
}
