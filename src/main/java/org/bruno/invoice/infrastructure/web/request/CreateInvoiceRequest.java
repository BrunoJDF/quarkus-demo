package org.bruno.invoice.infrastructure.web.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.bruno.invoice.application.command.CreateInvoiceCommand;

import java.util.List;

public record CreateInvoiceRequest(
  @NotNull String customerName,
  @NotNull String currency,
  @NotEmpty @Valid List<CreateInvoiceItemRequest> items
) {

  public static CreateInvoiceCommand create(CreateInvoiceRequest request) {
    return new CreateInvoiceCommand(
      request.customerName(),
      request.currency(),
      request.items().stream()
        .map(CreateInvoiceItemRequest::create)
        .toList()
    );
  }
}
