package org.bruno.product.application.input;

import org.bruno.product.domain.Product;

import java.math.BigDecimal;

public record CreateProductInput(
  String name,
  BigDecimal price
) {
  public Product toDomain() {
    Product request = new Product();
    request.setName(name);
    request.setPrice(price);
    return request;
  }
}
