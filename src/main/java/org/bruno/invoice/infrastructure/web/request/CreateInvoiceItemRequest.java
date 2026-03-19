package org.bruno.invoice.infrastructure.web.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.bruno.invoice.application.command.dto.CreateInvoiceItemDTO;

public record CreateInvoiceItemRequest(
  @NotBlank String description,
  @Min(1) int quantity
) {
  public static CreateInvoiceItemDTO create(CreateInvoiceItemRequest detail) {
    return new CreateInvoiceItemDTO(
      detail.description(),
      detail.quantity()
    );
  }
}
