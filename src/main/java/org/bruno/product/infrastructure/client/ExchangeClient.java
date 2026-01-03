package org.bruno.product.infrastructure.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.bruno.product.infrastructure.client.dto.ExchangeDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/v6")
@RegisterRestClient(configKey = "ex-change-client")
@Produces(MediaType.APPLICATION_JSON)
public interface ExchangeClient {

  @GET
  @Path("/{apiKey}/latest/{currency}")
  ExchangeDTO getExChangeDTO(@PathParam("apiKey") String apiKey, @PathParam("currency") String currency);
}
