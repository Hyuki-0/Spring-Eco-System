package com.hyuki.tobi_spring.payment;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

import com.hyuki.tobi_spring.TestObjectFactory;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestObjectFactory.class)
public class PaymentServiceSpringTest {

  @Autowired
  PaymentService paymentService;

  @Autowired
  ExRateProviderStub exRateProviderStub;


  @Test
  void convertedAmount() throws IOException, InterruptedException {
    // exRate: 1000
    Payment pa = paymentService.prepare(1L, "USD", BigDecimal.TEN);
    assertThat(pa.getExRate()).isEqualByComparingTo(valueOf(1_000));

    //exRate: 500
    exRateProviderStub.setExRate(valueOf(500));
    Payment pa2 = paymentService.prepare(1L, "USD", BigDecimal.TEN);

    assertThat(pa2.getExRate()).isEqualByComparingTo(valueOf(500));
    assertThat(pa2.getConvertedAmount()).isEqualByComparingTo(pa2.getExRate().multiply(BigDecimal.TEN));
  }
}
