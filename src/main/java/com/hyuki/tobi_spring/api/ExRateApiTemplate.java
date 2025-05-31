package com.hyuki.tobi_spring.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyuki.tobi_spring.exrate.response.ExRateData;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.stereotype.Component;

@Component
public class ExRateApiTemplate {

  private final ApiExecutor defalutApiExecutor;
  private final ExRateExtractor defaultExRateExtractor;

  public ExRateApiTemplate() {
    this.defalutApiExecutor = new HttpClientApiExecutor();
    this.defaultExRateExtractor = response -> {
      ObjectMapper objectMapper = new ObjectMapper();
      ExRateData exRateData = objectMapper.readValue(response, ExRateData.class);
      return exRateData.rates().get("KRW");
    };
  }

  public BigDecimal getForExRate(String url) {
    return this.getForExRate(url, this.defalutApiExecutor, this.defaultExRateExtractor);
  }

  public BigDecimal getForExRate(String url, ApiExecutor apiExecutor) {
    return this.getForExRate(url, apiExecutor, this.defaultExRateExtractor);
  }

  public BigDecimal getForExRate(String url, ExRateExtractor exRateExtractor) {
    return this.getForExRate(url, defalutApiExecutor, this.defaultExRateExtractor);
  }
  public BigDecimal getForExRate(String url, ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
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
