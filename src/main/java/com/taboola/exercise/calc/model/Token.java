package com.taboola.exercise.calc.model;

import java.util.Optional;

public enum Token {
  /**
   * UNSIGNED NUMBER (examples: 123, 45)
   **/
  DIGITS,
  /**
   * Variable Name (examples: x, val)
   **/
  IDENTIFIER,

  /**
   * Addition operator (+)
   **/
  PLUS("+"),
  /**
   * Subtraction operator, or Negative sign (-)
   **/
  MINUS("-"),
  /**
   * Multiplication operator (*)
   **/
  ASTERISK("*"),
  /**
   * Division operator (/)
   **/
  SLASH("/"),
  /**
   * Left round paranthesis '('
   **/
  OPEN_PARENTHESIS("("),
  /**
   * Right round paranthesis '('
   **/
  CLOSE_PARENTHESIS(")"),

  /**
   * Assignment operator (=)
   **/
  EQUAL("="),
  /**
   * Sum Assignment operator (+=)
   **/
  PLUS_EQUAL("+="),
  /**
   * Subtraction Assignment operator (/=)
   **/
  MINUS_EQUAL("-="),
  /**
   * Multiplication Assignment operator (*=)
   **/
  ASTERIC_EQUAL("*="),
  /**
   * Division Assignment operator (/=)
   **/
  SLASH_EQUAL("/="),

  /**
   * Double plus 'increment' operator (++)
   **/
  PLUS_PLUS("++"),
  /**
   * Double minus 'decriment' operator (--)
   **/
  MINUS_MINUS("--"),
  ;


  private final String fixedText;

  Token() {
    this.fixedText = null;
  }

  Token(String fixedText) {
    this.fixedText = fixedText;
  }

  /**
   * Gets the fixed text of the token type.
   *
   * @return the fixed text if present, or empty for dynamic tokens.
   */
  public Optional<String> fixedText() {
    return Optional.ofNullable(fixedText);
  }
}
