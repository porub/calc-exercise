package com.taboola.exercise.calc.model.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.taboola.exercise.calc.model.Operation;
import java.util.Map;
import org.junit.jupiter.api.Test;

class NumberTest {

  @Test
  void calculate_returnsParsedValue_whenValueIsPositive() {
    Operation target = new Number("42");

    long result = target.calculate(Map.of());

    assertEquals(42, result);
  }

  @Test
  void calculate_returnsParsedValue_whenValueIsNegative() {
    Operation target = new Number("-15");

    long result = target.calculate(Map.of());

    assertEquals(-15, result);
  }

  @Test
  void calculate_returnsZero_whenValueIsZero() {
    Operation target = new Number("0");

    long result = target.calculate(Map.of());

    assertEquals(0, result);
  }
}
