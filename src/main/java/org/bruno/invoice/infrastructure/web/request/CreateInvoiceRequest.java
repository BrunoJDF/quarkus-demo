package org.bruno.invoice.infrastructure.web.request;

import org.bruno.invoice.application.command.CreateInvoiceCommand;

import java.util.List;

public record CreateInvoiceRequest(
  String customerName,
  String customerEmail,
  String customerAddress,
  String currency,
  List<CreateInvoiceItemRequest> items
) {

  public static CreateInvoiceCommand create(CreateInvoiceRequest request) {
    return new CreateInvoiceCommand(
      request.customerName(),
      request.customerEmail(),
      request.customerAddress(),
      request.currency(),
      request.items().stream()
        .map(CreateInvoiceItemRequest::create)
        .toList()
    );
  }
}
