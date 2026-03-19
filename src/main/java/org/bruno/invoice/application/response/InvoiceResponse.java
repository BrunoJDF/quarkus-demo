package org.bruno.invoice.application.response;

import org.bruno.invoice.application.response.dto.ClientDTO;
import org.bruno.invoice.application.response.dto.InvoiceItemDTO;
import org.bruno.invoice.domain.Invoice;

import java.math.BigDecimal;
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
}
