package org.bruno.invoice.application.command;

import org.bruno.invoice.application.command.dto.CreateInvoiceItemDTO;

import java.util.List;

public record CreateInvoiceCommand(
  String customerName,
  String currency,
  List<CreateInvoiceItemDTO> items
) {
}
