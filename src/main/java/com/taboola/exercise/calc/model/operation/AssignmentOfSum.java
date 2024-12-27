package com.taboola.exercise.calc.model.operation;

import com.taboola.exercise.calc.model.Operation;
import java.util.Map;

/**
 * Represents an assignment operation that adds the result of an expression to a variable's current value.
 */
public record AssignmentOfSum(String identifier, Operation expression) implements Operation {

  @Override
  public long calculate(Map<String, Long> context) {
    long oldValue = context.getOrDefault(identifier, 0L);
    long addition = expression.calculate(context);
    long result = oldValue + addition;
    context.put(identifier, result);
    return result;
  }
}
