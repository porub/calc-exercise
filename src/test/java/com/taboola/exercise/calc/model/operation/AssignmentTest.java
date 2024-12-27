package com.taboola.exercise.calc.model.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.taboola.exercise.calc.model.Operation;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class AssignmentTest {

  @Test
  void calculate_storesValueInContext_whenAssignmentIsValid() {
    Operation expression = new Number("42");
    Assignment target = new Assignment("x", expression);

    Map<String, Long> context = new HashMap<>();
    long result = target.calculate(context);

    assertEquals(42, result);
    assertEquals(42, context.get("x"));
  }

  @Test
  void calculate_throwsException_whenExpressionIsInvalid() {
    Operation invalidExpression = context -> {
      throw new ArithmeticException("Invalid calculation");
    };

    Assignment target = new Assignment("x", invalidExpression);

    Map<String, Long> context = new HashMap<>();
    assertThrows(ArithmeticException.class, () -> target.calculate(context));
  }
}
