package com.udemy.event.driven.cqrs.webapp.intro.query;


import com.udemy.event.driven.cqrs.webapp.intro.core.data.ProductRepository;
import com.udemy.event.driven.cqrs.webapp.intro.query.rest.ProductRestModel;
import lombok.AllArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProductsQueryHandler {

  private final ProductRepository repo;

  @QueryHandler
  public List<ProductRestModel> findProducts(FindProductsQuery query) {
    return repo.findAll().stream().map(e -> {
      var restModel = new ProductRestModel();
      BeanUtils.copyProperties(e, restModel);
      return restModel;
    }).collect(Collectors.toList());
  }


}
