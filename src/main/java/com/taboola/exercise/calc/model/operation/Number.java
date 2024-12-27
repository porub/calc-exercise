package com.taboola.exercise.calc.model.operation;

import com.taboola.exercise.calc.model.Operation;
import java.util.Map;

/**
 * Represents a numeric constant parse operation.
 */
public record Number(String value) implements Operation {

  @Override
  public long calculate(Map<String, Long> context) {
    return Long.parseLong(value);
  }
}
