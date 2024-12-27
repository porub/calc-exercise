package com.taboola.exercise.calc.model.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.taboola.exercise.calc.model.Operation;
import java.util.Map;
import org.junit.jupiter.api.Test;

class AdditionTest {

  @Test
  void calculate_returnsCorrectResult_whenOperandsAreNumbers() {
    Operation operand1 = new Number("5");
    Operation operand2 = new Number("3");

    Addition target = new Addition(operand1, operand2);
    long result = target.calculate(Map.of());

    assertEquals(8, result);
  }

  @Test
  void calculate_returnsOverflowedResult_whenOperandIsMaxLong() {
    Operation operand1 = new Number("" + Long.MAX_VALUE);
    Operation operand2 = new Number("1");

    Addition target = new Addition(operand1, operand2);
    long result = target.calculate(Map.of());

    assertEquals(Long.MIN_VALUE, result);
  }

  @Test
  void calculate_returnsCorrectResult_whenOperandsIncludeNestedOperations() {
    Operation nestedAddition = new Addition(new Number("2"), new Number("3"));
    Operation operand2 = new Number("4");

    Addition addition = new Addition(nestedAddition, operand2);

    long result = addition.calculate(Map.of());

    assertEquals(9, result);
  }
}
