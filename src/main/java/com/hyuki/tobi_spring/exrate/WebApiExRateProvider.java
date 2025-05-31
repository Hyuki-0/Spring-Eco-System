package com.hyuki.tobi_spring.exrate;

import com.hyuki.tobi_spring.api.ApiExecutor;
import com.hyuki.tobi_spring.api.ExRateApiTemplate;
import com.hyuki.tobi_spring.payment.ExRateProvider;
import java.math.BigDecimal;

/*
 * TODO : Strategy Pattern
 * Refactoring -> Template 공통적인 요소 개선
 * */
public class WebApiExRateProvider implements ExRateProvider {

  private final ExRateApiTemplate exRateApiTemplate;

  public WebApiExRateProvider(ExRateApiTemplate exRateApiTemplate) {
    this.exRateApiTemplate = exRateApiTemplate;
  }
  /*
   * 클라이언트가 콜백 함수를 만들어 템플릿을 호출한다.
   * */
  @Override
  public BigDecimal getExRate(String currency) {
    String url = "https://open.er-api.com/v6/latest/" + currency;

    return exRateApiTemplate.getForExRate(url);
  }
}

