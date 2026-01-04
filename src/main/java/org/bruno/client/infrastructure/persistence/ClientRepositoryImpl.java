package org.bruno.client.infrastructure.persistence;

import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.bruno.client.domain.Client;
import org.bruno.client.domain.ClientRepository;
import org.bruno.client.infrastructure.persistence.model.ClientEntity;

import java.util.List;


@ApplicationScoped
public class ClientRepositoryImpl implements ClientRepository, PanacheRepositoryBase<ClientEntity, Long> {

    @Override
    public Uni<List<Client>> findAllClients() {
        return listAll().map(clientEntities ->
          clientEntities.stream()
            .map(ClientEntity::toDomain)
            .toList()
        );
    }

    @Override
    public Uni<Client> findByName(String name) {
      return find(ClientEntity.SQLClient.NAME, name)
        .firstResult()
        .map(ClientEntity::toDomain);
    }
}
