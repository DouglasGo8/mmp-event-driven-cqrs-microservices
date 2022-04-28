package com.udemy.event.driven.cqrs.webapp.intro.command;

import com.udemy.event.driven.cqrs.webapp.intro.core.data.ProductLookupEntity;
import com.udemy.event.driven.cqrs.webapp.intro.core.data.ProductLookupRepository;
import com.udemy.event.driven.cqrs.webapp.intro.core.events.ProductCreatedEvent;
import lombok.AllArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@ProcessingGroup("product-group")
public class ProductLookupEventsHandler {

  private final ProductLookupRepository repository;

  @EventHandler
  public void on(ProductCreatedEvent event) {
    var productEntity = new ProductLookupEntity(event.getProductId(), event.getTitle());

    repository.save(productEntity);
  }
}
