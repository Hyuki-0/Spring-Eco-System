package com.hyuki.tobi_spring;

import static java.math.BigDecimal.valueOf;

import com.hyuki.tobi_spring.payment.ExRateProvider;
import com.hyuki.tobi_spring.payment.ExRateProviderStub;
import com.hyuki.tobi_spring.payment.PaymentService;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestPaymentConfig {

  @Bean
  public PaymentService paymentService() {
    return new PaymentService(exRateProvider(), clock());
  }

  @Bean
  public Clock clock() {
    return Clock.fixed(Instant.now(), ZoneId.systemDefault());
  }
  @Bean
  public ExRateProvider exRateProvider() {
    return new ExRateProviderStub(valueOf(1_000));
//    return new SimpleExRateProvider();
  }
}
