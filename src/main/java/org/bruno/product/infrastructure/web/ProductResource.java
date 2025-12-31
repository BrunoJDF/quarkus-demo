package org.bruno.product.infrastructure.web;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.bruno.product.application.ProductService;
import org.bruno.product.domain.Product;
import org.bruno.product.infrastructure.web.request.CreateProductRequest;
import org.bruno.product.infrastructure.web.response.ProductResponse;

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
    return productService.findAll().stream()
      .map(ProductResponse::fromDomain)
      .toList();
  }

  @GET
  @Path("/name/{name}")
  public ProductResponse getProductByName(@PathParam("name") String name) {
    return ProductResponse.fromDomain(productService.findByName(name));
  }

  @POST
  public void createProduct(CreateProductRequest product) {
    Product request = CreateProductRequest.create(product);
    productService.save(request);
  }
}
