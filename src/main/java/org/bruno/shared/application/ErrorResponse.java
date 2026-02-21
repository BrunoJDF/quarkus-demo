package org.bruno.shared.application;

import org.bruno.shared.domain.CategoryErrorEnum;

public record ErrorResponse(
  CategoryErrorEnum category,
  String message
) {

}
