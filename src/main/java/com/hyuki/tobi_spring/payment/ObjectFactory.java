package com.hyuki.tobi_spring.payment;

import com.hyuki.tobi_spring.exrate.CachedExRateProvider;
import com.hyuki.tobi_spring.exrate.SimpleExRateProvider;
import com.hyuki.tobi_spring.exrate.WebApiExRateProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/*
 * SpringBoot의 Bean Factory 예시본
 * */
@Configuration
public class ObjectFactory {

  @Bean
  public PaymentService paymentService() {
    return new PaymentService(cachedExRateProvider());
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
