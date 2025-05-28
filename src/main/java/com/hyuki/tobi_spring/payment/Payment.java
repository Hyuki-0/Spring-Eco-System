package com.hyuki.tobi_spring.payment;

import com.hyuki.tobi_spring.exrate.WebApiExRateProvider;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.cglib.core.Local;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Payment {

  private static LocalDateTime validUntil1;
  private Long orderId;
  private String currency;
  private BigDecimal foreignCurrencyAmount;
  private BigDecimal exRate;
  private BigDecimal convertedAmount;
  private LocalDateTime validUntil;

  // 클래스 인스턴스가 많아질 떈 Factory Method를 활용한다.
  public static Payment createdPrepared(
      Long orderId,
      String currency,
      BigDecimal foreignCurrencyAmount,
      BigDecimal exRate,
      LocalDateTime now
  ) {
    BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
    LocalDateTime validUntil = now.plusMinutes(30);

    return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
  }

  public boolean isValidUntil(Clock clock) {
    return LocalDateTime.now(clock).isBefore(this.validUntil);
  }
}

