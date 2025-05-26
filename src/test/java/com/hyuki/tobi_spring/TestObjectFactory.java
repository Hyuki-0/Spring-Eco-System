package com.hyuki.tobi_spring;

import static java.math.BigDecimal.valueOf;

import com.hyuki.tobi_spring.exrate.SimpleExRateProvider;
import com.hyuki.tobi_spring.payment.ExRateProvider;
import com.hyuki.tobi_spring.payment.ExRateProviderStub;
import com.hyuki.tobi_spring.payment.PaymentService;
import java.math.BigDecimal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestObjectFactory {

  @Bean
  public PaymentService paymentService() {
    return new PaymentService(exRateProvider());
  }

  @Bean
  public ExRateProvider exRateProvider() {
    return new ExRateProviderStub(valueOf(1_000));
//    return new SimpleExRateProvider();
  }
}
