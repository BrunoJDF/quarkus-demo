package org.bruno.invoice.application;

import jakarta.enterprise.context.ApplicationScoped;
import org.bruno.invoice.application.command.CreateInvoiceCommand;
import org.bruno.invoice.application.command.dto.CreateInvoiceItemDTO;
import org.bruno.invoice.domain.Invoice;
import org.bruno.invoice.domain.InvoiceItem;
import org.bruno.invoice.domain.InvoiceRepository;
import org.bruno.product.domain.Product;
import org.bruno.product.domain.ProductRepository;
import org.bruno.shared.domain.exception.QSNotFoundException;

import java.util.List;

@ApplicationScoped
public class InvoiceService {

  private final InvoiceRepository invoiceRepository;
  private final ProductRepository productRepository;

  public InvoiceService(InvoiceRepository invoiceRepository, ProductRepository productRepository) {
    this.invoiceRepository = invoiceRepository;
    this.productRepository = productRepository;
  }

  public void create(CreateInvoiceCommand command) {
    Invoice invoice = CreateInvoiceCommand.createDomain(command);
    List<InvoiceItem> items = createInvoiceItem(command.items());
    invoice.setItems(items);

    invoiceRepository.save(invoice);
  }

  private List<InvoiceItem> createInvoiceItem(List<CreateInvoiceItemDTO> items) {
    return items.stream()
      .map(invoiceItemDTO -> {
        Product product = productRepository.findByName(invoiceItemDTO.description())
          .orElseThrow(() -> new QSNotFoundException("Product not found with name: " + invoiceItemDTO.description()));
        InvoiceItem createInvoiceItem = new InvoiceItem();
        createInvoiceItem.setProduct(product);
        createInvoiceItem.setQuantity(invoiceItemDTO.quantity());
        return createInvoiceItem;
      })
      .toList();
  }

}
