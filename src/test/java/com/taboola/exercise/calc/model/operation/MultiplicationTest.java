package com.taboola.exercise.calc.model.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.taboola.exercise.calc.model.Operation;
import java.util.Map;
import org.junit.jupiter.api.Test;

class MultiplicationTest {

  @Test
  void calculate_returnsProduct_whenOperandsArePositiveNumbers() {
    Operation operand1 = new Number("3");
    Operation operand2 = new Number("4");

    Multiplication target = new Multiplication(operand1, operand2);
    long result = target.calculate(Map.of());

    assertEquals(12, result);
  }

  @Test
  void calculate_returnsProduct_whenOperandsIncludeNegativeNumber() {
    Operation operand1 = new Number("-2");
    Operation operand2 = new Number("5");

    Multiplication target = new Multiplication(operand1, operand2);
    long result = target.calculate(Map.of());

    assertEquals(-10, result);
  }

  @Test
  void calculate_returnsZero_whenOneOperandIsZero() {
    Operation operand1 = new Number("0");
    Operation operand2 = new Number("10");

    Multiplication target = new Multiplication(operand1, operand2);
    long result = target.calculate(Map.of());

    assertEquals(0, result);
  }
}
