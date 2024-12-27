package com.taboola.exercise.calc.model.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.taboola.exercise.calc.model.Operation;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class AssignmentOfSumTest {

  @Test
  void calculate_updatesVariableWithSum_whenValidExpressionProvided() {
    Operation expression = new Number("5");
    AssignmentOfSum target = new AssignmentOfSum("x", expression);

    Map<String, Long> context = new HashMap<>();
    context.put("x", 10L);

    long result = target.calculate(context);

    assertEquals(15, result);
    assertEquals(15, context.get("x"));
  }

  @Test
  void calculate_storesExpressionValue_whenVariableIsNotInContext() {
    Operation expression = new Number("7");
    AssignmentOfSum target = new AssignmentOfSum("y", expression);

    Map<String, Long> context = new HashMap<>();

    long result = target.calculate(context);

    assertEquals(7, result);
    assertEquals(7, context.get("y"));
  }

  @Test
  void calculate_returnsOverflowedValue_whenSumExceedsMaxLong() {
    Operation expression = new Number("1");
    AssignmentOfSum target = new AssignmentOfSum("z", expression);

    Map<String, Long> context = new HashMap<>();
    context.put("z", Long.MAX_VALUE);

    long result = target.calculate(context);

    assertEquals(Long.MIN_VALUE, result); // Overflow behavior
    assertEquals(Long.MIN_VALUE, context.get("z"));
  }
}
