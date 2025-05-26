package com.hyuki.tobi_spring.payment;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class PaymentServiceTest {
  /*
  PaymentService paymentService;

  @BeforeEach
  void init() {
    // 외부 API라고 볼 수 있다.
    // paymentService = new PaymentService(new WebApiExRateProvider());
    paymentService = new PaymentService(new ExRateProviderStub(valueOf(500)));
  }
  */

  // Collaborator들과 연관되어 있을 때, 항상 멱등한가? Test Double, Stub 활용
  @Test
  void prepare() throws IOException {
    Payment stub1 = getPayment(valueOf(100), valueOf(1_000));
    Payment stub2 = getPayment(valueOf(500), valueOf(5_000));
    Payment stub3 = getPayment(valueOf(1000), valueOf(10_000));

    assertThat(stub1.getConvertedAmount()).isNotEqualTo(stub2.getConvertedAmount());
  }

  private Payment getPayment(BigDecimal exRate, BigDecimal convertedAmount) throws IOException {
    PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate));
    Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

    assertThat(payment.getExRate()).isEqualByComparingTo(exRate); // BigDecimal.equals() 유효 자릿수 / 크기까지도 비교한다. 소수점 6자리까지 ㅎㅎ
    assertThat(payment.getConvertedAmount()).isEqualTo(convertedAmount);

    return payment;
  }
}