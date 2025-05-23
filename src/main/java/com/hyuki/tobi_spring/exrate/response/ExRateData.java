package com.hyuki.tobi_spring.exrate.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ExRateData (String result, Map<String, BigDecimal> rates){

}
