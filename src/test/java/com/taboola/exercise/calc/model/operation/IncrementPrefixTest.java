package com.taboola.exercise.calc.model.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.taboola.exercise.calc.model.Operation;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class IncrementPrefixTest {

  @Test
  void calculate_incrementsValuePrefix_whenVariableExists() {
    Map<String, Long> context = new HashMap<>();
    context.put("x", 10L);

    Operation target = new IncrementPrefix("x");
    long result = target.calculate(context);

    assertEquals(11, result); // Returns the incremented value
    assertEquals(11, context.get("x")); // Updates the value in the context
  }

  @Test
  void calculate_initializesAndIncrements_whenVariableDoesNotExist() {
    Map<String, Long> context = new HashMap<>();

    Operation target = new IncrementPrefix("y");
    long result = target.calculate(context);

    assertEquals(1, result); // Returns the incremented value
    assertEquals(1, context.get("y")); // Sets the value in the context to 1
  }
}
