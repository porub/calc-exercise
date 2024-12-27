package com.taboola.exercise.calc.model.operation;

import com.taboola.exercise.calc.model.Operation;
import java.util.Map;

/**
 * Represents a division operation between two operands.
 */
public record Division(Operation operand1, Operation operand2) implements Operation {

  @Override
  public long calculate(Map<String, Long> context) {
    long arg1 = operand1.calculate(context);
    long arg2 = operand2.calculate(context);

    if (0 == arg2) {
      throw new ArithmeticException("Division by zero is not allowed.");
    }

    return arg1 / arg2;
  }
}
