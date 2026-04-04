package org.bruno.client.application.input;

import org.bruno.client.domain.Client;

public record CreateClientInput(
    String name,
    String lastName,
    String ruc,
    String email,
    String phone,
    String address) {

  public Client toDomain() {
    Client client = new Client();
    client.setName(name);
    client.setLastName(lastName);
    client.setRuc(ruc);
    client.setEmail(email);
    client.setPhone(phone);
    client.setAddress(address);
    return client;
  }
}
