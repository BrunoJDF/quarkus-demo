package org.bruno.product.infrastructure.web.request;

import org.bruno.product.domain.Product;

import java.math.BigDecimal;

public record CreateProductRequest(
  String name,
  BigDecimal price
) {
  public static Product create(CreateProductRequest product) {
    Product newProduct = new Product();
    newProduct.setName(product.name());
    newProduct.setPrice(product.price());
    return newProduct;
  }
}
