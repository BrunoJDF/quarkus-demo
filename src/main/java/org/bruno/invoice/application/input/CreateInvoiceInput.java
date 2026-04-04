package org.bruno.invoice.application.input;

import org.bruno.invoice.application.input.dto.CreateInvoiceItemDTO;

import java.util.List;

public record CreateInvoiceInput(
  String customerName,
  String currency,
  List<CreateInvoiceItemDTO> items
) {
}
