package org.bruno.client.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bruno.client.domain.Client;
import org.bruno.client.domain.ClientRepository;
import org.bruno.client.infrastructure.persistence.model.ClientEntity;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ClientRepositoryImpl implements ClientRepository, PanacheRepository<ClientEntity> {

  @Override
  public List<Client> findAllClients() {
    return listAll().stream()
      .map(ClientEntity::toDomain)
      .toList();
  }

  @Override
  public Optional<Client> findByName(String name) {
    return find(ClientEntity.SQLClient.NAME, name)
      .firstResultOptional()
      .map(ClientEntity::toDomain);
  }

  @Override
  public void create(Client toCreate) {
    ClientEntity entity = ClientEntity.fromDomain(toCreate);
    persist(entity);
  }
}
