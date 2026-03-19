package org.bruno.invoice.infrastructure.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.bruno.invoice.domain.InvoiceItem;
import org.bruno.product.infrastructure.persistence.model.ProductEntity;

@Entity
@Table(name = InvoiceItemEntity.SQLInvoiceItem.TABLE_NAME)
public class InvoiceItemEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_product", nullable = false)
  private ProductEntity product;
  private int quantity;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_invoice", nullable = false)
  private InvoiceEntity invoice;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public InvoiceEntity getInvoice() {
    return invoice;
  }

  public void setInvoice(InvoiceEntity invoice) {
    this.invoice = invoice;
  }

  public ProductEntity getProduct() {
    return product;
  }

  public void setProduct(ProductEntity product) {
    this.product = product;
  }

  public InvoiceItem toDomain() {
    InvoiceItem invoiceItem = new InvoiceItem();
    invoiceItem.setProduct(product.toDomain());
    invoiceItem.setQuantity(quantity);
    return invoiceItem;
  }

  public static class SQLInvoiceItem {
    static final String TABLE_NAME = "invoice_item";

    private SQLInvoiceItem() {
      throw new IllegalStateException("Utility class");
    }
  }
}
