package com.taboola.exercise;

import com.taboola.exercise.calc.Calculator;
import com.taboola.exercise.calc.exception.SyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Application {

  public static void main(String[] args) {
    try {
      List<String> expressions = readStrings();
      Map<String, Long> result = new Calculator().calculateAll(expressions.stream());
      print(result);

    } catch (SyntaxException ex) {
      printError(ex);
    }
  }


  private static ArrayList<String> readStrings() {
    var in = new Scanner(System.in);
    System.out.println("Enter expressions (empty line to finish). Example: i = 7 * 2");

    var expressions = new ArrayList<String>();
    while (true) {
      var input = in.nextLine();
      if (input.isEmpty()) {
        break;
      }
      expressions.add(input);
    }
    return expressions;
  }

  private static void print(Map<String, Long> result) {
    String output = result.entrySet().stream()
        .map(entry -> entry.getKey() + "=" + entry.getValue())
        .collect(Collectors.joining(",", "{", "}"));

    System.out.println(output);
  }

  private static void printError(SyntaxException ex) {
    System.err.println(ex.getMessage());
    if (ex.getCause() != null) {
      System.err.println(ex.getCause().getMessage());
    }
  }
}
