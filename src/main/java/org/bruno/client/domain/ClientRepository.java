package org.bruno.client.domain;

import java.util.List;

import io.smallrye.mutiny.Uni;

public interface ClientRepository {
    Uni<List<Client>> findAllClients();

    Uni<Client> findByName(String name);

    Uni<Void> create(Client toCreate);
}
