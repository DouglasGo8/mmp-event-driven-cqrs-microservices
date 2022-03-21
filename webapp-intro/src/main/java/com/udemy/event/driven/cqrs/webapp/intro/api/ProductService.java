package com.udemy.event.driven.cqrs.webapp.intro.api;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductService {

  @PostMapping
  public String createProduct() {
    return "HTTP POST handled";
  }

  @GetMapping
  public String getProduct() {
    return "HTTP GET handled";
  }

  @PutMapping
  public String putProduct() {
    return "HTTP PUT handled";
  }

  @DeleteMapping
  public String deleteProduct() {
    return "HTTP DELETE handled";
  }
}
