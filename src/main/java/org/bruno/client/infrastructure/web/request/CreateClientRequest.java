package org.bruno.client.infrastructure.web.request;

import org.bruno.client.application.command.CreateClientCommand;

public record CreateClientRequest(
  String name,
  String lastName,
  String ruc,
  String email,
  String phone,
  String address
) {

  public CreateClientCommand create() {
    return new CreateClientCommand(name, lastName, ruc, email, phone, address);
  }
}
