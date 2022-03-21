package com.udemy.event.driven.cqrs.webapp.intro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

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

}
