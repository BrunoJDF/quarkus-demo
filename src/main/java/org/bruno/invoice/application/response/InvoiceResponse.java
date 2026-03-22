package org.bruno.invoice.application.response;

import org.bruno.invoice.application.response.dto.ClientDTO;
import org.bruno.invoice.application.response.dto.InvoiceItemDTO;
import org.bruno.invoice.domain.Invoice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public record InvoiceResponse(
  String codInvoice,
  ClientDTO client,
  BigDecimal subTotalPrice,
  BigDecimal igv,
  BigDecimal totalPrice,
  List<InvoiceItemDTO> items,
  String currency
) {
  public static InvoiceResponse fromDomain(Invoice invoice) {
    List<InvoiceItemDTO> items = invoice.getItems().stream()
      .map(InvoiceItemDTO::fromDomain)
      .toList();

    ClientDTO client = new ClientDTO(
      invoice.getClient().getName(),
      invoice.getClient().getEmail(),
      invoice.getClient().getPhone()
    );

    return new InvoiceResponse(
      invoice.getCodInvoice(),
      client,
      invoice.getSubTotalPrice(),
      invoice.getIgv(),
      invoice.getTotalPrice(),
      items,
      invoice.getCurrency()
    );
  }

  public BigDecimal getSubTotalPrice() {
    return subTotalPrice.setScale(2, RoundingMode.HALF_UP);
  }

  public BigDecimal getIgv() {
    return igv.setScale(2, RoundingMode.HALF_UP);
  }

  public BigDecimal getTotalPrice() {
    return totalPrice.setScale(2, RoundingMode.HALF_UP);
  }
}
