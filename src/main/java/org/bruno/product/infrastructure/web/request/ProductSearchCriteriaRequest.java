package org.bruno.product.infrastructure.web.request;

import org.bruno.product.application.input.ProductSearchCriteriaInput;

public record ProductSearchCriteriaRequest(
  String name,
  Long id
) {

  public static ProductSearchCriteriaInput create(ProductSearchCriteriaRequest criteria) {
    return new ProductSearchCriteriaInput(criteria.name, criteria.id);
  }
}
