package org.bruno.invoice.infrastructure.web;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bruno.invoice.application.InvoiceService;
import org.bruno.invoice.application.command.CreateInvoiceCommand;
import org.bruno.invoice.infrastructure.web.request.CreateInvoiceRequest;

@Path("/invoices")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InvoiceResource {

    private final InvoiceService invoiceService;

    public InvoiceResource(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    public Response createInvoice(CreateInvoiceRequest request) {
        CreateInvoiceCommand command = CreateInvoiceRequest.create(request);
        invoiceService.create(command);
        return Response.status(Response.Status.CREATED).build();
    }
}
