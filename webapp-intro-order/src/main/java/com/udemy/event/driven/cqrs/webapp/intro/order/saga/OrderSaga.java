package com.udemy.event.driven.cqrs.webapp.intro.order.saga;


import com.udemy.event.driven.cqrs.webapp.core.commands.ReserveProductCommand;
import com.udemy.event.driven.cqrs.webapp.core.events.ProductReservedEvent;
import com.udemy.event.driven.cqrs.webapp.intro.order.core.events.OrderCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

@Saga
@Slf4j
@AllArgsConstructor
public class OrderSaga {

  private transient final CommandGateway gateway;

  @StartSaga
  @SagaEventHandler(associationProperty = "orderId")
  public void handle(OrderCreatedEvent orderCreatedEvent) {
    var reserveProdCommand =
            ReserveProductCommand.builder().orderId(orderCreatedEvent.getOrderId())
                    .productId(orderCreatedEvent.getProductId())
                    .quantity(orderCreatedEvent.getQuantity())
                    .userId(orderCreatedEvent.getUserId())
                    .build();
    //
    log.info("The OrderCreatedEvent orderId {}", reserveProdCommand.getOrderId());
    //

    gateway.send(reserveProdCommand, (cm, crm) -> {
      if (crm.isExceptional()) {
        //

      }
    });
  }

  @SagaEventHandler(associationProperty = "orderId")
  public void handle(ProductReservedEvent productReservedEvent) {
    //
    log.info("ProductEvent productId {}", productReservedEvent.getProductId());
  }


}
