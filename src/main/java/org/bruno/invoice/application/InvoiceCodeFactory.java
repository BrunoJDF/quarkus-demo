package org.bruno.invoice.application;

public class InvoiceCodeFactory {
    public static String generateCode() {
        String prefix = "INV";
        String timestamp = String.valueOf(System.currentTimeMillis());
        return String.format("%s-%s", prefix, timestamp);
    }
}
