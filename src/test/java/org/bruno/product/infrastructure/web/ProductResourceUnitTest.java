package org.bruno.product.infrastructure.web;

import org.bruno.product.ProductUnitTestCase;
import org.bruno.product.application.ProductService;
import org.bruno.product.application.input.ProductSearchCriteriaInput;
import org.bruno.product.application.response.ProductResponse;
import org.bruno.product.infrastructure.web.request.CreateProductRequest;
import org.bruno.product.infrastructure.web.request.ProductSearchCriteriaRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductResourceUnitTest extends ProductUnitTestCase {

  private ProductResource systemUnderTest;
  private ProductService productService;

  @BeforeEach
  void setUp() {
    productService = mock(ProductService.class);
    systemUnderTest = new ProductResource(productService);
  }

  @Test
  void getProducts() {
    when(productService.findAll())
      .thenReturn(List.of());
    var res = systemUnderTest.getProducts();
    assertNotNull(res);
  }

  @Test
  void getProductByName() {
    ProductSearchCriteriaRequest request = new ProductSearchCriteriaRequest("Test", 1L);
    ProductSearchCriteriaInput input = ProductSearchCriteriaRequest.create(request);
    String source = "USD";
    String target = "PEN";
    ProductResponse productResponse = mock(ProductResponse.class);
    when(productService.findByCriteriaAndPriceConverted(input, source, target))
      .thenReturn(List.of(productResponse));
    var res = systemUnderTest.getProductByName(request, source, target);
    assertNotNull(res);
  }

  @Test
  void createProduct() {
    CreateProductRequest request = mock(CreateProductRequest.class);
    var res = systemUnderTest.createProduct(request);
    assertNotNull(res);
  }
}
