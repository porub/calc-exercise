package com.taboola.exercise.calc.model.operation;

import com.taboola.exercise.calc.model.Operation;
import java.util.Map;

/**
 * Represents an identifier read operation.
 */
public record Identifier(String identifierName) implements Operation {

  @Override
  public long calculate(Map<String, Long> context) {
    return context.getOrDefault(identifierName, 0L);
  }
}
