package com.taboola.exercise.calc.exception;

/**
 * Exception thrown for syntax or lexical errors in a given input expression line
 */
public class SyntaxException extends RuntimeException {

  public SyntaxException(String message) {
    super(message);
  }

  public SyntaxException(Throwable cause) {
    super(cause);
  }

  public SyntaxException(String message, Throwable cause) {
    super(message, cause);
  }
}

