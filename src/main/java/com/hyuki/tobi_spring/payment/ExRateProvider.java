package com.hyuki.tobi_spring.payment;

import java.io.IOException;
import java.math.BigDecimal;

/*
* DIP를 위해 사용하는 클라이언트쪽으로 인터페이스 위치를 옮긴다.
*
* */
public interface ExRateProvider {
  BigDecimal getExRate(String currency) throws IOException;
}