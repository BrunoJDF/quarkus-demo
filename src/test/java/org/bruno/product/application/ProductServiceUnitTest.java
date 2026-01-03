package org.bruno.product.application;

import org.bruno.product.ProductUnitTestCase;
import org.bruno.product.application.command.CreateProductCommand;
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
    CreateProductCommand command = mock(CreateProductCommand.class);
    Executable execution = () -> systemUnderTest.save(command);
    assertDoesNotThrow(execution);
  }

  @Test
  void findByName() {
    String name = "SampleProduct";
    Executable execution = () -> systemUnderTest.findByName(name);
    assertThrows(Exception.class, execution);
  }
}
