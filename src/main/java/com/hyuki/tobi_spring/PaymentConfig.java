package com.hyuki.tobi_spring;

import com.hyuki.tobi_spring.exrate.CachedExRateProvider;
import com.hyuki.tobi_spring.payment.ExRateProvider;
import com.hyuki.tobi_spring.exrate.SimpleExRateProvider;
import com.hyuki.tobi_spring.exrate.WebApiExRateProvider;
import com.hyuki.tobi_spring.payment.PaymentService;
import java.time.Clock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * SpringBoot의 Bean Factory 예시본
 * */
@Configuration
public class PaymentConfig {

  @Bean
  public PaymentService paymentService() {
    return new PaymentService(cachedExRateProvider(), clock());
  }

  @Bean
  public Clock clock() {
    return Clock.systemDefaultZone();
  }

  @Bean
  public ExRateProvider simpleExRateProvider() {
    return new SimpleExRateProvider();
  }

  @Bean
  public ExRateProvider cachedExRateProvider() {
    return new CachedExRateProvider(exRateProvider());
  }

  @Bean
  public ExRateProvider exRateProvider() {
    return new WebApiExRateProvider();
  }
}
