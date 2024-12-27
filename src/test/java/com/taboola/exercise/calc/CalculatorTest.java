package com.taboola.exercise.calc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class CalculatorTest {

  Calculator target = new Calculator();

  @Test
  void calculateAll_parsesHappyPath_whenPlusAndMultiply() {
    var given = Stream.of(
        "i = 0",
        "j = ++i",
        "x = i++ + 5",
        "y = (5 + 3) * 10",
        "i += y"
    );

    Map<String, Long> result = target.calculateAll(given);

    // Expected results in a LinkedHashMap (preserves order)
    var expected = new LinkedHashMap<String, Long>();
    expected.put("i", 82L);
    expected.put("j", 1L);
    expected.put("x", 6L);
    expected.put("y", 80L);
    assertEquals(expected, result);
  }

  @Test
  void calculateAll_parsesHappyPath_whenMinusAndDivide() {
    var given = Stream.of(
        "i = -0",
        "j = --i",
        "x = i-- - -5",
        "y = (-50 - -30) / -10",
        "i -= y"
    );

    Map<String, Long> result = target.calculateAll(given);

    // Expected results in a LinkedHashMap (preserves order)
    var expected = new LinkedHashMap<String, Long>();
    expected.put("i", -4L);
    expected.put("j", -1L);
    expected.put("x", 4L);
    expected.put("y", 2L);
    assertEquals(expected, result);
  }

  @Test
  void calculateAll_parsesHappyPath_whenMinusAndDivide000() {
    var given = Stream.of(
        "i = 0",
        "j = --i",
        "x = i-- - 5",
        "y = (5 - 3) / 2",
        "i -= y"
    );

    Map<String, Long> result = target.calculateAll(given);

    // Expected results in a LinkedHashMap (preserves order)
    var expected = new LinkedHashMap<String, Long>();
    expected.put("i", -3L);
    expected.put("j", -1L);
    expected.put("x", -6L);
    expected.put("y", 1L);
    assertEquals(expected, result);
  }
}
