package com.taboola.exercise.calc;

import com.taboola.exercise.calc.exception.SyntaxException;
import com.taboola.exercise.calc.exception.UnexpectedSymbolException;
import com.taboola.exercise.calc.exception.UnexpectedTokenException;
import com.taboola.exercise.calc.model.Operation;
import com.taboola.exercise.calc.model.TokenValue;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Calculator {

  private final Tokenizer tokenizer;

  public Calculator() {
    this.tokenizer = new Tokenizer();
  }

  /**
   * @param expressions
   * @return
   * @throws SyntaxException when cannot parth a given expression.
   */
  public Map<String, Long> calculateAll(Stream<String> expressions) {
    var result = new LinkedHashMap<String, Long>();                             // LinkedMap preserves original order.
    expressions.forEach(expression -> parseAndCalculate(expression, result));
    return result;
  }


  private void parseAndCalculate(String expression, Map<String, Long> result) {
    try {
      List<TokenValue> tokens = tokenizer.readTokens(expression);
      Operation syntaxTree = new OperationParser(tokens).parse();
      syntaxTree.calculate(result);
    } catch (UnexpectedSymbolException | UnexpectedTokenException ex) {
      throw new SyntaxException("Error in line: [" + expression + "].", ex);
    }
  }
}
