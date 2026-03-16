package org.bruno.invoice.application.command.dto;

public record CreateInvoiceItemDTO (
  String description,
  int quantity,
  double unitPrice
){
}
