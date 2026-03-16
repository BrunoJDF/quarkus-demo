package org.bruno.invoice.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bruno.invoice.domain.Invoice;
import org.bruno.invoice.domain.InvoiceRepository;
import org.bruno.invoice.infrastructure.persistence.model.InvoiceEntity;

@ApplicationScoped
public class InvoiceRepositoryImpl implements InvoiceRepository, PanacheRepository<InvoiceEntity> {

  @Override
  public void save(Invoice invoice) {
    InvoiceEntity entity = InvoiceEntity.fromDomain(invoice);
    persist(entity);
  }
}
