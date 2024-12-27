package com.taboola.exercise.calc.model;

import static com.taboola.exercise.calc.model.Token.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TokenValueTest {

  @Test
  void constructor_createsTokenValue_whenDynamicTokenProvided() {
    TokenValue tokenValue = new TokenValue(IDENTIFIER, "myVar");

    assertEquals(IDENTIFIER, tokenValue.type());
    assertEquals("myVar", tokenValue.text());
  }

  @Test
  void of_createsTokenValue_whenStaticTokenProvided() {
    TokenValue tokenValue = TokenValue.of(PLUS);

    assertEquals(PLUS, tokenValue.type());
    assertEquals("+", tokenValue.text());
  }

  @Test
  void of_throwsException_whenDynamicTokenProvided() {
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> TokenValue.of(IDENTIFIER)
    );

    assertEquals("[IDENTIFIER] is not a static token. HINT: Use 2 args constructor.", exception.getMessage());
  }

  @Test
  void length_returnsCorrectLength_ofTokenText() {
    TokenValue tokenValue = new TokenValue(DIGITS, "12345");

    assertEquals(5, tokenValue.length());
  }
}
