package com.udemy.event.driven.cqrs.webapp.intro.query;

import com.udemy.event.driven.cqrs.webapp.intro.core.data.ProductEntity;
import com.udemy.event.driven.cqrs.webapp.intro.core.data.ProductRepository;
import com.udemy.event.driven.cqrs.webapp.intro.core.events.ProductCreatedEvent;
import lombok.AllArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@ProcessingGroup("product-group")
public class ProductsEventsHandler {

  private final ProductRepository repo;

  @ExceptionHandler(resultType = Exception.class)
  public void handle(Exception ex) throws Exception {
    throw ex;
  }

  @ExceptionHandler(resultType = IllegalArgumentException.class)
  public void handle(IllegalArgumentException ex) {

  }

  // Consumer from eventStore
  @EventHandler
  public void on(ProductCreatedEvent event) {
    var productEntity = new ProductEntity();
    BeanUtils.copyProperties(event, productEntity);
    try {
      this.repo.save(productEntity);
    } catch (IllegalArgumentException ex) {
      ex.printStackTrace();
    }
  }
}
