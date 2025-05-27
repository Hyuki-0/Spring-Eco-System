package com.hyuki.tobi_spring.exrate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyuki.tobi_spring.exrate.response.ExRateData;
import com.hyuki.tobi_spring.payment.ExRateProvider;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

/*
 * TODO : Strategy Pattern
 * Refactoring -> Template 공통적인 요소 개선
 * */
public class WebApiExRateProvider implements ExRateProvider {

  @Override
  public BigDecimal getExRate(String currency) {

    URI url = null;

    try {
      url = new URI("https://open.er-api.com/v6/latest/" + currency);
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }

    String response;

    try {
      HttpURLConnection connection = (HttpURLConnection) url.toURL().openConnection();
      try (BufferedReader br = new BufferedReader(new java.io.InputStreamReader(connection.getInputStream()))) {
        response = br.lines().collect(Collectors.joining());
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    ExRateData data = null;
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      data = objectMapper.readValue(response, ExRateData.class);
      return data.rates().get("KRW");
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
