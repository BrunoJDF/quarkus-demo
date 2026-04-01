package org.bruno.client.infrastructure.web.request;

import jakarta.validation.constraints.NotNull;
import org.bruno.client.application.command.CreateClientCommand;
import org.bruno.client.application.command.UpdateClientCommand;

public record CreateUpdateClientRequest(
  @NotNull String name,
  @NotNull String lastName,
  String ruc,
  @NotNull String email,
  String phone,
  String address
) {

  public CreateClientCommand create() {
    return new CreateClientCommand(name, lastName, ruc, email, phone, address);
  }

  public UpdateClientCommand update(Long id) {
    return new UpdateClientCommand(id, name, lastName, ruc, email, phone, address, null);
  }
}
