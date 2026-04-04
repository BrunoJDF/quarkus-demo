package org.bruno.invoice.application.input.dto;

public record CreateInvoiceItemDTO (
  String description,
  int quantity
){
}
