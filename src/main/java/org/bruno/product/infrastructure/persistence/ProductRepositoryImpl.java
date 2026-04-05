package org.bruno.product.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import org.bruno.product.application.input.ProductSearchCriteriaInput;
import org.bruno.product.domain.Product;
import org.bruno.product.domain.ProductRepository;
import org.bruno.product.infrastructure.persistence.model.ProductEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class ProductRepositoryImpl implements ProductRepository, PanacheRepository<ProductEntity> {

  @Override
  public List<Product> findAllProducts() {
    return findAll().stream()
        .map(ProductEntity::toDomain)
        .toList();
  }

  @Override
  public Optional<Product> findByName(String name) {
    return find(ProductEntity.SQLProduct.NAME, name)
        .firstResultOptional()
        .map(ProductEntity::toDomain);
  }

  @Override
  public void save(Product product) {
    ProductEntity entity = ProductEntity.fromDomain(product);
    persist(entity);
  }

  @Override
  public List<Product> findAllByCriteria(ProductSearchCriteriaInput input) {
    Map<String, Object> params = new HashMap<>();
    List<String> conditions = new ArrayList<>();
    Optional.ofNullable(input.name()).ifPresent(name -> {
      conditions.add(ProductEntity.SQLProduct.NAME + " = :name");
      params.put("name", name);
    });
    Optional.ofNullable(input.id()).ifPresent(id -> {
      conditions.add(ProductEntity.SQLProduct.ID + " = :id");
      params.put("id", id);
    });
    String whereClause = conditions.isEmpty() ? "" : " where " + String.join(" and ", conditions);

    return find(whereClause, params)
        .stream()
        .map(ProductEntity::toDomain)
        .toList();
  }
}
