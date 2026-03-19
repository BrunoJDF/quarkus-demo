package org.bruno.client.domain;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
  List<Client> findAllClients();

  Optional<Client> findByName(String name);

  void create(Client toCreate);
}
