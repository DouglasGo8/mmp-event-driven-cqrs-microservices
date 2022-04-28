package com.udemy.event.driven.cqrs.webapp.intro.order.query;

import com.udemy.event.driven.cqrs.webapp.intro.order.core.data.OrderEntity;
import com.udemy.event.driven.cqrs.webapp.intro.order.core.data.OrdersRepository;
import com.udemy.event.driven.cqrs.webapp.intro.order.core.events.OrderCreatedEvent;
import lombok.AllArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@ProcessingGroup("order-group")
public class OrderEventsHandler {

  private final OrdersRepository repo;

  // Consumer from Axon Events Broker
  @EventHandler
  public void on(OrderCreatedEvent event) throws Exception {
    var orderEntity = new OrderEntity();
    //
    BeanUtils.copyProperties(event, orderEntity);
    //
    try {
      System.out.println("RECEIVED OOORDERRRR");
      this.repo.save(orderEntity);
    } catch (IllegalArgumentException ex) {
      ex.printStackTrace();
    }
  }
}
