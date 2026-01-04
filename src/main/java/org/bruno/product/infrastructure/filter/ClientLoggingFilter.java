package org.bruno.product.infrastructure.filter;

import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.client.ClientResponseContext;
import jakarta.ws.rs.client.ClientResponseFilter;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

@Provider
public class ClientLoggingFilter implements ClientRequestFilter, ClientResponseFilter {
  private static final Logger LOG = Logger.getLogger(ClientLoggingFilter.class);

  @Override
  public void filter(ClientRequestContext clientRequestContext) {
    String message = String.format("Request: %s %s", clientRequestContext.getMethod(), clientRequestContext.getUri());
    LOG.info(message);
    String headers = String.format("Request Headers: %s", clientRequestContext.getHeaders());
    LOG.debug(headers);
  }

  @Override
  public void filter(ClientRequestContext clientRequestContext, ClientResponseContext clientResponseContext) {
    String message = String.format("Response: %s %s", clientRequestContext.getMethod(), clientRequestContext.getUri());
    LOG.info(message);
    String headers = String.format("Response Headers: %s", clientResponseContext.getHeaders());
    LOG.debug(headers);
  }
}
