package com.ynov.ex09.crud;

import java.time.Instant;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RemoteLogClient {

  private final RestTemplate restTemplate;
  private final LogsApiProperties properties;

  public RemoteLogClient(RestTemplate restTemplate, LogsApiProperties properties) {
    this.restTemplate = restTemplate;
    this.properties = properties;
  }

  public void send(String level, String source, String message) {
    Map<String, String> body =
        Map.of(
            "level", level,
            "source", source,
            "message", message,
            "timestamp", Instant.now().toString());

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    try {
      restTemplate.postForEntity(
          properties.getUrl() + "/api/v1/logs", new HttpEntity<>(body, headers), Void.class);
    } catch (RuntimeException ignored) {
    }
  }
}
