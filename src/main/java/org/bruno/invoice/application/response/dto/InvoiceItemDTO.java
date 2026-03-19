package org.bruno.invoice.application.response.dto;

import org.bruno.invoice.domain.InvoiceItem;

import java.math.BigDecimal;

public record InvoiceItemDTO(
  String productName,
  int quantity,
  BigDecimal price
) {
  public static InvoiceItemDTO fromDomain(InvoiceItem invoiceItem) {
    return new InvoiceItemDTO(
      invoiceItem.getProduct().getName(),
      invoiceItem.getQuantity(),
      invoiceItem.getProduct().getPrice()
    );
  }
}
