package org.bruno.invoice.infrastructure.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.bruno.invoice.domain.Invoice;
import org.bruno.product.domain.Product;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = InvoiceEntity.SQLInvoice.TABLE_NAME)
public class InvoiceEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String customer;
  private BigDecimal total;
  private String currency;
  private List<InvoiceItemEntity> details;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCustomer() {
    return customer;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public List<InvoiceItemEntity> getDetails() {
    return details;
  }

  public void setDetails(List<InvoiceItemEntity> details) {
    this.details = details;
  }

  public static InvoiceEntity fromDomain(Invoice invoice) {
    List<InvoiceItemEntity> details = invoice.getItems().stream()
      .map(item -> {
        InvoiceItemEntity entity = new InvoiceItemEntity();
        Product product = item.getProduct();
        entity.setProductId(product.getId());
        entity.setQuantity(item.getQuantity());
        return entity;
      })
      .toList();

    InvoiceEntity entity = new InvoiceEntity();
    entity.setCustomer(invoice.getCustomer());
    entity.setTotal(invoice.getTotal());
    entity.setCurrency(invoice.getCurrency());
    entity.setDetails(details);

    return entity;
  }

  public static class SQLInvoice {
    static final String TABLE_NAME = "invoice";

    private SQLInvoice() {
      throw new IllegalStateException("Utility class");
    }
  }
}
