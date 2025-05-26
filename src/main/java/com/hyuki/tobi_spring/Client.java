package com.hyuki.tobi_spring;

import com.hyuki.tobi_spring.payment.PaymentService;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {

  public static void main(String[] args) throws IOException, InterruptedException {
    BeanFactory beanFactory = new AnnotationConfigApplicationContext(PaymentConfig.class);

    PaymentConfig objectFactory = (PaymentConfig) beanFactory.getBean("paymentConfig");
    PaymentService paymentService = objectFactory.paymentService();

    System.out.println(paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7)));
    System.out.println("======================================================================\n");

    System.out.println(paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7)));
    System.out.println("======================================================================\n");

    TimeUnit.SECONDS.sleep(1);
    System.out.println(paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7)));
    System.out.println("======================================================================");
  }
}
