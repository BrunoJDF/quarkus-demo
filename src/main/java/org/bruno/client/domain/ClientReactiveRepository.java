package org.bruno.client.domain;

import io.smallrye.mutiny.Uni;
import org.bruno.client.application.ClientSearchCriteria;

import java.util.List;

public interface ClientReactiveRepository {
    Uni<List<Client>> findAllClients();

    Uni<Client> findByName(String name);

    Uni<Void> create(Client toCreate);

    Uni<Void> update(Client toUpdate);

    Uni<List<Client>> findByCriteria(ClientSearchCriteria clientInactive);
}
