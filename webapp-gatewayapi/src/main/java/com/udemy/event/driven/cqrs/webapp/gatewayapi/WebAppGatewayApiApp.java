package com.udemy.event.driven.cqrs.webapp.gatewayapi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class WebAppGatewayApiApp {

  public static void main(String[] args) {
    SpringApplication.run(WebAppGatewayApiApp.class, args);
  }

}
