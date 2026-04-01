package org.bruno.client.application;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.bruno.client.application.command.CreateClientCommand;
import org.bruno.client.application.command.UpdateClientCommand;
import org.bruno.client.application.response.ClientResponse;
import org.bruno.client.domain.Client;
import org.bruno.client.domain.ClientReactiveRepository;

import java.util.List;

@WithSession
@ApplicationScoped
public class ClientService {
  private final ClientReactiveRepository clientRepository;

  public ClientService(ClientReactiveRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  public Uni<List<ClientResponse>> getAllClients() {
    return clientRepository.findAllClients()
        .map(clients -> clients.stream()
            .map(ClientResponse::fromDomain)
            .toList());
  }

  public Uni<ClientResponse> getClientByName(String name) {
    return clientRepository.findByName(name)
        .map(ClientResponse::fromDomain);
  }

  @WithTransaction
  public Uni<Void> createClient(CreateClientCommand command) {
    Client toCreate = command.toDomain();
    return clientRepository.create(toCreate);
  }

  @WithTransaction
  public Uni<Void> updateClient(UpdateClientCommand command) {
    Client toUpdate = command.toDomain();
    return clientRepository.update(toUpdate);
  }

  public Uni<List<UpdateClientCommand>> getAllClientsInactive(ClientSearchCriteria clientInactive) {
    return clientRepository.findByCriteria(clientInactive)
      .map(clients -> clients.stream().map(UpdateClientCommand::fromDomain).toList());
  }
}
