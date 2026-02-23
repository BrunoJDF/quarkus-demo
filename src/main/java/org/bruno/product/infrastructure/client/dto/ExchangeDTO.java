package org.bruno.product.infrastructure.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Map;

public record ExchangeDTO(
  String result,
  String documentation,
  @JsonProperty("terms_of_use")
  String termsOfUse,
  @JsonProperty("time_last_update_unix")
  Long timeLastUpdateUnix,
  @JsonProperty("time_last_update_utc")
  String timeLastUpdateUtc,
  @JsonProperty("time_next_update_unix")
  Long timeNextUpdateUnix,
  @JsonProperty("time_next_update_utc")
  String timeNextUpdateUtc,
  @JsonProperty("base_code")
  String baseCode,
  @JsonProperty("target_code")
  String targetCode,
  @JsonProperty("conversion_rates")
  Map<String, BigDecimal> conversionRates,
  @JsonProperty("conversion_result")
  Double conversionResult
) {

}
