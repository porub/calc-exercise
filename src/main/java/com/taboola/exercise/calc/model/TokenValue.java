package com.taboola.exercise.calc.model;

public record TokenValue(Token type, String text) {

  /**
   * Creates a non-fixed token instance (with a custom text value).
   *
   * @param type  - the type of the token. Known types are {@link Token#IDENTIFIER}, {@link  Token#DIGITS}
   * @param value - the custom text of the token
   */
  public TokenValue(Token type, CharSequence value) {
    this(type, value.toString());
  }

  /**
   * Gets a fixed-text static token instance by its enumeration constant
   *
   * @param staticToken - the type of the token. Known types are {@link Token#IDENTIFIER}, {@link  Token#DIGITS}
   * @return the token instance of an operation or parenthesis type.
   */
  public static TokenValue of(Token staticToken) {
    return new TokenValue(staticToken, staticToken.fixedText()
        .orElseThrow(() -> new IllegalArgumentException("[" + staticToken + "] is not a static token. HINT: Use 2 args constructor.")));
  }


  public int length() {
    return text.length();
  }
}
