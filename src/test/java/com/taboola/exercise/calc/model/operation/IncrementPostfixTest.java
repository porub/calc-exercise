package com.taboola.exercise.calc.model.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.taboola.exercise.calc.model.Operation;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class IncrementPostfixTest {

  @Test
  void calculate_incrementsValuePostfix_whenVariableExists() {
    Map<String, Long> context = new HashMap<>();
    context.put("x", 10L);

    Operation target = new IncrementPostfix("x");
    long result = target.calculate(context);

    assertEquals(10, result); // Returns the original value
    assertEquals(11, context.get("x")); // Updates the value in the context
  }

  @Test
  void calculate_initializesAndIncrements_whenVariableDoesNotExist() {
    Map<String, Long> context = new HashMap<>();

    Operation target = new IncrementPostfix("y");
    long result = target.calculate(context);

    assertEquals(0, result); // Returns default value (0)
    assertEquals(1, context.get("y")); // Sets the value in the context to 1
  }
}
