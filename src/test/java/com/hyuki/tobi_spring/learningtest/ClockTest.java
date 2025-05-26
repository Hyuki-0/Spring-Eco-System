package com.hyuki.tobi_spring.learningtest;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClockTest {

  // Clock 이용해서 LocalDateTime.now
  @Test
  void clock() {
    Clock clock = Clock.system(ZoneId.of("UTC")); // 서버 시간대

    LocalDateTime now = LocalDateTime.now(clock);
    LocalDateTime now2 = LocalDateTime.now(clock);

    assertThat(now).isBeforeOrEqualTo(now2);
  }

  // Clock Test에서 사용할 때 내가 원하는 시간을 지정해서 현재 시간을 가져오게 할 수 있는가?
  @Test
  @DisplayName("fixedClock")
  void fixedClock(){
    // given
    // fixed 사용 시, 시간을 고정해둘 수 있다.
    Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

    // when
    LocalDateTime now = LocalDateTime.now(clock);
    LocalDateTime now2 = LocalDateTime.now(clock);

    // 한 시간을 더 한 경우
    LocalDateTime now3 = now2.plusHours(1);

    // then
    assertThat(now2).isEqualTo(now);

    assertThat(now3).isEqualTo(now.plusHours(1));
  }
}

