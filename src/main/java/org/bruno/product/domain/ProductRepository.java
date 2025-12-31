package org.bruno.product.domain;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAllProducts();

    Optional<Product> findByName(String name);

    void save(Product product);
}
