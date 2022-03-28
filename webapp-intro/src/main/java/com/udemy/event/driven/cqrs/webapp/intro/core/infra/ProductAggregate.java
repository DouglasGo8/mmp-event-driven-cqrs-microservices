package com.udemy.event.driven.cqrs.webapp.intro.core.infra;

import com.udemy.event.driven.cqrs.webapp.intro.core.event.ProductCreatedEvent;
import com.udemy.event.driven.cqrs.webapp.intro.service.CreateProductCommand;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

@Aggregate
@NoArgsConstructor
public class ProductAggregate {

  @AggregateIdentifier
  private String productId;

  @CommandHandler
  public ProductAggregate(CreateProductCommand createProductCommand) {
    //
    if (createProductCommand.getProduct().getPrice().compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("Price cannot be less than 0");
    }

    if (createProductCommand.getProduct().getTitle().isBlank()) {
      throw new IllegalArgumentException("Title cannot be blank");
    }

    AggregateLifecycle.apply(new ProductCreatedEvent(createProductCommand));


  }

  @EventSourcingHandler
  public void on(ProductCreatedEvent productCreatedEvent) {
    //
    var product = productCreatedEvent.getCreateProductCommand().getProduct();
    //
    this.productId = productCreatedEvent.getCreateProductCommand().getProductId();
    var price = product.getPrice();
    var title = product.getTitle();
    var quantity = product.getQuantity();
    //

  }


}
