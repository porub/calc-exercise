package com.taboola.exercise.calc.model.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.taboola.exercise.calc.model.Operation;
import java.util.Map;
import org.junit.jupiter.api.Test;

class SubtractionTest {

  @Test
  void calculate_returnsDifference_whenOperandsArePositiveNumbers() {
    Operation operand1 = new Number("10");
    Operation operand2 = new Number("3");

    Subtraction target = new Subtraction(operand1, operand2);
    long result = target.calculate(Map.of());

    assertEquals(7, result);
  }

  @Test
  void calculate_returnsNegativeDifference_whenResultIsNegative() {
    Operation operand1 = new Number("3");
    Operation operand2 = new Number("10");

    Subtraction target = new Subtraction(operand1, operand2);
    long result = target.calculate(Map.of());

    assertEquals(-7, result);
  }

  @Test
  void calculate_handlesZeroOperand_whenSubtractedFromPositiveNumber() {
    Operation operand1 = new Number("5");
    Operation operand2 = new Number("0");

    Subtraction target = new Subtraction(operand1, operand2);
    long result = target.calculate(Map.of());

    assertEquals(5, result);
  }
}
