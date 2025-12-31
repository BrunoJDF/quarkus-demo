package org.bruno.product.application;

import jakarta.enterprise.context.ApplicationScoped;
import org.bruno.product.domain.Product;
import org.bruno.product.domain.ProductRepository;

import java.util.List;

@ApplicationScoped
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAllProducts();
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public Product findByName(String name) {
        return productRepository.findByName(name)
                .orElseThrow();
    }
}
