package org.bruno.product.infrastructure.web;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bruno.product.application.ProductService;
import org.bruno.product.application.command.CreateProductCommand;
import org.bruno.product.application.response.ProductResponse;
import org.bruno.product.infrastructure.web.request.CreateProductRequest;

import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

  private final ProductService productService;

  public ProductResource(ProductService productService) {
    this.productService = productService;
  }

  @GET
  public List<ProductResponse> getProducts() {
    return productService.findAll();
  }

  @GET
  @Path("/name")
  public ProductResponse getProductByName(@QueryParam("name") String name) {
    return productService.findByName(name);
  }

  @POST
  @Transactional
  public Response createProduct(CreateProductRequest product) {
    CreateProductCommand request = CreateProductRequest.create(product);
    productService.save(request);
    return Response.status(Response.Status.CREATED).build();
  }
}
