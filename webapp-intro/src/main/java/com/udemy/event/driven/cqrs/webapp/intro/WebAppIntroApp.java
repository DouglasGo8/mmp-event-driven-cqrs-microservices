package com.udemy.event.driven.cqrs.webapp.intro;

import com.udemy.event.driven.cqrs.webapp.intro.command.interceptor.CreateProductCommandInterceptor;
import com.udemy.event.driven.cqrs.webapp.intro.core.errorhandling.ProductsServiceEventsErrorHandler;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

/**
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
public class WebAppIntroApp {

  /**
   * A main method to start this application.
   */
  public static void main(String[] args) {
    SpringApplication.run(WebAppIntroApp.class, args);
  }

  @Autowired
  public void createCommandProductInterceptor(ApplicationContext context, CommandBus bus) {
    bus.registerDispatchInterceptor(context.getBean(CreateProductCommandInterceptor.class));
  }

  @Autowired
  public void configure(EventProcessingConfigurer config) {
    config.registerListenerInvocationErrorHandler("product-group",
            conf -> new ProductsServiceEventsErrorHandler());
  }

}
