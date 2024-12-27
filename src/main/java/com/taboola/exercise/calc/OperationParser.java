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
import static com.taboola.exercise.calc.model.Token.SLASH;

import com.taboola.exercise.calc.exception.UnexpectedTokenException;
import com.taboola.exercise.calc.model.Operation;
import com.taboola.exercise.calc.model.Token;
import com.taboola.exercise.calc.model.TokenValue;
import com.taboola.exercise.calc.model.operation.Addition;
import com.taboola.exercise.calc.model.operation.Assignment;
import com.taboola.exercise.calc.model.operation.AssignmentOfDifference;
import com.taboola.exercise.calc.model.operation.AssignmentOfSum;
import com.taboola.exercise.calc.model.operation.DecrementPostfix;
import com.taboola.exercise.calc.model.operation.DecrementPrefix;
import com.taboola.exercise.calc.model.operation.Division;
import com.taboola.exercise.calc.model.operation.Identifier;
import com.taboola.exercise.calc.model.operation.IncrementPostfix;
import com.taboola.exercise.calc.model.operation.IncrementPrefix;
import com.taboola.exercise.calc.model.operation.Multiplication;
import com.taboola.exercise.calc.model.operation.Number;
import com.taboola.exercise.calc.model.operation.Subtraction;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class OperationParser {

  private final List<TokenValue> tokens;
  private int position = 0;

  public Operation parse() {
    Operation result = parseAssignment();
    if (position < tokens.size()) {
      throw unexpectedTokenInsteadOf("There should be nothing after assignment.");
    }
    return result;
  }

  private Operation parseAssignment() {
    if (currentTokenIs(IDENTIFIER)) {
      TokenValue identifier = consume();
      if (currentTokenIs(EQUAL)) {
        consume();  // consumes '='
        Operation expression = parseExpression();
        return new Assignment(identifier.text(), expression);

      } else if (currentTokenIs(PLUS_EQUAL)) {
        consume();  // consumes '+='
        Operation expression = parseExpression();
        return new AssignmentOfSum(identifier.text(), expression);

      } else if (currentTokenIs(MINUS_EQUAL)) {
        consume();  // consumes '-='
        Operation expression = parseExpression();
        return new AssignmentOfDifference(identifier.text(), expression);

      } else if (currentTokenIs(PLUS_PLUS)) {
        consume(); // consumes '++'
        return new IncrementPostfix(identifier.text());
      } else if (currentTokenIs(MINUS_MINUS)) {
        consume(); // consumes '--'
        return new DecrementPostfix(identifier.text());
      }

    } else if (currentTokenIs(PLUS_PLUS)) {
      consume(); // consumes '++'
      if (currentTokenIs(IDENTIFIER)) {
        TokenValue identifier = consume();
        return new IncrementPrefix(identifier.text());
      }
    } else if (currentTokenIs(MINUS_MINUS)) {
      consume(); // consumes '--'
      if (currentTokenIs(IDENTIFIER)) {
        TokenValue identifier = consume();
        return new DecrementPrefix(identifier.text());
      }
    }
    throw unexpectedTokenInsteadOf("Only assignment or increment lines are allowed.");
  }

  /**
   * Expression grammar:
   * - Expression ::= Addend ["+" Addend]*
   * - Addend     ::= Factor ["*" Factor]*
   * - Factor     ::= Number | IDENTIFIER | Increment | "(" Expression ")"
   */
  private Operation parseExpression() {
    Operation left = parseAddend();
    while (currentTokenIsIn(PLUS, MINUS)) {
      boolean isPlusOperator = currentTokenIs(PLUS);
      consume();  // consumes '+' or '-'
      Operation right = parseAddend();
      left = isPlusOperator
          ? new Addition(left, right)
          : new Subtraction(left, right);
    }
    return left;
  }

  /**
   * Addend grammar:
   * - Addend ::= Factor ["*" Factor]*
   * - Factor ::= Number | IDENTIFIER | Increment | "(" Expression ")"
   */
  private Operation parseAddend() {
    Operation left = parseFactor();
    while (currentTokenIsIn(ASTERISK, SLASH)) {
      boolean isMultiplyOperator = currentTokenIs(ASTERISK);
      consume();  // consumes '*' or '/'
      Operation right = parseFactor();
      left = isMultiplyOperator
          ? new Multiplication(left, right)
          : new Division(left, right);
    }
    return left;
  }

  /**
   * Factor grammar:
   * - Factor     ::= NUMBER | IDENTIFIER | Increment | "(" Expression ")"
   * - Increment  ::= IDENTIFIER "++" | "++" IDENTIFIER
   */
  private Operation parseFactor() {
    // Number.         Examples: 2, 37
    if (currentTokenIs(DIGITS)) {
      TokenValue number = consume();
      return new Number(number.text());

      // Negative Number    Example: -3, -55
    } else if (currentTokenIs(MINUS)) {
      consume(); // consumes '-'
      if (currentTokenIs(DIGITS)) {
        TokenValue number = consume();
        return new Number("-" + number.text());
      }
      throw new UnexpectedTokenException("WAS: -[" + currentTokenTextOrEmpty() + "]. EXPECTED: A Number after the negative sign.");

      // IDENTIFIER[++|--]. Examples: x, y++, z--
    } else if (currentTokenIs(IDENTIFIER)) {
      TokenValue identifier = consume();
      if (currentTokenIs(PLUS_PLUS)) {
        consume();
        return new IncrementPostfix(identifier.text());
      } else if (currentTokenIs(MINUS_MINUS)) {
        consume();
        return new DecrementPostfix(identifier.text());
      }
      return new Identifier(identifier.text());

      // ++IDENTIFIER.      Examples: ++x, --y
    } else if (currentTokenIs(PLUS_PLUS)) {
      consume();
      if (currentTokenIs(IDENTIFIER)) {
        TokenValue identifier = consume();
        return new IncrementPrefix(identifier.text());
      }
      throw new UnexpectedTokenException("WAS: ++[" + currentTokenTextOrEmpty() + "]. EXPECTED: ++identifier.");

      // --IDENTIFIER.      Examples: --x, --z
    } else if (currentTokenIs(MINUS_MINUS)) {
      consume();
      if (currentTokenIs(IDENTIFIER)) {
        TokenValue identifier = consume();
        return new DecrementPrefix(identifier.text());
      }
      throw new UnexpectedTokenException("WAS: --[" + currentTokenTextOrEmpty() + "]. EXPECTED: ++identifier.");
    }

    // "(" Expression ")"
    else if (currentTokenIs(OPEN_PARENTHESIS)) {
      consume(); // Consume the opening parenthesis
      Operation expression = parseExpression();
      if (!currentTokenIs(CLOSE_PARENTHESIS)) {
        throw unexpectedTokenInsteadOf("Closing parenthesis.");
      }
      consume();
      return expression;
    }
    throw unexpectedTokenInsteadOf("A number or identifier.");
  }

  private boolean currentTokenIs(Token type) {
    return position < tokens.size() && tokens.get(position).type() == type;
  }

  private boolean currentTokenIsIn(Token option1, Token option2) {
    if (position >= tokens.size()) {
      return false;
    }
    Token current = tokens.get(position).type();
    return current == option1 || current == option2;
  }

  private TokenValue consume() {
    return tokens.get(position++);
  }

  private UnexpectedTokenException unexpectedTokenInsteadOf(String expectation) {
    return new UnexpectedTokenException("WAS: [" + currentTokenTextOrEmpty() + "]. EXPECTED: " + expectation);
  }

  private String currentTokenTextOrEmpty() {
    return position < tokens.size()
        ? tokens.get(position).text()
        : "";
  }
}
