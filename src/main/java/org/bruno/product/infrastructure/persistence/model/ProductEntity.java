package org.bruno.product.infrastructure.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.bruno.product.domain.Product;

import java.math.BigDecimal;

@Entity
@Table(name = ProductEntity.SQLProduct.TABLE_NAME)
public class ProductEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = SQLProduct.NAME)
  private String name;
  @Column(name = SQLProduct.PRICE)
  private BigDecimal price;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public static class SQLProduct {
    static final String TABLE_NAME = "product";

    private SQLProduct() {
      throw new IllegalStateException("Utility class");
    }

    public static final String NAME = "name";
    public static final String PRICE = "price";
  }

  public Product toDomain() {
    Product product = new Product();
    product.setId(this.id);
    product.setName(this.name);
    product.setPrice(this.price);
    return product;
  }

  public static ProductEntity fromDomain(Product product) {
    ProductEntity entity = new ProductEntity();
    entity.setId(product.getId());
    entity.setName(product.getName());
    entity.setPrice(product.getPrice());
    return entity;
  }
}
