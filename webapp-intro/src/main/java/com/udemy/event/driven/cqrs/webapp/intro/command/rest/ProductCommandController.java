package com.udemy.event.driven.cqrs.webapp.intro.command.rest;


import com.udemy.event.driven.cqrs.webapp.intro.command.CreateProductCommand;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

/**
 * @author dougdb
 */
@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductCommandController {

  private final Environment env;
  // Same ProducerTemplate Apache Camel concept
  private final CommandGateway gateway;

  @PostMapping
  public String createProduct(@Valid @RequestBody CreateProductRestModel body) {
    //System.out.println(product.getPrice());

    var command = CreateProductCommand.builder()
            .price(body.getPrice())
            .title(body.getTitle())
            .quantity(body.getQuantity())
            .productId(UUID.randomUUID().toString())
            .build();

    return this.gateway.sendAndWait(command);

    // the same concept requestBody from producerTemplate
    /*try {
      return this.gateway.sendAndWait(command); // returns a AggregateIdentifier stored axon server
    } catch (Exception ex) {
      return ex.getLocalizedMessage();
    }*/
  }

}
