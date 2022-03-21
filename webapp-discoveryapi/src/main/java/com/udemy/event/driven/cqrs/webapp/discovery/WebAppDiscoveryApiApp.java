package com.udemy.event.driven.cqrs.webapp.discovery;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 *
 */
@EnableEurekaServer
@SpringBootApplication
public class WebAppDiscoveryApiApp {
  public static void main(String[] args) {
    SpringApplication.run(WebAppDiscoveryApiApp.class, args);
  }
}
