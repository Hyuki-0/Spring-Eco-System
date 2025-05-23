package com.hyuki.tobi_spring.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Payment {

  private Long orderId;
  private String currency;
  private BigDecimal foreignCurrencyAmount;
  private BigDecimal exRate;
  private BigDecimal convertedAmount;
  private LocalDateTime validUntil;

  public static Payment createdPrepared(
      Long orderId,
      String currency,
      BigDecimal foreignCurrencyAmount,
      BigDecimal exRate,
      BigDecimal convertedAmount,
      LocalDateTime validUntil
  ) {
    return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
  }
}

