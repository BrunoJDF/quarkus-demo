package org.bruno.invoice.application.command;

import org.bruno.invoice.application.command.dto.CreateInvoiceItemDTO;
import org.bruno.invoice.domain.Invoice;

import java.math.BigDecimal;
import java.util.List;

public record CreateInvoiceCommand(
  String customerName,
  String customerEmail,
  String customerAddress,
  String currency,
  List<CreateInvoiceItemDTO> items
) {
  public static Invoice createDomain(CreateInvoiceCommand command) {
    Invoice invoice = new Invoice();
    invoice.setCustomer(command.customerName());
    invoice.setCurrency(command.currency());
    double sum = command.items().stream()
      .mapToDouble(item -> item.quantity() * item.unitPrice())
      .sum();
    invoice.setTotal(BigDecimal.valueOf(sum));
    return invoice;
  }
}
