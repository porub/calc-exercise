package com.taboola.exercise.calc.model.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.taboola.exercise.calc.model.Operation;
import java.util.Map;
import org.junit.jupiter.api.Test;

class IdentifierTest {

  @Test
  void calculate_returnsValueFromContext_whenIdentifierExists() {
    Operation target = new Identifier("x");

    Map<String, Long> context = Map.of("x", 42L, "y", 15L);
    long result = target.calculate(context);

    assertEquals(42, result);
  }

  @Test
  void calculate_returnsDefaultValue_whenIdentifierDoesNotExist() {
    Operation target = new Identifier("z");

    Map<String, Long> context = Map.of("x", 42L, "y", 15L);
    long result = target.calculate(context);

    assertEquals(0, result);
  }
}
