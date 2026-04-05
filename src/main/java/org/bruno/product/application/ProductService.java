package org.bruno.product.application;

import jakarta.enterprise.context.ApplicationScoped;
import org.bruno.product.application.input.CreateProductInput;
import org.bruno.product.application.input.ProductSearchCriteriaInput;
import org.bruno.product.application.response.ProductResponse;
import org.bruno.product.domain.ProductRepository;
import org.bruno.product.domain.port.ExchangeRatePort;
import java.math.BigDecimal;
import java.util.List;

@ApplicationScoped
public class ProductService {
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

  public void save(CreateProductInput product) {
    productRepository.save(product.toDomain());
  }

  public List<ProductResponse> findByCriteriaAndPriceConverted(ProductSearchCriteriaInput input, String source,
      String target) {
    List<ProductResponse> productResponse = productRepository.findAllByCriteria(input)
        .parallelStream()
        .map(product -> {
          BigDecimal conversionRate = exchangeRatePort.getConversionRate(source, target);
          BigDecimal priceConverted = product.getPrice().multiply(conversionRate);
          product.setPriceConverted(priceConverted);
          return product;
        })
        .map(ProductResponse::fromDomain)
        .toList();

    return productResponse;
  }
}
