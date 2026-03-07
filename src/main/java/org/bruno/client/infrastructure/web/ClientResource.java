package org.bruno.client.infrastructure.web;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.bruno.client.application.ClientService;
import org.bruno.client.application.command.CreateClientCommand;
import org.bruno.client.application.response.ClientResponse;
import org.bruno.client.infrastructure.web.request.CreateClientRequest;

import java.util.List;

@Path("/clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientResource {

    private final ClientService clientService;

    public ClientResource(ClientService clientService) {
        this.clientService = clientService;
    }

    @GET
    public Uni<List<ClientResponse>> getAllClients() {
        return clientService.getAllClients();
    }

    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Uni<List<ClientResponse>> streamAllClients() {
        return null;
    }

    @GET
    @Path("/name")
    public Uni<ClientResponse> getClientByName(@QueryParam("name") String name) {
        return clientService.getClientByName(name);
    }

    @POST
    public Uni<Void> createClient(CreateClientRequest clientRequest) {
        CreateClientCommand command = clientRequest.create();
        return clientService.createClient(command);
    }
}
