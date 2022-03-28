package com.udemy.event.driven.cqrs.webapp.intro.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
  private String title;
  private BigDecimal price;
  private Integer quantity;
}
