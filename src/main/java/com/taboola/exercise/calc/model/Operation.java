package com.taboola.exercise.calc.model;

import java.util.Map;

public interface Operation {

  long calculate(Map<String, Long> context);
}

