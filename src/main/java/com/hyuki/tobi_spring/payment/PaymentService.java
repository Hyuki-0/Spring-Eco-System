package com.hyuki.tobi_spring.payment;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {

  private final ExRateProvider exRateProvider;
  private final Clock clock;
  private LocalDateTime validUntil;

  public PaymentService(ExRateProvider exRateProvider, Clock clock) {
    this.exRateProvider = exRateProvider;
    this.clock = clock;
  }

  public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) {
    BigDecimal exRate = exRateProvider.getExRate(currency);

    return Payment.createdPrepared(orderId, currency, foreignCurrencyAmount, exRate, LocalDateTime.now(clock));
  }
}