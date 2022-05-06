package com.udemy.event.driven.cqrs.webapp.core.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductReservedEvent {

  private final int quantity;
  private final String userId;
  private final String orderId;
  private final String productId;
}
