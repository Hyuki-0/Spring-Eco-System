package com.hyuki.tobi_spring.payment;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

import com.hyuki.tobi_spring.TestObjectFactory;
import java.io.IOException;
import java.math.BigDecimal;
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

  @Test
  void convertedAmount() throws IOException, InterruptedException {
    Payment pa = paymentService.prepare(1L, "USD", BigDecimal.TEN);
    assertThat(pa.getExRate()).isEqualByComparingTo(valueOf(1_000));
  }
}
