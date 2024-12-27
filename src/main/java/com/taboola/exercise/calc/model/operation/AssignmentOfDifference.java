package com.taboola.exercise.calc.model.operation;

import com.taboola.exercise.calc.model.Operation;
import java.util.Map;

/**
 * Represents an assignment operation that subtracts the result of an expression from a variable's current value.
 */
public record AssignmentOfDifference(String identifier, Operation expression) implements Operation {

  @Override
  public long calculate(Map<String, Long> context) {
    long oldValue = context.getOrDefault(identifier, 0L);
    long subtraction = expression.calculate(context);
    long result = oldValue - subtraction;
    context.put(identifier, result);
    return result;
  }
}
