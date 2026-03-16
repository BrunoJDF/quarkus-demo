package org.bruno.invoice.application.command;

public record CreateInvoiceCommand(
    String customerName,
    String customerEmail,
    String customerAddress,
    String productName,
    int quantity,
    String currency
) {
}
