package org.bruno.product.application;

import jakarta.enterprise.context.ApplicationScoped;
import org.bruno.product.application.command.CreateProductCommand;
import org.bruno.product.application.response.ProductResponse;
import org.bruno.product.domain.ProductRepository;
import org.bruno.product.domain.port.ExchangeRatePort;
import org.bruno.shared.domain.exception.QSNotFoundException;
import org.jboss.logging.Logger;

import java.math.BigDecimal;
import java.util.List;

@ApplicationScoped
public class ProductService {
  private static final Logger LOG = Logger.getLogger(ProductService.class);
  private final ProductRepository productRepository;
  private final ExchangeRatePort exchangeRatePort;

  public ProductService(ProductRepository productRepository, ExchangeRatePort exchangeRatePort) {
    this.productRepository = productRepository;
    this.exchangeRatePort = exchangeRatePort;
  }

  public List<ProductResponse> findAll() {
    return productRepository.findAllProducts().parallelStream()
      .map(ProductResponse::fromDomain)
      .toList();
  }

  public void save(CreateProductCommand product) {
    productRepository.save(product.toDomain());
  }

  public ProductResponse findByName(String name, String source, String target) {
    ProductResponse productResponse = productRepository.findByName(name)
      .map(product -> {
        BigDecimal conversionRate = exchangeRatePort.getConversionRate(source, target);
        BigDecimal priceConverted = product.getPrice().multiply(conversionRate);
        product.setPriceConverted(priceConverted);
        return product;
      })
      .map(ProductResponse::fromDomain)
      .orElseThrow(() -> new QSNotFoundException("Product not found with name: " + name));

    LOG.info("Product found: " + productResponse);

    return productResponse;
  }
}
