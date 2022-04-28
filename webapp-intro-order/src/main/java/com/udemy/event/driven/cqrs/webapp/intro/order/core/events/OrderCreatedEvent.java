package com.udemy.event.driven.cqrs.webapp.intro.order.core.events;

import com.udemy.event.driven.cqrs.webapp.intro.order.core.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvent {
  //
  private String orderId;

  private int quantity;
  private String userId;
  private String productId;
  private String addressId;
  //
  private OrderStatus orderStatus;
}
