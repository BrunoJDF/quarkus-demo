package org.bruno.invoice.domain;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository {
  void save(Invoice invoice);

  List<Invoice> getAll();

  Optional<Invoice> getById(Long id);
}
