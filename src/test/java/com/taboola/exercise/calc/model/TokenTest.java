package com.taboola.exercise.calc.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Optional;
import org.junit.jupiter.api.Test;

class TokenTest {

  @Test
  void fixedText_returnsCorrectText_forFixedTokens() {
    assertEquals(Optional.of("+"), Token.PLUS.fixedText());
    assertEquals(Optional.of("-"), Token.MINUS.fixedText());
    assertEquals(Optional.of("*"), Token.ASTERISK.fixedText());
    assertEquals(Optional.of("/"), Token.SLASH.fixedText());
    assertEquals(Optional.of("("), Token.OPEN_PARENTHESIS.fixedText());
    assertEquals(Optional.of(")"), Token.CLOSE_PARENTHESIS.fixedText());
    assertEquals(Optional.of("="), Token.EQUAL.fixedText());
    assertEquals(Optional.of("+="), Token.PLUS_EQUAL.fixedText());
    assertEquals(Optional.of("-="), Token.MINUS_EQUAL.fixedText());
    assertEquals(Optional.of("*="), Token.ASTERIC_EQUAL.fixedText());
    assertEquals(Optional.of("/="), Token.SLASH_EQUAL.fixedText());
    assertEquals(Optional.of("++"), Token.PLUS_PLUS.fixedText());
    assertEquals(Optional.of("--"), Token.MINUS_MINUS.fixedText());
  }

  @Test
  void fixedText_returnsEmpty_forDynamicTokens() {
    assertFalse(Token.DIGITS.fixedText().isPresent());
    assertFalse(Token.IDENTIFIER.fixedText().isPresent());
  }

  @Test
  void fixedText_correctlyIdentifiesIfTokenHasFixedText() {
    // Fixed text tokens
    assertTrue(Token.PLUS.fixedText().isPresent());
    assertTrue(Token.MINUS.fixedText().isPresent());
    assertTrue(Token.PLUS_EQUAL.fixedText().isPresent());

    // Dynamic tokens
    assertFalse(Token.DIGITS.fixedText().isPresent());
    assertFalse(Token.IDENTIFIER.fixedText().isPresent());
  }
}
