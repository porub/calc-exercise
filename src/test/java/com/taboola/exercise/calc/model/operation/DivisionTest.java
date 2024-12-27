package com.taboola.exercise.calc.model.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.taboola.exercise.calc.model.Operation;
import java.util.Map;
import org.junit.jupiter.api.Test;

class DivisionTest {

  @Test
  void calculate_returnsQuotient_whenOperandsAreValid() {
    Operation operand1 = new Number("10");
    Operation operand2 = new Number("2");

    Division target = new Division(operand1, operand2);
    long result = target.calculate(Map.of());

    assertEquals(5, result);
  }

  @Test
  void calculate_throwsException_whenDividingByZero() {
    Operation operand1 = new Number("10");
    Operation operand2 = new Number("0");

    Division target = new Division(operand1, operand2);

    assertThrows(ArithmeticException.class, () -> target.calculate(Map.of()), "Division by zero is not allowed.");
  }

  @Test
  void calculate_handlesNegativeQuotient_whenOperandsAreNegative() {
    Operation operand1 = new Number("-10");
    Operation operand2 = new Number("2");

    Division target = new Division(operand1, operand2);
    long result = target.calculate(Map.of());

    assertEquals(-5, result);
  }
}
