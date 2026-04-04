package org.bruno.product.infrastructure.web;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bruno.product.application.ProductService;
import org.bruno.product.application.input.CreateProductInput;
import org.bruno.product.application.response.ProductResponse;
import org.bruno.product.infrastructure.web.request.CreateProductRequest;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

  private final ProductService productService;

  public ProductResource(ProductService productService) {
    this.productService = productService;
  }

  @Operation(summary = "Get all products")
  @APIResponse(responseCode = "200", description = "List of products retrieved successfully")
  @GET
  public List<ProductResponse> getProducts() {
    return productService.findAll();
  }

  @Operation(summary = "Get a product by name with currency conversion")
  @APIResponse(responseCode = "200", description = "Product found and converted successfully")
  @APIResponse(responseCode = "404", description = "Product not found")
  @GET
  @Path("/get-by-conversion")
  public ProductResponse getProductByName(@QueryParam("name") String name, @QueryParam("source") String source, @QueryParam("target") String target) {
    return productService.findByName(name, source, target);
  }

  @Operation(summary = "Create a new product")
  @APIResponse(responseCode = "201", description = "Product created successfully")
  @APIResponse(
    responseCode = "400",
    description = "Invalid product data"
  )
  @POST
  @Transactional
  public Response createProduct(CreateProductRequest product) {
    CreateProductInput request = CreateProductRequest.create(product);
    productService.save(request);
    return Response.status(Response.Status.CREATED).build();
  }
}
