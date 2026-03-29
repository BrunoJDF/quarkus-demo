package org.bruno.client.application.response;

import org.bruno.client.domain.Client;

public record ClientResponse(
  Long id,
  String name,
  String lastName,
  String fullName,
  String ruc,
  String email,
  String phone,
  String address,
  String status
) {
  public static ClientResponse fromDomain(Client client) {
    String status = client.getStatus() != null ? client.getStatus().name() : "INACTIVE";

    return new ClientResponse(
      client.getId(),
      client.getName(),
      client.getLastName(),
      client.getFullName(),
      client.getRuc(),
      client.getEmail(),
      client.getPhone(),
      client.getAddress(),
      status
    );
  }
}
