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
import static com.taboola.exercise.calc.model.Token.SLASH;
import static com.taboola.exercise.calc.model.Token.SLASH_EQUAL;
import static java.lang.Character.isWhitespace;

import com.taboola.exercise.calc.exception.UnexpectedSymbolException;
import com.taboola.exercise.calc.model.TokenValue;
import java.util.ArrayList;
import java.util.List;

class Tokenizer {

  public List<TokenValue> readTokens(String expression) {
    List<TokenValue> result = new ArrayList<>();

    int i = 0;
    while (i < expression.length()) {
      char current = expression.charAt(i);

      if (isWhitespace(current)) {
        i++;
      } else if (Character.isDigit(current)) {
        i += readNumber(expression, i, result);
      } else if (isLetter(current)) {
        i += readIdentifier(expression, i, result);
      } else {
        i += readOperatorOrThrow(expression, i, result);
      }
    }

    return result;
  }

  private static int readOperatorOrThrow(String expression, int position, List<TokenValue> result) {
    char current = expression.charAt(position);
    TokenValue token = switch (current) {
      case '=' -> TokenValue.of(EQUAL);
      case '+' -> readPlusRelatedToken(expression, position);
      case '-' -> readMinusRelatedToken(expression, position);
      case '*' -> readAstericRelatedToken(expression, position);
      case '/' -> readSlashRelatedToken(expression, position);
      case '(' -> TokenValue.of(OPEN_PARENTHESIS);
      case ')' -> TokenValue.of(CLOSE_PARENTHESIS);
      default -> throw new UnexpectedSymbolException("Invalid symbol [" + current + "] at position [" + position + "].");
    };
    result.add(token);
    return token.length();
  }

  private static TokenValue readPlusRelatedToken(String expression, int position) {
    char next = nextCharOrZero(expression, position);
    if (next == '+') {
      return TokenValue.of(PLUS_PLUS);
    } else if (next == '=') {
      return TokenValue.of(PLUS_EQUAL);
    } else {
      return TokenValue.of(PLUS);
    }
  }

  private static TokenValue readMinusRelatedToken(String expression, int position) {
    char next = nextCharOrZero(expression, position);
    if (next == '-') {
      return TokenValue.of(MINUS_MINUS);
    } else if (next == '=') {
      return TokenValue.of(MINUS_EQUAL);
    } else {
      return TokenValue.of(MINUS);
    }
  }

  private static TokenValue readAstericRelatedToken(String expression, int position) {
    char next = nextCharOrZero(expression, position);
    if (next == '=') {
      return TokenValue.of(ASTERIC_EQUAL);
    } else {
      return TokenValue.of(ASTERISK);
    }
  }

  private static TokenValue readSlashRelatedToken(String expression, int position) {
    char next = nextCharOrZero(expression, position);
    if (next == '=') {
      return TokenValue.of(SLASH_EQUAL);
    } else {
      return TokenValue.of(SLASH);
    }
  }

  private static char nextCharOrZero(String expression, int position) {
    if (position >= expression.length()) {
      return Character.MIN_VALUE;
    }
    return expression.charAt(position + 1);
  }

  private static int readNumber(String expression, int position, List<TokenValue> result) {
    var number = new StringBuilder();
    for (int i = position; isDigit(expression, i); i++) {
      number.append(expression.charAt(i));
    }
    var token = new TokenValue(DIGITS, number);
    result.add(token);
    return token.length();
  }

  private static int readIdentifier(String expression, int position, List<TokenValue> result) {
    var identifier = new StringBuilder();
    for (int i = position; isLetterOrDigit(expression, i); i++) {
      identifier.append(expression.charAt(i));
    }
    var token = new TokenValue(IDENTIFIER, identifier);
    result.add(token);
    return token.length();
  }

  private static boolean isLetterOrDigit(String expression, int i) {
    if (i >= expression.length()) {
      return false;
    }

    char ch = expression.charAt(i);
    return isLetter(ch) || Character.isDigit(ch);
  }

  private static boolean isDigit(String expression, int i) {
    return i < expression.length() && Character.isDigit(expression.charAt(i));
  }

  private static boolean isLetter(char ch) {
    return switch (ch) {
      case '$', '_' -> true;
      default -> Character.isAlphabetic(ch);
    };
  }
}
