package org.bruno.invoice.infrastructure.persistence.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.bruno.client.infrastructure.persistence.model.ClientEntity;
import org.bruno.invoice.domain.Invoice;
import org.bruno.product.infrastructure.persistence.model.ProductEntity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = InvoiceEntity.SQLInvoice.TABLE_NAME)
public class InvoiceEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = SQLInvoice.COD_INVOICE)
  private String codInvoice;
  @Column(name = SQLInvoice.SUB_TOTAL_PRICE)
  private BigDecimal subTotalPrice;
  @Column(name = SQLInvoice.IGV)
  private BigDecimal igv;
  @Column(name = SQLInvoice.TOTAL_PRICE)
  private BigDecimal totalPrice;
  @Column(name = SQLInvoice.EMISSION_DATE)
  private OffsetDateTime emissionDate;
  @Column(name = SQLInvoice.EXPIRATION_DATE)
  private OffsetDateTime expirationDate;
  @Column(name = SQLInvoice.CREATION_DATE)
  private OffsetDateTime creationDate;
  @Column(name = SQLInvoice.MODIFICATION_DATE)
  private OffsetDateTime modificationDate;
  @Column(name = SQLInvoice.STATUS)
  private String status;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_client", nullable = false)
  private ClientEntity client;
  @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<InvoiceItemEntity> items;
  @Column(name = SQLInvoice.CURRENCY)
  private String currency;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCodInvoice() {
    return codInvoice;
  }

  public void setCodInvoice(String codInvoice) {
    this.codInvoice = codInvoice;
  }

  public BigDecimal getSubTotalPrice() {
    return subTotalPrice;
  }

  public void setSubTotalPrice(BigDecimal subTotalPrice) {
    this.subTotalPrice = subTotalPrice;
  }

  public BigDecimal getIgv() {
    return igv;
  }

  public void setIgv(BigDecimal igv) {
    this.igv = igv;
  }

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }

  public OffsetDateTime getEmissionDate() {
    return emissionDate;
  }

  public void setEmissionDate(OffsetDateTime emissionDate) {
    this.emissionDate = emissionDate;
  }

  public OffsetDateTime getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(OffsetDateTime expirationDate) {
    this.expirationDate = expirationDate;
  }

  public OffsetDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public OffsetDateTime getModificationDate() {
    return modificationDate;
  }

  public void setModificationDate(OffsetDateTime modificationDate) {
    this.modificationDate = modificationDate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public List<InvoiceItemEntity> getItems() {
    return items;
  }

  public void setItems(List<InvoiceItemEntity> items) {
    this.items = items;
  }

  public ClientEntity getClient() {
    return client;
  }

  public void setClient(ClientEntity client) {
    this.client = client;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public static InvoiceEntity fromDomain(Invoice invoice) {
    InvoiceEntity entity = new InvoiceEntity();
    entity.setCodInvoice(invoice.getCodInvoice());
    entity.setSubTotalPrice(invoice.getSubTotalPrice());
    entity.setIgv(invoice.getIgv());
    entity.setTotalPrice(invoice.getTotalPrice());
    entity.setEmissionDate(invoice.getEmissionDate());
    entity.setExpirationDate(invoice.getExpirationDate());
    entity.setCreationDate(invoice.getCreationDate());
    entity.setModificationDate(invoice.getModificationDate());
    entity.setStatus(invoice.getStatus());
    ClientEntity client = ClientEntity.fromDomain(invoice.getClient());
    entity.setClient(client);
    entity.setCurrency(invoice.getCurrency());

    // Crear los items y establecer la relación bidireccional
    List<InvoiceItemEntity> details = invoice.getItems().stream()
      .map(item -> {
        InvoiceItemEntity itemEntity = new InvoiceItemEntity();
        ProductEntity product = ProductEntity.fromDomain(item.getProduct());
        itemEntity.setProduct(product);
        itemEntity.setQuantity(item.getQuantity());
        itemEntity.setInvoice(entity);
        return itemEntity;
      })
      .toList();

    entity.setItems(details);
    return entity;
  }

  public Invoice toDomain() {
    Invoice invoice = new Invoice();
    invoice.setCodInvoice(codInvoice);
    invoice.setSubTotalPrice(subTotalPrice);
    invoice.setIgv(igv);
    invoice.setTotalPrice(totalPrice);
    invoice.setEmissionDate(emissionDate);
    invoice.setExpirationDate(expirationDate);
    invoice.setCreationDate(creationDate);
    invoice.setModificationDate(modificationDate);
    invoice.setStatus(status);
    invoice.setCurrency(currency);

    ClientEntity clientEntity = getClient();
    if (clientEntity != null) {
      invoice.setClient(clientEntity.toDomain());
    }

    List<InvoiceItemEntity> itemEntities = getItems();
    if (itemEntities != null) {
      List<org.bruno.invoice.domain.InvoiceItem> invoiceItems = itemEntities.stream()
        .map(InvoiceItemEntity::toDomain)
        .toList();
      invoice.setItems(invoiceItems);
    }

    return invoice;
  }

  public static class SQLInvoice {
    static final String TABLE_NAME = "invoice";
    public static final String COD_INVOICE = "cod_invoice";
    public static final String SUB_TOTAL_PRICE = "sub_total_price";
    public static final String IGV = "igv";
    public static final String TOTAL_PRICE = "total_price";
    public static final String EMISSION_DATE = "emission_date";
    public static final String EXPIRATION_DATE = "expiration_date";
    public static final String CREATION_DATE = "creation_date";
    public static final String MODIFICATION_DATE = "modification_date";
    public static final String STATUS = "status";
    public static final String ID_CLIENT = "id_client";
    public static final String CURRENCY = "currency";

    private SQLInvoice() {
      throw new IllegalStateException("Utility class");
    }
  }
}
