package com.hyuki.tobi_spring.exrate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyuki.tobi_spring.api.ExRateApiTemplate;
import com.hyuki.tobi_spring.api.SimpleApiExecutor;
import com.hyuki.tobi_spring.exrate.response.ExRateData;
import com.hyuki.tobi_spring.payment.ExRateProvider;
import java.math.BigDecimal;

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
    ExRateApiTemplate exRateApiTemplate = new ExRateApiTemplate();
    String url = "https://open.er-api.com/v6/latest/" + currency;

    return exRateApiTemplate.getExRate(url, new SimpleApiExecutor(), response -> {
      ObjectMapper objectMapper = new ObjectMapper();
      ExRateData exRateData = objectMapper.readValue(response, ExRateData.class);
      return exRateData.rates().get("KRW");
    });
  }
}

