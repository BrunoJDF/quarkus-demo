package org.bruno.invoice.application;

import jakarta.enterprise.context.ApplicationScoped;
import org.bruno.invoice.application.command.CreateInvoiceCommand;

@ApplicationScoped
public class InvoiceService {

  public void create(CreateInvoiceCommand command) {
    throw new UnsupportedOperationException("Unimplemented method 'create'");
  }

}
