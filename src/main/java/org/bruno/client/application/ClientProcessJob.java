package org.bruno.client.application;

import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.scheduler.Scheduled;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.bruno.client.application.command.UpdateClientCommand;
import org.bruno.client.domain.ClientStatus;
import org.jboss.logging.Logger;

@ApplicationScoped
public class ClientProcessJob {
  private static final Logger LOGGER = Logger.getLogger(ClientProcessJob.class);
  private final ClientService clientService;

  public ClientProcessJob(ClientService clientService) {
    this.clientService = clientService;
  }

  @Scheduled(every = "30s")
  @WithTransaction
  public Uni<Void> activeAllClientsJob() {
    ClientSearchCriteria clientCriteriaInactive = new ClientSearchCriteria(ClientStatus.INACTIVE);
    return clientService.getAllClientsInactive(clientCriteriaInactive)
      .onItem().transformToMulti(clients -> Multi.createFrom().iterable(clients))
      .onItem().transformToUniAndMerge(client -> {
        UpdateClientCommand updateCommand = client.updateStatus(ClientStatus.ACTIVE);
        return clientService.updateClient(updateCommand)
          .onItem().invoke(() ->
            LOGGER.info("Client with id " + client.id() + " activated successfully")
          )
          .onFailure().recoverWithNull()
          .onFailure().invoke(failure ->
            LOGGER.error("Failed to activate client " + client.id(), failure)
          );
      })
      .collect().asList()
      .onItem().invoke(updatedClients ->
        LOGGER.info("Total clients activated: " + updatedClients.size())
      )
      .replaceWithVoid();
  }
}
