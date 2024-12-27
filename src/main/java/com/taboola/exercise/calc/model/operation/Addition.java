package com.taboola.exercise.calc.model.operation;

import com.taboola.exercise.calc.model.Operation;
import java.util.Map;

/**
 * Represents an addition operation between two operands.
 */
public record Addition(Operation operand1, Operation operand2) implements Operation {

  @Override
  public long calculate(Map<String, Long> context) {
    long arg1 = operand1.calculate(context);
    long arg2 = operand2.calculate(context);
    return arg1 + arg2;
  }
}

