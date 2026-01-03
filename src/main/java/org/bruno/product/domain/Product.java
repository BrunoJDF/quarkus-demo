package org.bruno.product.domain;

import java.math.BigDecimal;

public class Product {
  private Long id;
  private String name;
  private BigDecimal price;
  private BigDecimal priceConverted;

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

  public BigDecimal getPriceConverted() {
    return priceConverted;
  }

  public void setPriceConverted(BigDecimal priceConverted) {
    this.priceConverted = priceConverted;
  }
}
