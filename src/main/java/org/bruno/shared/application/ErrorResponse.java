package org.bruno.shared.application;

import org.bruno.shared.domain.CategoryErrorEnum;

import java.time.ZonedDateTime;

public record ErrorResponse(
  CategoryErrorEnum category,
  String message,
  ZonedDateTime timestamp
) {
}
