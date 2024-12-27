package com.taboola.exercise.calc;

import static com.taboola.exercise.calc.model.Token.ASTERISK;
import static com.taboola.exercise.calc.model.Token.CLOSE_PARENTHESIS;
import static com.taboola.exercise.calc.model.Token.DIGITS;
import static com.taboola.exercise.calc.model.Token.EQUAL;
import static com.taboola.exercise.calc.model.Token.IDENTIFIER;
import static com.taboola.exercise.calc.model.Token.MINUS;
import static com.taboola.exercise.calc.model.Token.MINUS_EQUAL;
import static com.taboola.exercise.calc.model.Token.MINUS_MINUS;
import static com.taboola.exercise.calc.model.Token.OPEN_PARENTHESIS;
import static com.taboola.exercise.calc.model.Token.PLUS;
import static com.taboola.exercise.calc.model.Token.PLUS_EQUAL;
import static com.taboola.exercise.calc.model.Token.PLUS_PLUS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.taboola.exercise.calc.exception.UnexpectedTokenException;
import com.taboola.exercise.calc.model.Operation;
import com.taboola.exercise.calc.model.TokenValue;
import com.taboola.exercise.calc.model.operation.Addition;
import com.taboola.exercise.calc.model.operation.Assignment;
import com.taboola.exercise.calc.model.operation.AssignmentOfDifference;
import com.taboola.exercise.calc.model.operation.AssignmentOfSum;
import com.taboola.exercise.calc.model.operation.Identifier;
import com.taboola.exercise.calc.model.operation.Number;
import com.taboola.exercise.calc.model.operation.Subtraction;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.Test;

class OperationParserTest {

  @Test
  void parse_returnsCorrectOperation_whenSimpleAssignmentProvided() {
    List<TokenValue> tokens = List.of(
        new TokenValue(IDENTIFIER, "x"),
        TokenValue.of(EQUAL),
        new TokenValue(DIGITS, "42")
    );

    OperationParser parser = new OperationParser(tokens);
    Operation result = parser.parse();

    assertInstanceOf(Assignment.class, result);
    assertEquals(42, result.calculate(new HashMap<>()));
  }

  @Test
  void parse_returnsCorrectOperation_whenAdditionExpressionProvided() {
    List<TokenValue> tokens = List.of(
        new TokenValue(IDENTIFIER, "x"),
        TokenValue.of(PLUS_EQUAL),
        new TokenValue(DIGITS, "5"),
        TokenValue.of(PLUS),
        new TokenValue(DIGITS, "3")
    );

    OperationParser parser = new OperationParser(tokens);
    Operation result = parser.parse();

    assertInstanceOf(AssignmentOfSum.class, result);
    assertEquals(8, result.calculate(new HashMap<>()));
  }

  @Test
  void parse_returnsCorrectOperation_whenSubtractionExpressionProvided() {
    List<TokenValue> tokens = List.of(
        new TokenValue(IDENTIFIER, "x"),
        TokenValue.of(MINUS_EQUAL),
        new TokenValue(DIGITS, "10"),
        TokenValue.of(MINUS),
        new TokenValue(DIGITS, "4")
    );

    OperationParser parser = new OperationParser(tokens);
    Operation result = parser.parse();

    assertInstanceOf(AssignmentOfDifference.class, result);
    assertEquals(-6, result.calculate(new HashMap<>()));
  }

  @Test
  void parse_throwsException_whenUnexpectedTokenProvided() {
    List<TokenValue> tokens = List.of(
        new TokenValue(IDENTIFIER, "x"),
        TokenValue.of(PLUS_PLUS),
        new TokenValue(IDENTIFIER, "y")
    );

    OperationParser parser = new OperationParser(tokens);
    UnexpectedTokenException exception = assertThrows(
        UnexpectedTokenException.class, parser::parse
    );

    assertEquals("WAS: [y]. EXPECTED: There should be nothing after assignment.", exception.getMessage());
  }

  @Test
  void parse_returnsCorrectOperation_whenParenthesisExpressionProvided() {
    List<TokenValue> tokens = List.of(
        new TokenValue(IDENTIFIER, "sum"),
        TokenValue.of(EQUAL),
        TokenValue.of(OPEN_PARENTHESIS),
        new TokenValue(DIGITS, "5"),
        TokenValue.of(PLUS),
        new TokenValue(DIGITS, "3"),
        TokenValue.of(CLOSE_PARENTHESIS)
    );

    OperationParser parser = new OperationParser(tokens);
    Operation result = parser.parse();

    assertInstanceOf(Assignment.class, result);
    assertEquals(8, result.calculate(new HashMap<>()));
  }

  @Test
  void parse_returnsNegativeResult_whenNegativeNumberAssinged() {
    List<TokenValue> tokens = List.of(
        new TokenValue(IDENTIFIER, "negative"),
        TokenValue.of(EQUAL),
        TokenValue.of(MINUS),
        new TokenValue(DIGITS, "100")
    );

    OperationParser parser = new OperationParser(tokens);
    Operation result = parser.parse();

    assertEquals(-100, result.calculate(new HashMap<>()));
  }
}
