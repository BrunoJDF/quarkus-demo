package org.bruno.product.application.command;

import org.bruno.product.domain.Product;

import java.math.BigDecimal;

public record CreateProductCommand(
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
