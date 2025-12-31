package org.bruno.product.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bruno.product.domain.Product;
import org.bruno.product.domain.ProductRepository;
import org.bruno.product.infrastructure.persistence.model.ProductEntity;

import java.util.List;
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
}
