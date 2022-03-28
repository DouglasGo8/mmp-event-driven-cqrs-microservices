package com.udemy.event.driven.cqrs.webapp.intro.core.event;

import com.udemy.event.driven.cqrs.webapp.intro.service.CreateProductCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductCreatedEvent {
  final CreateProductCommand createProductCommand;
}
