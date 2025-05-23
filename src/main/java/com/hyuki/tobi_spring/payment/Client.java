package com.hyuki.tobi_spring.payment;

import ch.qos.logback.core.util.TimeUtil;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {

  public static void main(String[] args) throws IOException, InterruptedException {
    BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);

    ObjectFactory objectFactory = (ObjectFactory) beanFactory.getBean("objectFactory");
    PaymentService paymentService1 = objectFactory.paymentService();

    System.out.println(paymentService1.prepare(100L, "USD", BigDecimal.valueOf(50.7)));
    System.out.println("======================================================================\n");

    System.out.println(paymentService1.prepare(100L, "USD", BigDecimal.valueOf(50.7)));
    System.out.println("======================================================================\n");

    TimeUnit.SECONDS.sleep(3);
    System.out.println(paymentService1.prepare(100L, "USD", BigDecimal.valueOf(50.7)));
    System.out.println("======================================================================");


  }
}
