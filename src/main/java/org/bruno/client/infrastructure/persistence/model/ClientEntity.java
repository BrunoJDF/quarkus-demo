package org.bruno.client.infrastructure.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.bruno.client.domain.Client;
import org.bruno.client.domain.ClientStatus;

import java.util.Optional;

@Entity
@Table(name = ClientEntity.SQLClient.TABLE_NAME)
public class ClientEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = SQLClient.NAME)
  private String name;
  @Column(name = SQLClient.LAST_NAME)
  private String lastName;
  @Column(name = SQLClient.FULL_NAME)
  private String fullName;
  @Column(name = SQLClient.RUC)
  private String ruc;
  @Column(name = SQLClient.EMAIL)
  private String email;
  @Column(name = SQLClient.PHONE)
  private String phone;
  @Column(name = SQLClient.ADDRESS)
  private String address;
  @Column(name = SQLClient.STATUS)
  @Enumerated(EnumType.STRING)
  private ClientStatus status;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getRuc() {
    return ruc;
  }

  public void setRuc(String ruc) {
    this.ruc = ruc;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public ClientStatus getStatus() {
    return status;
  }

  public void setStatus(ClientStatus status) {
    this.status = status;
  }

  public static class SQLClient {
    static final String TABLE_NAME = "client";

    private SQLClient() {
      throw new IllegalStateException("Utility class");
    }

    public static final String NAME = "name";
    public static final String LAST_NAME = "last_name";
    public static final String FULL_NAME = "full_name";
    public static final String RUC = "ruc";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String ADDRESS = "address";
    public static final String STATUS = "status";
  }

  public Client toDomain() {
    Client client = new Client();
    client.setId(this.id);
    client.setName(this.name);
    client.setLastName(this.lastName);

    String localFullName = Optional.ofNullable(this.fullName)
      .orElseGet(() -> buildFullName(this.name, this.lastName));
    client.setFullName(localFullName);

    client.setRuc(this.ruc);
    client.setEmail(this.email);
    client.setPhone(this.phone);
    client.setAddress(this.address);
    client.setStatus(this.status);
    return client;
  }

  public static ClientEntity fromDomain(Client client) {
    ClientEntity entity = new ClientEntity();
    entity.setId(client.getId());
    entity.setName(client.getName());
    entity.setLastName(client.getLastName());

    String fullName = buildFullName(client.getName(), client.getLastName());
    entity.setFullName(fullName);

    entity.setRuc(client.getRuc());
    entity.setEmail(client.getEmail());
    entity.setPhone(client.getPhone());
    entity.setAddress(client.getAddress());
    entity.setStatus(client.getStatus());
    return entity;
  }

  public static void updateFromDomain(ClientEntity client, Client toUpdate) {
    client.setName(toUpdate.getName());
    client.setLastName(toUpdate.getLastName());
    client.setRuc(toUpdate.getRuc());
    client.setEmail(toUpdate.getEmail());
    client.setPhone(toUpdate.getPhone());
    client.setAddress(toUpdate.getAddress());

    String fullName = buildFullName(client.getName(), client.getLastName());
    client.setFullName(fullName);

    Optional.ofNullable(toUpdate.getStatus())
      .ifPresent(client::setStatus);
  }

  private static String buildFullName(String name, String lastName) {
    return String.format("%s %s", name, lastName);
  }
}
