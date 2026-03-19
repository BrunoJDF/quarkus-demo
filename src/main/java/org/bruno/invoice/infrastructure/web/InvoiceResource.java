package org.bruno.invoice.infrastructure.web;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bruno.invoice.application.InvoiceService;
import org.bruno.invoice.application.command.CreateInvoiceCommand;
import org.bruno.invoice.infrastructure.web.request.CreateInvoiceRequest;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/invoices")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InvoiceResource {

  private final InvoiceService invoiceService;

  public InvoiceResource(InvoiceService invoiceService) {
    this.invoiceService = invoiceService;
  }

  @APIResponse(responseCode = "201", description = "Invoice created successfully")
  @APIResponse(responseCode = "400", description = "Invalid request")
  @POST
  @Transactional
  public Response createInvoice(CreateInvoiceRequest request) {
    CreateInvoiceCommand command = CreateInvoiceRequest.create(request);
    invoiceService.create(command);
    return Response.status(Response.Status.CREATED).build();
  }
}
