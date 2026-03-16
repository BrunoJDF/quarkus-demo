package org.bruno.invoice.domain;

import org.bruno.product.domain.Product;

public class InvoiceItem {
  private Product product;
  private int quantity;

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
