package org.bruno.client.infrastructure.persistence;

import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.bruno.client.application.ClientSearchCriteria;
import org.bruno.client.domain.Client;
import org.bruno.client.domain.ClientReactiveRepository;
import org.bruno.client.infrastructure.persistence.model.ClientEntity;
import org.bruno.shared.domain.exception.QSNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class ClientReactiveRepositoryImpl
  implements ClientReactiveRepository, PanacheRepositoryBase<ClientEntity, Long> {

  @Override
  public Uni<List<Client>> findAllClients() {
    return listAll().map(clientEntities -> clientEntities.stream()
      .map(ClientEntity::toDomain)
      .toList());
  }

  @Override
  public Uni<Client> findByName(String name) {
    return find(ClientEntity.SQLClient.NAME, name)
      .firstResult()
      .map(ClientEntity::toDomain);
  }

  @Override
  public Uni<Void> create(Client toCreate) {
    ClientEntity entity = ClientEntity.fromDomain(toCreate);
    return persist(entity).replaceWithVoid();
  }

  @Override
  public Uni<Void> update(Client toUpdate) {
    return findById(toUpdate.getId())
      .onItem().ifNull().failWith(() -> new QSNotFoundException("Client not found"))
      .onItem().ifNotNull().invoke(client ->
        ClientEntity.updateFromDomain(client, toUpdate)
      )
      .replaceWithVoid();
  }

  @Override
  public Uni<List<Client>> findByCriteria(ClientSearchCriteria clientInactive) {
    Map<String, Object> params = new HashMap<>();
    List<String> conditions = new ArrayList<>();
    Optional.ofNullable(clientInactive.getStatus()).ifPresent(status -> {
      conditions.add(ClientEntity.SQLClient.STATUS + " = :status");
      params.put("status", status);
    });

    String whereClause = conditions.isEmpty() ? "" : " where " + String.join(" and ", conditions);

    if (whereClause.isEmpty()) {
      return listAll().map(clientEntities -> clientEntities.stream()
        .map(ClientEntity::toDomain)
        .toList());
    } else {
      return find(whereClause, params).list().map(clientEntities -> clientEntities.stream()
        .map(ClientEntity::toDomain)
        .toList());
    }
  }
}
