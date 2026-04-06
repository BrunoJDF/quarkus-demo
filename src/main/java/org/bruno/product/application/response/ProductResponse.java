package org.bruno.product.application.response;

import org.bruno.product.domain.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public record ProductResponse(
  Long id,
  String name,
  BigDecimal price,
  BigDecimal priceConverted
) {
  public static ProductResponse fromDomain(Product product) {
    return new ProductResponse(
      product.getId(),
      product.getName(),
      product.getPrice(),
      product.getPriceConverted()
    );
  }

  @SuppressWarnings("unused")
  public BigDecimal getPriceConverted() {
    return Optional.ofNullable(priceConverted)
      .map(price -> price.setScale(2, RoundingMode.UP))
      .orElse(BigDecimal.ZERO);
  }
}
