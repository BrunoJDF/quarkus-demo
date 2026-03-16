package org.bruno.invoice.infrastructure.web.request;

import org.bruno.invoice.application.command.CreateInvoiceCommand;

public record CreateInvoiceRequest(
  String customerName,
  String customerEmail,
  String customerAddress,
  String productName,
  int quantity,
  String currency
) {

  public static CreateInvoiceCommand create(CreateInvoiceRequest request) {
    return new CreateInvoiceCommand(
      request.customerName,
      request.customerEmail,
      request.customerAddress,
      request.productName,
      request.quantity,
      request.currency
    );
  }
}
