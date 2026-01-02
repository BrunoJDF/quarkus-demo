package org.bruno.product.infrastructure.persistence;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.bruno.product.domain.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class ProductRepositoryImplIT {

  @Inject
  ProductRepositoryImpl systemUnderTest;

  @Test
  void findAllProducts() {
    var products = systemUnderTest.findAllProducts();
    assertNotNull(products);
  }

  @Test
  void findByName() {
    String name = "SampleProduct";
    var productOpt = systemUnderTest.findByName(name);
    assertNotNull(productOpt);
  }

  @Test
  @Transactional
  void save() {
    Product product = new Product();
    product.setName("SampleProduct");
    product.setPrice(new BigDecimal("19.99"));
    assertDoesNotThrow(() -> systemUnderTest.save(product));
  }
}
