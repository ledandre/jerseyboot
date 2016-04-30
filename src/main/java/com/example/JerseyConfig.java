package com.example;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.example.controllers.JerseyController;
import com.example.controllers.PersonController;

@Configuration
public class JerseyConfig extends ResourceConfig {
     public JerseyConfig() {
        register(JerseyController.class);
        register(PersonController.class);
    }
}