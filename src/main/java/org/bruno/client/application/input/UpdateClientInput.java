package org.bruno.client.application.input;

import jakarta.validation.constraints.NotNull;
import org.bruno.client.domain.Client;
import org.bruno.client.domain.ClientStatus;

public record UpdateClientInput(
  @NotNull Long id,
  String name,
  String lastName,
  String ruc,
  String email,
  String phone,
  String address,
  ClientStatus status
) {
  public Client toDomain() {
    Client client = new Client();
    client.setId(id);
    client.setName(name);
    client.setLastName(lastName);
    client.setRuc(ruc);
    client.setEmail(email);
    client.setPhone(phone);
    client.setAddress(address);
    client.setStatus(status);

    return client;
  }

  public static UpdateClientInput fromDomain(Client client) {
    return new UpdateClientInput(
      client.getId(),
      client.getName(),
      client.getLastName(),
      client.getRuc(),
      client.getEmail(),
      client.getPhone(),
      client.getAddress(),
      client.getStatus()
    );
  }

  public UpdateClientInput updateStatus(ClientStatus newStatus) {
    return new UpdateClientInput(
      id,
      name,
      lastName,
      ruc,
      email,
      phone,
      address,
      newStatus
    );
  }
}
