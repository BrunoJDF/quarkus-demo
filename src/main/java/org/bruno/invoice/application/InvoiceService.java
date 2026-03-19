package org.bruno.invoice.application;

import jakarta.enterprise.context.ApplicationScoped;
import org.bruno.client.domain.Client;
import org.bruno.client.domain.ClientRepository;
import org.bruno.invoice.application.command.CreateInvoiceCommand;
import org.bruno.invoice.application.command.dto.CreateInvoiceItemDTO;
import org.bruno.invoice.domain.Invoice;
import org.bruno.invoice.domain.InvoiceItem;
import org.bruno.invoice.domain.InvoiceRepository;
import org.bruno.product.domain.Product;
import org.bruno.product.domain.ProductRepository;
import org.bruno.shared.domain.exception.QSNotFoundException;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@ApplicationScoped
public class InvoiceService {

  private static final double IGV_ON_PE = 0.18;
  private final InvoiceRepository invoiceRepository;
  private final ProductRepository productRepository;
  private final ClientRepository clientRepository;

  public InvoiceService(InvoiceRepository invoiceRepository, ProductRepository productRepository, ClientRepository clientRepository) {
    this.invoiceRepository = invoiceRepository;
    this.productRepository = productRepository;
    this.clientRepository = clientRepository;
  }

  public void create(CreateInvoiceCommand command) {
    List<InvoiceItem> items = createInvoiceItem(command.items());
    Invoice invoice = createInvoice(command, items);
    invoice.setItems(items);

    invoiceRepository.save(invoice);
  }

  private Invoice createInvoice(CreateInvoiceCommand command, List<InvoiceItem> items) {
    Client client = clientRepository.findByName(command.customerName())
      .orElseThrow(() -> new QSNotFoundException("Client not found with name: " + command.customerName()));
    BigDecimal subTotalPrice = items.stream()
      .map(invoiceItem -> {
        var price = invoiceItem.getProduct().getPrice();
        var quantity = invoiceItem.getQuantity();
        return price.multiply(BigDecimal.valueOf(quantity));
      })
      .reduce(BigDecimal.ZERO, BigDecimal::add);
    BigDecimal igv = subTotalPrice.multiply(BigDecimal.valueOf(IGV_ON_PE));
    BigDecimal totalPrice = subTotalPrice.add(igv);

    Invoice invoice = new Invoice();
    invoice.setCodInvoice("INV-" + System.currentTimeMillis());
    invoice.setSubTotalPrice(subTotalPrice);
    invoice.setIgv(igv);
    invoice.setTotalPrice(totalPrice);
    invoice.setStatus("CREATED");
    invoice.setEmissionDate(OffsetDateTime.now());
    invoice.setExpirationDate(null);
    invoice.setCreationDate(OffsetDateTime.now());
    invoice.setModificationDate(null);
    invoice.setCurrency(command.currency());

    invoice.setClient(client);
    return invoice;
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
