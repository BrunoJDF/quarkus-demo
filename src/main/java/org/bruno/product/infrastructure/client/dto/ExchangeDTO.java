package org.bruno.product.infrastructure.client.dto;

import java.math.BigDecimal;
import java.util.Map;

public record ExchangeDTO(
  String result,
  String documentation,
  String terms_of_use,
  Long time_last_update_unix,
  String time_last_update_utc,
  Long time_next_update_unix,
  String time_next_update_utc,
  String base_code,
  String target_code,
  Map<String, BigDecimal> conversion_rates,
  Double conversion_result
) {

}
