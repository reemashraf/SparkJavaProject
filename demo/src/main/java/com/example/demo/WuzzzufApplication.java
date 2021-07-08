package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication

public class WuzzzufApplication {


public static void main(String[] args) {

	SpringApplicationBuilder builder = new SpringApplicationBuilder(WuzzzufApplication.class);

	builder.headless(false);

	ConfigurableApplicationContext context = builder.run(args);

}
}
