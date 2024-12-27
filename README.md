# Simple Calculator Application
## Overview

This project is a text-based calculator application designed as part of the Infrastructure R&D - Coding Exercise. The purpose of the project is to evaluate technical abilities in software engineering, including correctness, design, and code quality. The calculator supports a subset of Java numeric expressions and operators, excluding solutions like Rhino or Nashorn.

---

## Features

- **Input**: A series of assignment expressions written in a simplified Java-like syntax.
- **Output**: The evaluated result of all variables after processing all expressions.
- **Operators Supported**:
    - Assignment (`=`)
    - Increment (`++`), Decrement (`--`)
    - Addition (`+`), Subtraction (`-`)
    - Multiplication (`*`), Division (`/`)
    - Compound Assignments (`+=`, `-=`, `*=`, `/=`)
- **Parentheses Support** for grouping operations.

---

## Technical Details

- **Language**: Java 17
- **Build Tool**: Maven
- **Unit Testing Framework**: JUnit 5
- **Dependencies**:
    - Lombok (for boilerplate code reduction)
    - JUnit Jupiter (for testing)

---

## Usage

### Build and Run
1. **Clone the repository**:
   ```bash
   git clone https://github.com/porub/calc-exercise.git
   cd calc

2. **Build the project**:
   ```bash
   mvn clean install
   
3. **Run the application**:
   ```bash
   java -cp target/classes com.taboola.exercise.Application
   ```
   
   Input:
   ```
   i = 0
   j = ++i
   x = i++ + 5
   y = (5 + 3) * 10
   i += y
   
   ```
   Add extra empty line (double-press <ENTER> button) at the end to exit.

Output:
   ```
   (i=82,j=1,x=6,y=80)
   ```

### Project Structure
```
src/
├── main/
│   ├── java/com/taboola/exercise/
│   │   ├── Application.java           # Main entry point
│   │   └── calc/
│   │       ├── Calculator.java        # Central text-based expressions processor
│   │       ├── Tokenizer.java         # (internal) Tokenization logic
│   │       ├── OperationParser.java   # (internal) Expression parser
│   │       └── model/
│   │           ├── Token.java         # Tokens enumeratoin
│   │           ├── TokenValue.java    # Token instances
│   │           ├── Operation.java     # Abstract math operation
│   │           └── operation/         
│   │               ├── Addition.java         # concrete implementations
│   │               ├── Subtraction.java
│   │               ├── Multiplication.java
│   │               └── ...
├── test/
│   └── java/com/taboola/exercise/calc/  # Unit tests
└── pom.xml  # Maven configuration
 ```
### Additional Notes
Ensure Java 17 is installed and configured on your system.

