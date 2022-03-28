package com.udemy.event.driven.cqrs.webapp.intro.api;


import com.udemy.event.driven.cqrs.webapp.intro.domain.Product;
import com.udemy.event.driven.cqrs.webapp.intro.service.CreateProductCommand;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author dougdb
 */
@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

  private final Environment envs;
  private final CommandGateway gateway;

  @PostMapping
  public String createProduct(@RequestBody Product product) {
    //System.out.println(product.getPrice());

    var productCREATECommand = CreateProductCommand.builder()
            .product(new Product(product.getTitle(), product.getPrice(), product.getQuantity()))
            .productId(UUID.randomUUID().toString());

    // Using Camel template
    return this.gateway.sendAndWait(productCREATECommand);

  }

  @GetMapping
  public String getProduct() {
    return "HTTP GET handled";
  }

  @PutMapping
  public String putProduct() {
    return "HTTP PUT handled";
  }

  @DeleteMapping
  public String deleteProduct() {
    return "HTTP DELETE handled";
  }
}
