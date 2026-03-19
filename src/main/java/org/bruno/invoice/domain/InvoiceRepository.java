package org.bruno.invoice.domain;

import java.util.List;

public interface InvoiceRepository {
  void save(Invoice invoice);

  List<Invoice> findAllInvoice();
}
