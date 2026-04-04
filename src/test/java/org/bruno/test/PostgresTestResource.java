package org.bruno.test;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.HashMap;
import java.util.Map;

public class PostgresTestResource implements QuarkusTestResourceLifecycleManager {
  private PostgreSQLContainer<?> postgres;

  @Override
  public Map<String, String> start() {
    postgres = new PostgreSQLContainer<>("postgres:15-alpine")
      .withDatabaseName("testdb")
      .withUsername("test")
      .withPassword("test");
    postgres.start();

    String jdbcUrl = postgres.getJdbcUrl();
    String host = postgres.getHost();
    Integer port = postgres.getFirstMappedPort();

    Map<String, String> config = new HashMap<>();
    // JDBC (blocking Hibernate)
    config.put("quarkus.datasource.jdbc.url", jdbcUrl);
    config.put("quarkus.datasource.username", postgres.getUsername());
    config.put("quarkus.datasource.password", postgres.getPassword());
    config.put("quarkus.datasource.db-kind", "postgresql");

    // Reactive datasource (if used anywhere)
    String reactiveUrl = String.format("postgresql://%s:%d/%s", host, port, postgres.getDatabaseName());
    config.put("quarkus.datasource.reactive.url", reactiveUrl);

    // Ensure Liquibase runs against the container DB during tests
    config.put("quarkus.liquibase.migrate-at-start", "true");
    // Explicit change-log location (adapt if your changelog is in another path)
    config.put("quarkus.liquibase.change-log", "classpath:db/changeLog.xml");

    return config;
  }

  @Override
  public void stop() {
    if (postgres != null) {
      postgres.stop();
    }
  }
}


