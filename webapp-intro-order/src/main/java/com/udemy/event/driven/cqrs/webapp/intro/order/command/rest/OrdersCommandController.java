package com.udemy.event.driven.cqrs.webapp.intro.order.command.rest;

import com.udemy.event.driven.cqrs.webapp.intro.order.command.commands.CreateOrderCommand;
import com.udemy.event.driven.cqrs.webapp.intro.order.core.model.OrderStatus;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrdersCommandController {

  private final CommandGateway gateway;

  @PostMapping
  public String createOrder(@Valid @RequestBody OrderCreateRest order) {

    var userId = "27b95829-4f3f-4ddf-8983-151ba010e35b";

    var createOrderCommand = CreateOrderCommand.builder()
            .addressId(order.getAddressId())
            .productId(order.getProductId())
            .userId(userId)
            .quantity(order.getQuantity())
            .orderId(UUID.randomUUID().toString())
            .orderStatus(OrderStatus.CREATED)
            .build();

    return gateway.sendAndWait(createOrderCommand);
  }

}
