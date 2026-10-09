package com.ynov.ex09.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(LogsApiProperties.class)
public class CrudApplication {

  public static void main(String[] args) {
    SpringApplication.run(CrudApplication.class, args);
  }
}
