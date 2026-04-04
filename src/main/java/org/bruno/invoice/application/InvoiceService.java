package org.bruno.invoice.application;

import jakarta.enterprise.context.ApplicationScoped;
import org.bruno.client.domain.Client;
import org.bruno.client.domain.ClientRepository;
import org.bruno.invoice.application.input.CreateInvoiceInput;
import org.bruno.invoice.application.input.dto.CreateInvoiceItemDTO;
import org.bruno.invoice.application.response.InvoiceResponse;
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
  private final ClientRepository clientRepository;

  public InvoiceService(InvoiceRepository invoiceRepository, ProductRepository productRepository, ClientRepository clientRepository) {
    this.invoiceRepository = invoiceRepository;
    this.productRepository = productRepository;
    this.clientRepository = clientRepository;
  }

  public void create(CreateInvoiceInput input) {
    List<InvoiceItem> items = createInvoiceItem(input.items());
    Invoice invoice = createInvoice(input, items);
    invoice.setItems(items);

    invoiceRepository.save(invoice);
  }

  private Invoice createInvoice(CreateInvoiceInput input, List<InvoiceItem> items) {
    Client client = clientRepository.findByName(input.customerName())
      .orElseThrow(() -> new QSNotFoundException("Client not found with name: " + input.customerName()));
    return InvoiceFactory.createInvoice(client, input.currency(), items);
  }

  private List<InvoiceItem> createInvoiceItem(List<CreateInvoiceItemDTO> items) {
    return items.stream()
      .map(invoiceItemDTO -> {
        Product product = productRepository.findByName(invoiceItemDTO.description())
          .orElseThrow(() -> new QSNotFoundException("Product not found with name: " + invoiceItemDTO.description()));
        return InvoiceFactory.createInvoiceItem(product, invoiceItemDTO.quantity());
      })
      .toList();
  }

  public List<InvoiceResponse> findAll() {
    return invoiceRepository.getAll().stream()
      .map(InvoiceResponse::fromDomain)
      .toList();
  }

  public InvoiceResponse findById(Long id) {
    return invoiceRepository.getById(id)
      .map(InvoiceResponse::fromDomain)
      .orElseThrow(() -> new QSNotFoundException("Invoice not found with id: " + id));
  }
}
