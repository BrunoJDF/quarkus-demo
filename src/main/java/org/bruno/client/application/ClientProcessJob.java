package org.bruno.client.application;

import org.jboss.logging.Logger;

import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.scheduler.Scheduled;
import io.smallrye.mutiny.Uni;

public class ClientProcessJob {
  private static final Logger LOGGER = Logger.getLogger(ClientProcessJob.class);
  private final ClientService clientService;

  @Scheduled(every = "30s")
  @WithTransaction
  public Uni<Void> activeAllClientsJob() {
    return Uni.createFrom().voidItem()
        .onItem().invoke(() -> {
          var clientsUpdated = clientService.activateAllClients();
          LOGGER.info("Running activeAllClientsJob...");
        });
  }
}
