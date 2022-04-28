package com.udemy.event.driven.cqrs.webapp.intro.order.command.commands;


import com.udemy.event.driven.cqrs.webapp.intro.order.core.model.OrderStatus;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CreateOrderCommand {

  @TargetAggregateIdentifier
  private final String orderId;
  //
  private final int quantity;
  private final String userId;
  private final String productId;
  private final String addressId;
  //
  private final OrderStatus orderStatus;

}
