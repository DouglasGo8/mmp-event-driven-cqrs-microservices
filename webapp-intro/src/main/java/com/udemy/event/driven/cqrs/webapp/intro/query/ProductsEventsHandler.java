package com.udemy.event.driven.cqrs.webapp.intro.query;

import com.udemy.event.driven.cqrs.webapp.intro.core.data.ProductEntity;
import com.udemy.event.driven.cqrs.webapp.intro.core.data.ProductRepository;
import com.udemy.event.driven.cqrs.webapp.intro.core.events.ProductCreatedEvent;
import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductsEventsHandler {

  private final ProductRepository repo;

  // Consumer from eventStore
  @EventHandler
  public void on(ProductCreatedEvent event) {
    var productEntity = new ProductEntity();
    BeanUtils.copyProperties(event, productEntity);
    this.repo.save(productEntity);
  }
}
