package com.hyuki.tobi_spring.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hyuki.tobi_spring.exrate.response.ExRateData;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

public class ExRateApiTemplate {
  public BigDecimal getExRate(String url, ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
    URI uri;
    try {
      uri = new URI(url);
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }

    String response;

    // HttpURLConnection
    try {
      response = apiExecutor.executeApi(uri);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    ExRateData exRateData;
    try {
      return exRateExtractor.extract(response);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
