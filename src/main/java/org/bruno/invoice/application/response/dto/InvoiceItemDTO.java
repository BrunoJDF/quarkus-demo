package org.bruno.invoice.application.response.dto;

import org.bruno.invoice.domain.InvoiceItem;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

  public BigDecimal getPrice() {
    return price.setScale(2, RoundingMode.HALF_UP);
  }
}
