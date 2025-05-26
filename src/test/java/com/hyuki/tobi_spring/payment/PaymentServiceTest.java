package com.hyuki.tobi_spring.payment;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PaymentServiceTest {

  Clock clock;

  @BeforeEach
  void init() {
    // 외부 API라고 볼 수 있다.
    clock = Clock.systemDefaultZone();
  }

  // Collaborator들과 연관되어 있을 때, 항상 멱등한가? Test Double, Stub 활용
  @Test
  void prepare() throws IOException {
    getPayment(valueOf(100), valueOf(1_000));
    getPayment(valueOf(500), valueOf(5_000));
    getPayment(valueOf(1000), valueOf(10_000));
  }

  private Payment getPayment(BigDecimal exRate, BigDecimal convertedAmount) throws IOException {
    PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate), clock);
    Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

    assertThat(payment.getExRate()).isEqualByComparingTo(exRate); // BigDecimal.equals() 유효 자릿수 / 크기까지도 비교한다. 소수점 6자리까지 ㅎㅎ
    assertThat(payment.getConvertedAmount()).isEqualTo(convertedAmount);
    return payment;
  }

  @Test
  @DisplayName("validUntil_Success")
  void validUntil_Success() throws IOException{
    // given
    PaymentService paymentService = new PaymentService(new ExRateProviderStub(valueOf(1_000)), clock);
    Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

    // when
    LocalDateTime now = LocalDateTime.now(clock);
    LocalDateTime expectedValidUntil = now.plusMinutes(30);

    // then

    assertThat(payment.getValidUntil()).isEqualTo(expectedValidUntil);
  }
}