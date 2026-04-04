package org.bruno.invoice.application;

import org.bruno.client.domain.Client;
import org.bruno.invoice.domain.Invoice;
import org.bruno.invoice.domain.InvoiceItem;
import org.bruno.invoice.domain.InvoiceStatusEnum;
import org.bruno.product.domain.Product;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public class InvoiceFactory {
  private static final double IGV_ON_PE = 0.18;

  private InvoiceFactory() {
    throw new IllegalStateException("Utility class");
  }

  public static String generateCode() {
    String prefix = "INV";
    String timestamp = String.valueOf(System.currentTimeMillis());
    return String.format("%s-%s", prefix, timestamp);
  }

  public static Invoice createInvoice(Client client, String currency, List<InvoiceItem> items) {
    BigDecimal subTotalPrice = items.stream()
      .map(invoiceItem -> {
        BigDecimal price = invoiceItem.getProduct().getPrice();
        int quantity = invoiceItem.getQuantity();
        return price.multiply(BigDecimal.valueOf(quantity));
      })
      .reduce(BigDecimal.ZERO, BigDecimal::add);
    BigDecimal igv = subTotalPrice.multiply(BigDecimal.valueOf(IGV_ON_PE));
    BigDecimal totalPrice = subTotalPrice.add(igv);

    Invoice invoice = new Invoice();
    invoice.setCodInvoice(InvoiceFactory.generateCode());
    invoice.setSubTotalPrice(subTotalPrice);
    invoice.setIgv(igv);
    invoice.setTotalPrice(totalPrice);
    invoice.setStatus(InvoiceStatusEnum.CREATED);
    invoice.setEmissionDate(OffsetDateTime.now());
    invoice.setExpirationDate(null);
    invoice.setCreationDate(OffsetDateTime.now());
    invoice.setModificationDate(null);
    invoice.setCurrency(currency);

    invoice.setClient(client);
    return invoice;
  }

  public static InvoiceItem createInvoiceItem(Product product, int quantity) {
    InvoiceItem createInvoiceItem = new InvoiceItem();
    createInvoiceItem.setProduct(product);
    createInvoiceItem.setQuantity(quantity);
    return createInvoiceItem;
  }
}
