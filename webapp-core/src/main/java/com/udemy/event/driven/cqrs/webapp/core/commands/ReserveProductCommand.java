package com.udemy.event.driven.cqrs.webapp.core.commands;


import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class ReserveProductCommand {


  private final int quantity;

  private final String userId;
  private final String orderId;

  @TargetAggregateIdentifier
  private final String productId;
}
