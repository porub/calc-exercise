package com.taboola.exercise.calc;

import static com.taboola.exercise.calc.model.Token.ASTERIC_EQUAL;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.taboola.exercise.calc.model.TokenValue;
import java.util.List;
import org.junit.jupiter.api.Test;

class TokenizerTest {

  private final Tokenizer target = new Tokenizer();

  @Test
  void readTokens_returnsCorrectTokens_whenBasicExpressionProvided() {
    String expression = "i = 5 + 3";

    List<TokenValue> tokens = target.readTokens(expression);

    assertEquals(5, tokens.size());
    assertEquals(new TokenValue(IDENTIFIER, "i"), tokens.get(0));
    assertEquals(TokenValue.of(EQUAL), tokens.get(1));
    assertEquals(new TokenValue(DIGITS, "5"), tokens.get(2));
    assertEquals(TokenValue.of(PLUS), tokens.get(3));
    assertEquals(new TokenValue(DIGITS, "3"), tokens.get(4));
  }

  @Test
  void readTokens_returnsCorrectTokens_whenOperatorsUsed() {
    String expression = "x += 10 * (y++)";

    List<TokenValue> tokens = target.readTokens(expression);

    assertEquals(8, tokens.size());
    assertEquals(new TokenValue(IDENTIFIER, "x"), tokens.get(0));
    assertEquals(TokenValue.of(PLUS_EQUAL), tokens.get(1));
    assertEquals(new TokenValue(DIGITS, "10"), tokens.get(2));
    assertEquals(TokenValue.of(ASTERISK), tokens.get(3));
    assertEquals(TokenValue.of(OPEN_PARENTHESIS), tokens.get(4));
    assertEquals(new TokenValue(IDENTIFIER, "y"), tokens.get(5));
    assertEquals(TokenValue.of(PLUS_PLUS), tokens.get(6));
    assertEquals(TokenValue.of(CLOSE_PARENTHESIS), tokens.get(7));
  }

  @Test
  void readTokens_throwsException_whenInvalidCharacterProvided() {
    String expression = "a @ b";

    RuntimeException exception = assertThrows(
        RuntimeException.class, () -> target.readTokens(expression)
    );

    assertEquals("Invalid symbol [@] at position [2].", exception.getMessage());
  }

  @Test
  void readTokens_returnsCorrectTokens_whenExpressionWithWhitespaceProvided() {
    String expression = "   x    =    42  ";

    List<TokenValue> tokens = target.readTokens(expression);

    assertEquals(3, tokens.size());
    assertEquals(new TokenValue(IDENTIFIER, "x"), tokens.get(0));
    assertEquals(TokenValue.of(EQUAL), tokens.get(1));
    assertEquals(new TokenValue(DIGITS, "42"), tokens.get(2));
  }

  @Test
  void readTokens_returnsIdentifierToken_whenUnderscoreAndDollarSignUsed() {
    String expression = "$myVarName_123++";

    List<TokenValue> tokens = target.readTokens(expression);

    assertEquals(2, tokens.size());
    assertEquals(new TokenValue(IDENTIFIER, "$myVarName_123"), tokens.get(0));
    assertEquals(TokenValue.of(PLUS_PLUS), tokens.get(1));
  }

  @Test
  void readTokens_returnsCorrectTokens_whenMinusOperatorsUsed() {
    String expression = "x -= 10 - (y--)";

    List<TokenValue> tokens = target.readTokens(expression);

    assertEquals(8, tokens.size());
    assertEquals(new TokenValue(IDENTIFIER, "x"), tokens.get(0));
    assertEquals(TokenValue.of(MINUS_EQUAL), tokens.get(1));
    assertEquals(new TokenValue(DIGITS, "10"), tokens.get(2));
    assertEquals(TokenValue.of(MINUS), tokens.get(3));
    assertEquals(TokenValue.of(OPEN_PARENTHESIS), tokens.get(4));
    assertEquals(new TokenValue(IDENTIFIER, "y"), tokens.get(5));
    assertEquals(TokenValue.of(MINUS_MINUS), tokens.get(6));
    assertEquals(TokenValue.of(CLOSE_PARENTHESIS), tokens.get(7));
  }

  @Test
  void readTokens_returnsCorrectTokens_whenAsteriskOperatorsUsed() {
    String expression = "x *= 2 * 3";

    List<TokenValue> tokens = target.readTokens(expression);

    assertEquals(5, tokens.size());
    assertEquals(new TokenValue(IDENTIFIER, "x"), tokens.get(0));
    assertEquals(TokenValue.of(ASTERIC_EQUAL), tokens.get(1));
    assertEquals(new TokenValue(DIGITS, "2"), tokens.get(2));
    assertEquals(TokenValue.of(ASTERISK), tokens.get(3));
    assertEquals(new TokenValue(DIGITS, "3"), tokens.get(4));
  }
}
