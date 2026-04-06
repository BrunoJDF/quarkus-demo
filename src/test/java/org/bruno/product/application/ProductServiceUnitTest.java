package org.bruno.product.application;

import org.bruno.product.ProductUnitTestCase;
import org.bruno.product.application.input.CreateProductInput;
import org.bruno.product.application.input.ProductSearchCriteriaInput;
import org.bruno.product.domain.ProductRepository;
import org.bruno.product.domain.port.ExchangeRatePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class ProductServiceUnitTest extends ProductUnitTestCase {

  private ProductService systemUnderTest;
  private ProductRepository productRepository;
  private ExchangeRatePort exchangeRatePort;

  @BeforeEach
  void setUp() {
    productRepository = mock(ProductRepository.class);
    exchangeRatePort = mock(ExchangeRatePort.class);
    systemUnderTest = new ProductService(productRepository, exchangeRatePort);
  }

  @Test
  void findAll() {
    var res = systemUnderTest.findAll();
    assertNotNull(res);
  }

  @Test
  void save() {
    CreateProductInput input = mock(CreateProductInput.class);
    Executable execution = () -> systemUnderTest.save(input);
    assertDoesNotThrow(execution);
  }

  @Test
  void findByName() {
    ProductSearchCriteriaInput input = new ProductSearchCriteriaInput("Test", 1L);
    String source = "USD";
    String target = "PEN";
    Executable execution = () -> systemUnderTest.findByCriteriaAndPriceConverted(input, source, target);
    assertDoesNotThrow(execution);
  }
}
