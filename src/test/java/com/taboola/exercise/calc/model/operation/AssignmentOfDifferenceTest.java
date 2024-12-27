package com.taboola.exercise.calc.model.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.taboola.exercise.calc.model.Operation;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class AssignmentOfDifferenceTest {

  @Test
  void calculate_updatesVariableWithDifference_whenValidExpressionProvided() {
    Operation expression = new Number("5");
    AssignmentOfDifference target = new AssignmentOfDifference("x", expression);

    Map<String, Long> context = new HashMap<>();
    context.put("x", 10L);

    long result = target.calculate(context);

    assertEquals(5, result);
    assertEquals(5, context.get("x"));
  }

  @Test
  void calculate_storesNegativeValue_whenExpressionResultIsGreaterThanCurrentValue() {
    Operation expression = new Number("15");
    AssignmentOfDifference target = new AssignmentOfDifference("x", expression);

    Map<String, Long> context = new HashMap<>();
    context.put("x", 10L);

    long result = target.calculate(context);

    assertEquals(-5, result);
    assertEquals(-5, context.get("x"));
  }

  @Test
  void calculate_returnsCorrectValue_whenVariableIsNotInContext() {
    Operation expression = new Number("7");
    AssignmentOfDifference target = new AssignmentOfDifference("y", expression);

    Map<String, Long> context = new HashMap<>();

    long result = target.calculate(context);

    assertEquals(-7, result);
    assertEquals(-7, context.get("y"));
  }

  @Test
  void calculate_throwsException_whenExpressionFails() {
    Operation invalidExpression = context -> {
      throw new ArithmeticException("Invalid calculation");
    };

    AssignmentOfDifference target = new AssignmentOfDifference("z", invalidExpression);

    Map<String, Long> context = new HashMap<>();
    context.put("z", 10L);

    assertThrows(ArithmeticException.class, () -> target.calculate(context));
  }
}
