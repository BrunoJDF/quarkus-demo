package org.bruno.product.domain.port;

import java.math.BigDecimal;

public interface ExchangeRatePort {
  BigDecimal getConversionRate(String currencySource, String currencyTarget);
}
