package org.bruno.client.application;

import org.bruno.client.domain.ClientStatus;

public class ClientSearchCriteria {
  private final ClientStatus status;

  public ClientSearchCriteria(ClientStatus status) {
    this.status = status;
  }

  public ClientStatus getStatus() {
    return status;
  }
}
