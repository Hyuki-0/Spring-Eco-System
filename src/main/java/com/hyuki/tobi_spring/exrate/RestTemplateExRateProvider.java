package com.hyuki.tobi_spring.exrate;

import com.hyuki.tobi_spring.exrate.response.ExRateData;
import com.hyuki.tobi_spring.payment.ExRateProvider;
import java.math.BigDecimal;
import org.springframework.web.client.RestTemplate;

public class RestTemplateExRateProvider implements ExRateProvider {

  private final RestTemplate restTemplate;

  public RestTemplateExRateProvider(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public BigDecimal getExRate(String currency) {
    String url = "https://open.er-api.com/v6/latest/" + currency;

    /*Spring 진영에서 사전 정의해둔 Template 활용*/
    return restTemplate.getForObject(url, ExRateData.class).rates().get("KRW");
  }
}
