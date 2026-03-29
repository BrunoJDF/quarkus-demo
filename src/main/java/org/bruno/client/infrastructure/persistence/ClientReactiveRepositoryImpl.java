package org.bruno.client.infrastructure.persistence;

import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.bruno.client.domain.Client;
import org.bruno.client.domain.ClientReactiveRepository;
import org.bruno.client.infrastructure.persistence.model.ClientEntity;
import org.bruno.shared.domain.exception.QSNotFoundException;

import java.util.List;

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
    .onItem().ifNotNull().invoke(client -> {
      client.setName(toUpdate.getName());
      client.setLastName(toUpdate.getLastName());
      client.setRuc(toUpdate.getRuc());
      client.setEmail(toUpdate.getEmail());
      client.setPhone(toUpdate.getPhone());
      client.setAddress(toUpdate.getAddress());
    })
    .replaceWithVoid();
  }

}
