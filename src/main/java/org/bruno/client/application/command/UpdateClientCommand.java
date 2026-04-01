package org.bruno.client.application.command;

import jakarta.validation.constraints.NotNull;
import org.bruno.client.domain.Client;
import org.bruno.client.domain.ClientStatus;

public record UpdateClientCommand(
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

  public static UpdateClientCommand fromDomain(Client client) {
    return new UpdateClientCommand(
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

  public UpdateClientCommand updateStatus(ClientStatus newStatus) {
    return new UpdateClientCommand(
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
