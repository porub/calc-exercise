package com.taboola.exercise.calc.exception;

/**
 * Exception thrown when an illegal symbol is encountered during tokenization.
 */
public class UnexpectedSymbolException extends RuntimeException {

  public UnexpectedSymbolException(String message) {
    super(message);
  }

  public UnexpectedSymbolException(Throwable cause) {
    super(cause);
  }

  public UnexpectedSymbolException(String message, Throwable cause) {
    super(message, cause);
  }
}
