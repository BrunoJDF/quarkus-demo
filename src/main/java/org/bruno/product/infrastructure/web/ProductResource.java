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
    public List<Product> getProducts() {
        return productService.findAll();
    }

    @GET
    @Path("/name/{name}")
    public Product getProductByName(@PathParam("name") String name) {
        return productService.findByName(name);
    }

    @POST
    public void createProduct(Product product) {
    }
}
