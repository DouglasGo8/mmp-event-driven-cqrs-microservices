package com.udemy.event.driven.cqrs.webapp.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class WebAppGatewayApp {

  public static void main(String[] args) {
    SpringApplication.run(WebAppGatewayApp.class, args);
  }

}
