package com.ynov.ex09.crud;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

  private final RemoteLogClient logClient;

  public RequestLoggingFilter(RemoteLogClient logClient) {
    this.logClient = logClient;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    ContentCachingResponseWrapper wrapped = new ContentCachingResponseWrapper(response);
    try {
      filterChain.doFilter(request, wrapped);
    } finally {
      int status = wrapped.getStatus();
      String message = status >= 400 ? "HTTP " + status : "OK";
      sendLog(request, status, message);
      wrapped.copyBodyToResponse();
    }
  }

  private void sendLog(HttpServletRequest request, int status, String message) {
    if (!request.getRequestURI().startsWith("/api/v1/dogs")) {
      return;
    }
    String level = status >= 500 ? "ERR" : status >= 400 ? "WARN" : "INFO";
    String source =
        "[CrudAPI] "
            + request.getMethod()
            + " "
            + request.getRequestURI();
    logClient.send(level, source, message);
  }
}
