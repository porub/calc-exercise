package com.taboola.exercise.calc.exception;

import lombok.AllArgsConstructor;

/**
 * Exception thrown when an unexpected token is encountered during syntax parsing.
 */
@AllArgsConstructor
public class UnexpectedTokenException extends RuntimeException {

  public UnexpectedTokenException(String message) {
    super(message);
  }

  public UnexpectedTokenException(Throwable cause) {
    super(cause);
  }

  public UnexpectedTokenException(String message, Throwable cause) {
    super(message, cause);
  }
}
