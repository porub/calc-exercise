package com.taboola.exercise.calc.model.operation;

import com.taboola.exercise.calc.model.Operation;
import java.util.Map;

/**
 * Represents a prefix decrement operation (++identifier).
 */
public record IncrementPrefix(String identifier) implements Operation {

  @Override
  public long calculate(Map<String, Long> context) {
    long oldValue = context.getOrDefault(identifier, 0L);
    long result = oldValue + 1;
    context.put(identifier, result);
    return result;
  }
}
