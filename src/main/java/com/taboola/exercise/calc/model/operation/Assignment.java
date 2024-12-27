package com.taboola.exercise.calc.model.operation;

import com.taboola.exercise.calc.model.Operation;
import java.util.Map;

/**
 * Represents assignment (write) operation.
 */
public record Assignment(String identifier, Operation expression) implements Operation {

  @Override
  public long calculate(Map<String, Long> context) {
    long result = expression.calculate(context);
    context.put(identifier, result);
    return result;
  }
}

