package com.hyuki.tobi_spring.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.stream.Collectors;

public class SimpleApiExecutor implements ApiExecutor {

  @Override
  public String executeApi(URI uri) throws IOException {
    String response;
    HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
    try (BufferedReader br = new BufferedReader(new java.io.InputStreamReader(connection.getInputStream()))) {
      response = br.lines().collect(Collectors.joining());
    }
    return response;
  }
}
