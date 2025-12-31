package org.bruno.product.application.response;

import org.bruno.product.domain.Product;

import java.math.BigDecimal;

public record ProductResponse(
  Long id,
  String name,
  BigDecimal price
) {
  public static ProductResponse fromDomain(Product product) {
    return new ProductResponse(
      product.getId(),
      product.getName(),
      product.getPrice()
    );
  }
}
