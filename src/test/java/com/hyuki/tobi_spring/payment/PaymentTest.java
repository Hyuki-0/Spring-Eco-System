package com.hyuki.tobi_spring.payment;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PaymentTest {

  @Test
  @DisplayName("createdPayment")
  void createdPayment() {
    // given
    Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    LocalDateTime expectedValidUntil = LocalDateTime.now(clock).plusMinutes(30);

    // when
    Payment payment = Payment.createdPrepared(1L, "USD", BigDecimal.TEN, valueOf(1_000), LocalDateTime.now(clock));

    // then
    assertThat(payment.getConvertedAmount()).isEqualByComparingTo(valueOf(10_000));
    assertThat(payment.getValidUntil()).isEqualTo(expectedValidUntil);
  }

  @Test
  @DisplayName("isValid")
  void isValid() {
    // given
    Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

    // when
    Payment payment = Payment.createdPrepared(1L, "USD", BigDecimal.TEN, valueOf(1_000), LocalDateTime.now(clock));

    // then
    assertThat(payment.isValidUntil(clock)).isTrue();
    assertThat(payment.isValidUntil(Clock.offset(clock, Duration.of(30L, ChronoUnit.MINUTES)))).isFalse();
    System.out.println(LocalDateTime.now(Clock.offset(clock, Duration.of(30L, ChronoUnit.MINUTES))));
  }
}