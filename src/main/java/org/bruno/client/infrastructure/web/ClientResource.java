package org.bruno.client.infrastructure.web;

import io.smallrye.mutiny.Uni;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.bruno.client.application.ClientService;
import org.bruno.client.application.input.CreateClientInput;
import org.bruno.client.application.input.UpdateClientInput;
import org.bruno.client.application.response.ClientResponse;
import org.bruno.client.infrastructure.web.request.CreateUpdateClientRequest;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.List;

@Path("/clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientResource {

  private final ClientService clientService;

  public ClientResource(ClientService clientService) {
    this.clientService = clientService;
  }

  @Operation(summary = "Get all clients")
  @APIResponse(responseCode = "200", description = "List of clients retrieved successfully")
  @GET
  public Uni<List<ClientResponse>> getAllClients() {
    return clientService.getAllClients();
  }

  @Operation(summary = "Get a client by name")
  @APIResponse(responseCode = "200", description = "Client found successfully")
  @GET
  @Path("/name")
  public Uni<ClientResponse> getClientByName(@QueryParam("name") @NotNull String name) {
    return clientService.getClientByName(name);
  }

  @Operation(summary = "Create a new client")
  @APIResponse(responseCode = "201", description = "Client created successfully")
  @POST
  public Uni<Void> createClient(@Valid CreateUpdateClientRequest clientRequest) {
    CreateClientInput input = clientRequest.create();
    return clientService.createClient(input);
  }

  @Operation(summary = "Update an existing client")
  @APIResponse(responseCode = "200", description = "Client updated successfully")
  @PATCH
  @Path("/{id}")
  public Uni<Void> updateClient(@PathParam("id") Long id, @Valid CreateUpdateClientRequest clientRequest) {
    UpdateClientInput input = clientRequest.update(id);
    return clientService.updateClient(input);
  }
}
