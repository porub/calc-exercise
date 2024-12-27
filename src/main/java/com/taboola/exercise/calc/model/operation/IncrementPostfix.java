package com.taboola.exercise.calc.model.operation;

import com.taboola.exercise.calc.model.Operation;
import java.util.Map;

/**
 * Represents a postfix decrement operation (identifier++).
 */
public record IncrementPostfix(String identifier) implements Operation {

  @Override
  public long calculate(Map<String, Long> context) {
    long result = context.getOrDefault(identifier, 0L);
    context.put(identifier, result + 1);
    return result;
  }
}
