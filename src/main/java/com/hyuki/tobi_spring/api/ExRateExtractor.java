package com.hyuki.tobi_spring.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.math.BigDecimal;

@FunctionalInterface
public interface ExRateExtractor {

  BigDecimal extract(String response) throws JsonProcessingException;
}
