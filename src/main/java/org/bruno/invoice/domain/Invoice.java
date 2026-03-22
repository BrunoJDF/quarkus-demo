package org.bruno.invoice.domain;

import org.bruno.client.domain.Client;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public class Invoice {
  private Long id;
  private String codInvoice;
  private BigDecimal subTotalPrice;
  private BigDecimal igv;
  private BigDecimal totalPrice;
  private InvoiceStatusEnum status;
  private OffsetDateTime emissionDate;
  private OffsetDateTime expirationDate;
  private OffsetDateTime creationDate;
  private OffsetDateTime modificationDate;
  private Client client;
  private List<InvoiceItem> items;
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

  public InvoiceStatusEnum getStatus() {
    return status;
  }

  public void setStatus(InvoiceStatusEnum status) {
    this.status = status;
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

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public List<InvoiceItem> getItems() {
    return items;
  }

  public void setItems(List<InvoiceItem> items) {
    this.items = items;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }
}
