package com.hyuki.tobi_spring.exrate;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/*
 * TODO : Decorator Pattern
 * */
public class CachedExRateProvider implements ExRateProvider {

  private final ExRateProvider target;

  private BigDecimal cachedExRate;
  private LocalDateTime cacheExpiryTime;

  public CachedExRateProvider(ExRateProvider target) {
    this.target = target;
  }

  @Override
  public BigDecimal getExRate(String currency) throws IOException {
    if (cachedExRate == null || cacheExpiryTime.isBefore(LocalDateTime.now())) {
      System.out.println("Cache Update");
      cachedExRate = target.getExRate(currency);
      cacheExpiryTime = LocalDateTime.now().plusSeconds(1);
    }

    return cachedExRate;
  }
}
