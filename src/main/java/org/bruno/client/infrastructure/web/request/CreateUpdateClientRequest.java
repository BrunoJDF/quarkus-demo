package org.bruno.client.infrastructure.web.request;

import jakarta.validation.constraints.NotNull;
import org.bruno.client.application.input.CreateClientInput;
import org.bruno.client.application.input.UpdateClientInput;

public record CreateUpdateClientRequest(
  @NotNull String name,
  @NotNull String lastName,
  String ruc,
  @NotNull String email,
  String phone,
  String address
) {

  public CreateClientInput create() {
    return new CreateClientInput(name, lastName, ruc, email, phone, address);
  }

  public UpdateClientInput update(Long id) {
    return new UpdateClientInput(id, name, lastName, ruc, email, phone, address, null);
  }
}
