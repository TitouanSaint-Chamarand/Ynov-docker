package com.ynov.ex09.crud;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "logs.api")
public class LogsApiProperties {

  private String url = "http://localhost:8081";

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
