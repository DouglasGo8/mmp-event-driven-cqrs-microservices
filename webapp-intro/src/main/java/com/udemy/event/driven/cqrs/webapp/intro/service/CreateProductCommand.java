package com.udemy.event.driven.cqrs.webapp.intro.service;


import com.udemy.event.driven.cqrs.webapp.intro.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductCommand {
  @TargetAggregateIdentifier
  private String productId;
  private Product product;
}
