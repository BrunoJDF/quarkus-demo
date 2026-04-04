package org.bruno.product.infrastructure.web.request;

import org.bruno.product.application.input.CreateProductInput;

import java.math.BigDecimal;

public record CreateProductRequest(
  String name,
  BigDecimal price
) {
  public static CreateProductInput create(CreateProductRequest product) {
    return new CreateProductInput(
      product.name(),
      product.price()
    );
  }
}
