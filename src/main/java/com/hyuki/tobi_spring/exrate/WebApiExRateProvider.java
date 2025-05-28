package com.hyuki.tobi_spring.exrate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyuki.tobi_spring.api.ApiExecutor;
import com.hyuki.tobi_spring.api.ExRateExtractor;
import com.hyuki.tobi_spring.api.SimpleApiExecutor;
import com.hyuki.tobi_spring.exrate.response.ExRateData;
import com.hyuki.tobi_spring.payment.ExRateProvider;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.stream.Collectors;

/*
 * TODO : Strategy Pattern
 * Refactoring -> Template 공통적인 요소 개선
 * */
public class WebApiExRateProvider implements ExRateProvider {

  /*
  * 클라이언트가 콜백 함수를 만들어 템플릿을 호출한다.
  * */
  @Override
  public BigDecimal getExRate(String currency) {
    String url = "https://open.er-api.com/v6/latest/" + currency;

    return runApiForExRate(url, new SimpleApiExecutor(), response -> {
      ObjectMapper objectMapper = new ObjectMapper();
      ExRateData exRateData = objectMapper.readValue(response, ExRateData.class);
      return exRateData.rates().get(currency);
    });
  }

  private static BigDecimal runApiForExRate(String url, ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
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

    try {
      return exRateExtractor.extract(response);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}

