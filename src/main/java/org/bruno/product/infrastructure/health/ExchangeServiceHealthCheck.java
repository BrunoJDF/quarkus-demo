package org.bruno.product.infrastructure.health;

import jakarta.enterprise.context.ApplicationScoped;
import org.bruno.product.domain.port.ExchangeRatePort;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.jboss.logging.Logger;

import java.math.BigDecimal;

@Readiness
@ApplicationScoped
public class ExchangeServiceHealthCheck implements HealthCheck {
  private static final Logger LOG = Logger.getLogger(ExchangeServiceHealthCheck.class);
  private final ExchangeRatePort exchangeRatePort;

  public ExchangeServiceHealthCheck(ExchangeRatePort exchangeRatePort) {
    this.exchangeRatePort = exchangeRatePort;
  }

  @Override
  public HealthCheckResponse call() {
    try {
      BigDecimal rate = exchangeRatePort.getConversionRate("USD", "EUR");
      if (rate != null && rate.compareTo(BigDecimal.ZERO) > 0) {
        return HealthCheckResponse.named("Exchange Service Health Check")
          .up()
          .withData("status", "Exchange service is reachable")
          .withData("test_rate", rate.toString())
          .build();
      } else {
        return HealthCheckResponse.named("Exchange Service Down Health Check")
          .down()
          .withData("reason", "Invalid exchange rate received")
          .build();
      }
    } catch (Exception e) {
      LOG.error("Exchange service is not available", e);
      return HealthCheckResponse.named("Exchange Service Down Health Check")
        .down()
        .withData("error", e.getMessage())
        .withData("reason", "Exchange service is not reachable")
        .build();
    }
  }
}
