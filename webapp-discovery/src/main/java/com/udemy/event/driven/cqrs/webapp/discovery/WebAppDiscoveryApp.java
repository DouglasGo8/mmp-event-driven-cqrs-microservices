package com.udemy.event.driven.cqrs.webapp.discovery;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 *
 */
@EnableEurekaServer
@SpringBootApplication
public class WebAppDiscoveryApp {
  public static void main(String[] args) {
    SpringApplication.run(WebAppDiscoveryApp.class, args);
  }
}
