package org.bruno.product.infrastructure.adapter;

import io.quarkus.cache.CacheKey;
import io.quarkus.cache.CacheResult;
import jakarta.enterprise.context.ApplicationScoped;
import org.bruno.product.domain.port.ExchangeRatePort;
import org.bruno.product.infrastructure.client.ExchangeClient;
import org.bruno.product.infrastructure.client.dto.ExchangeDTO;
import org.bruno.shared.domain.exception.QSNotFoundException;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class ExchangeRateAdapter implements ExchangeRatePort {

  private static final Logger LOG = Logger.getLogger(ExchangeRateAdapter.class);
  private final ExchangeClient exchangeClient;
  private final String apiKey;

  public ExchangeRateAdapter(
    @RestClient ExchangeClient exchangeClient,
    @ConfigProperty(name = "quarkus.rest-client.ex-change-client.api-key") String apiKey
  ) {
    this.exchangeClient = exchangeClient;
    this.apiKey = apiKey;
  }

  @Override
  @Timeout(2000)
  @Retry(maxRetries = 1, delay = 1000)
  @CacheResult(cacheName = "exchange-rates")
  public BigDecimal getConversionRate(@CacheKey String currencySource, @CacheKey String currencyTarget) {
    ExchangeDTO exchangeDTO = exchangeClient.getExChangeDTO(apiKey, currencySource);
    Map<String, BigDecimal> rates = getRates(exchangeDTO);
    BigDecimal rate = getRateFromCurrency(rates, currencyTarget);

    LOG.info("Rate for currency: " + currencyTarget + " is " + rate);
    return rate;
  }

  private BigDecimal getRateFromCurrency(Map<String, BigDecimal> rates, String currencyTarget) {
    return Optional.of(rates)
      .map(map -> map.get(currencyTarget))
      .orElseThrow(() -> new QSNotFoundException("Rate not found for currency: " + currencyTarget));
  }

  private Map<String, BigDecimal> getRates(ExchangeDTO exchangeDTO) {
    return Optional.ofNullable(exchangeDTO)
      .map(ExchangeDTO::conversionRates)
      .orElseThrow(() -> {
        Map<String, BigDecimal> conversionRate = Optional.ofNullable(exchangeDTO)
          .map(ExchangeDTO::conversionRates)
          .orElse(Map.of());
        return new QSNotFoundException("Conversion rates not found for currency: " + conversionRate);
      });
  }
}
