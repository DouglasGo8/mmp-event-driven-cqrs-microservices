package com.udemy.event.driven.cqrs.webapp.intro.query.rest;

import com.udemy.event.driven.cqrs.webapp.intro.query.FindProductsQuery;
import lombok.AllArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductQueryController {

  private final QueryGateway gateway;

  @GetMapping
  public List<ProductRestModel> getProducts() {

    var findProductsQuery = new FindProductsQuery();

    return gateway.query(findProductsQuery, ResponseTypes.multipleInstancesOf(ProductRestModel.class))
            .join();


  }
}
