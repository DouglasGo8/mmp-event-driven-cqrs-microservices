package com.udemy.event.driven.cqrs.webapp.intro.order.command;

import com.udemy.event.driven.cqrs.webapp.intro.order.command.commands.CreateOrderCommand;
import com.udemy.event.driven.cqrs.webapp.intro.order.core.events.OrderCreatedEvent;
import com.udemy.event.driven.cqrs.webapp.intro.order.core.model.OrderStatus;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@NoArgsConstructor
public class OrderAggregate {

  //  @TargetAggregateIdentifier in Command class
  @AggregateIdentifier
  private String orderId;
  //
  private int quantity;
  private String userId;
  private String productId;
  private String addressId;
  //
  private OrderStatus orderStatus;

  // Publisher
  @CommandHandler
  public OrderAggregate(CreateOrderCommand createOrderCommand) {
    var orderCreatedEvent = new OrderCreatedEvent();
    BeanUtils.copyProperties(createOrderCommand, orderCreatedEvent);
    //
    AggregateLifecycle.apply(orderCreatedEvent);
  }


  // Consumer
  @EventSourcingHandler
  public void on(OrderCreatedEvent orderCreatedEvent)  throws Exception {
    this.orderId = orderCreatedEvent.getOrderId();
    this.productId = orderCreatedEvent.getProductId();
    this.userId = orderCreatedEvent.getUserId();
    this.addressId = orderCreatedEvent.getAddressId();
    this.quantity = orderCreatedEvent.getQuantity();
    this.orderStatus = orderCreatedEvent.getOrderStatus();
  }
}
