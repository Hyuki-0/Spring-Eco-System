package com.hyuki.tobi_spring.exrate;

import com.hyuki.tobi_spring.payment.ExRateProvider;
import java.math.BigDecimal;

public class SimpleExRateProvider implements ExRateProvider {

  @Override
  public BigDecimal getExRate(String currency) {
    if (currency.equals("USD")) {
      return new BigDecimal("1000");
    }

    throw new IllegalArgumentException("Unsupported currency: " + currency);
  }
}
