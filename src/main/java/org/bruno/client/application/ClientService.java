package org.bruno.client.application;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.bruno.client.application.response.ClientResponse;
import org.bruno.client.domain.ClientRepository;

import java.util.List;

@ApplicationScoped
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @WithSession
    public Uni<List<ClientResponse>> getAllClients() {
        return clientRepository.findAllClients()
          .map(clients ->
            clients.stream()
              .map(ClientResponse::fromDomain)
              .toList()
          );
    }

    @WithSession
    public Uni<ClientResponse> getClientByName(String name) {
        return clientRepository.findByName(name)
          .map(ClientResponse::fromDomain);
    }
}
