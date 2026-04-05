package org.bruno.product.domain;

import java.util.List;
import java.util.Optional;
import org.bruno.product.application.input.ProductSearchCriteriaInput;

public interface ProductRepository {
  List<Product> findAllProducts();

  Optional<Product> findByName(String name);

  void save(Product product);

  List<Product> findAllByCriteria(ProductSearchCriteriaInput input);
}
