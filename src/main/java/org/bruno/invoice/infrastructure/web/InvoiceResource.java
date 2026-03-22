package org.bruno.invoice.infrastructure.web;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bruno.invoice.application.InvoiceService;
import org.bruno.invoice.application.command.CreateInvoiceCommand;
import org.bruno.invoice.application.response.InvoiceResponse;
import org.bruno.invoice.infrastructure.web.request.CreateInvoiceRequest;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.List;

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
  public Response createInvoice(@Valid CreateInvoiceRequest request) {
    CreateInvoiceCommand command = CreateInvoiceRequest.create(request);
    invoiceService.create(command);
    return Response.status(Response.Status.CREATED).build();
  }

  @APIResponse(responseCode = "200", description = "List of invoices")
  @GET
  public List<InvoiceResponse> getInvoices() {
    return invoiceService.findAll();
  }


  @APIResponse(responseCode = "200", description = "Invoice found successfully")
  @APIResponse(responseCode = "404", description = "Invoice not found")
  @GET
  @Path("/{id}")
  public InvoiceResponse getInvoiceById(@PathParam("id") Long id) {
    return invoiceService.findById(id);
  }
}
