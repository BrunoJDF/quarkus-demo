package org.bruno.product.infrastructure.web.request;

import org.bruno.product.application.command.CreateProductCommand;

import java.math.BigDecimal;

public record CreateProductRequest(
  String name,
  BigDecimal price
) {
  public static CreateProductCommand create(CreateProductRequest product) {
    return new CreateProductCommand(
      product.name(),
      product.price()
    );
  }
}
