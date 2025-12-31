package org.bruno.product.application;

import jakarta.enterprise.context.ApplicationScoped;
import org.bruno.product.application.command.CreateProductCommand;
import org.bruno.product.application.response.ProductResponse;
import org.bruno.product.domain.ProductRepository;

import java.util.List;

@ApplicationScoped
public class ProductService {
  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<ProductResponse> findAll() {
    return productRepository.findAllProducts().parallelStream()
      .map(ProductResponse::fromDomain)
      .toList();
  }

  public void save(CreateProductCommand product) {
    productRepository.save(product.toDomain());
  }

  public ProductResponse findByName(String name) {
    return productRepository.findByName(name)
      .map(ProductResponse::fromDomain)
      .orElseThrow();
  }
}
