package org.bruno.client.application.command;

import org.bruno.client.domain.Client;

import jakarta.validation.constraints.NotNull;

public record UpdateClientCommand(
  @NotNull Long id,
  String name,
  String lastName,
  String ruc,
  String email,
  String phone,
  String address
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
    return client;
  }
}
