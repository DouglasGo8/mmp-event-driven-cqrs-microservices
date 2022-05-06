package com.udemy.event.driven.cqrs.webapp.intro.command;

import com.udemy.event.driven.cqrs.webapp.core.commands.ReserveProductCommand;
import com.udemy.event.driven.cqrs.webapp.core.events.ProductReservedEvent;
import com.udemy.event.driven.cqrs.webapp.intro.core.events.ProductCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
public class ProductAggregate {

  @AggregateIdentifier
  private String productId;
  private String title;
  private BigDecimal price;
  private Integer quantity;

  public ProductAggregate() {
  }
  //

  @CommandHandler
  public ProductAggregate(CreateProductCommand command) {

    if (command.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("Price must be greater than Zero");
    }

    if (command.getTitle().isBlank()) {
      throw new IllegalArgumentException("Title cannot be Empty");
    }

    if (command.getQuantity().equals(0)) {
      throw new IllegalArgumentException("Quantity must be greater than Zero");
    }

    var productEvent = new ProductCreatedEvent();

    BeanUtils.copyProperties(command, productEvent);

    AggregateLifecycle.apply(productEvent); // dispatch event handlers

  }

  @CommandHandler
  public void handle(ReserveProductCommand reserveProductCommand) {
    if (quantity < reserveProductCommand.getQuantity()) {
      throw new IllegalArgumentException("Wrong numbers in stock");
    }

    var productReservedEvt = ProductReservedEvent.builder()
            .orderId(reserveProductCommand.getOrderId())
            .productId(reserveProductCommand.getProductId())
            .quantity(reserveProductCommand.getQuantity())
            .userId(reserveProductCommand.getUserId())
            .build();

    AggregateLifecycle.apply(productReservedEvt);
  }

  // Event Store producer action
  @EventSourcingHandler
  public void on(ProductCreatedEvent productCreatedEvent) {
    this.productId = productCreatedEvent.getProductId();
    this.price = productCreatedEvent.getPrice();
    this.title = productCreatedEvent.getTitle();
    this.quantity = productCreatedEvent.getQuantity();
  }

  @EventSourcingHandler
  public void on (ProductReservedEvent productReservedEvent) {
    this.quantity -= productReservedEvent.getQuantity();
  }
}
