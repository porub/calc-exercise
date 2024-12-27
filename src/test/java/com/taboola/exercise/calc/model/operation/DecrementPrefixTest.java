package com.taboola.exercise.calc.model.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.taboola.exercise.calc.model.Operation;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class DecrementPrefixTest {

  @Test
  void calculate_decrementsValuePrefix_whenVariableExists() {
    Map<String, Long> context = new HashMap<>();
    context.put("x", 10L);

    Operation target = new DecrementPrefix("x");
    long result = target.calculate(context);

    assertEquals(9, result); // Returns the decremented value
    assertEquals(9, context.get("x")); // Updates the value in the context
  }

  @Test
  void calculate_initializesAndDecrements_whenVariableDoesNotExist() {
    Map<String, Long> context = new HashMap<>();

    Operation target = new DecrementPrefix("y");
    long result = target.calculate(context);

    assertEquals(-1, result); // Returns the decremented value
    assertEquals(-1, context.get("y")); // Sets the value in the context to -1
  }
}
