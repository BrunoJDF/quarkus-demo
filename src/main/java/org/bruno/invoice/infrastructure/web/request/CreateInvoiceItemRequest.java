package org.bruno.invoice.infrastructure.web.request;

import org.bruno.invoice.application.command.dto.CreateInvoiceItemDTO;

public record CreateInvoiceItemRequest(
  String description,
  int quantity,
  double unitPrice
) {
  public static CreateInvoiceItemDTO create(CreateInvoiceItemRequest detail) {
    return new CreateInvoiceItemDTO(
      detail.description(),
      detail.quantity(),
      detail.unitPrice()
    );
  }
}
