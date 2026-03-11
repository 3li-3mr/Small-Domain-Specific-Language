# Mathematical Expression Evaluator

A multi-phase implementation of a **calculator compiler** that progresses through different programming paradigms:

- **Phase 1:** Lexer and parser implementation in C using Flex and Bison  
- **Phase 2:** Object-oriented Abstract Syntax Tree (AST) construction in Java  
- **Phase 3:** Functional-style prefix evaluation using recursive traversal  

---

# Project Structure

## Phase 1 — C / Flex / Bison
Implements **lexical and syntax analysis** using a manually designed grammar.

Features:
- Tokenization with **Flex**
- Parsing with **Bison**
- Manual operator precedence implementation
- Expression evaluation with error handling

---

## Phase 2 — Java (Object-Oriented AST)

Builds an **Abstract Syntax Tree (AST)** using the **Composite Design Pattern**.

Features:
- Recursive Descent Parser
- Queue-based token processing
- AST visualization through tree structure

---

## Phase 3 — Java (Prefix Evaluation)

Transforms the AST into **Lisp-style prefix notation** and evaluates it using a **pure recursive approach**.

Features:
- Preorder traversal of the AST
- Prefix expression generation
- Recursive evaluation without global state

---

# Compilation & Execution

## Phase 1 (C)

Requires:

- `flex`
- `bison`

Windows users can install **win_flex** and **win_bison**.

```powershell
bison -d parser.y
flex lexer.l
gcc parser.tab.c lex.yy.c -o mini.exe
.\mini.exe
```

---

## Phase 2 & Phase 3 (Java)

Requires:

- **JDK 8 or higher**

```powershell
javac phase2.java phase3.java
java phase3
```

---

# Implementation Highlights

## Manual Operator Precedence (C)

Instead of using Bison's precedence directives (`%left`, `%right`), operator precedence is enforced through grammar hierarchy:

```
arith       → addition / subtraction
expression  → multiplication / division
factor      → numbers / parentheses
```

---

## AST Construction (Java)

The parser reads **space-separated tokens** using:

```java
Queue<String>
```

Parsing follows **recursive descent**, where method hierarchy enforces precedence (similar to grammar hierarchy in bison):

```
parseArith()
   └── parseExpression()
          └── parseFactor()
```
---

## Arithmetic Expression Modeling

- **Encapsulation:** Each mathematical operation and operand is encapsulated within its own class (e.g., `AddNode`, `MultiplyNode`, `NumberNode`).
- **Inheritance & Polymorphism:** All node classes inherit from a common `abstract class ASTNode`. This allows the parser to treat any part of the expression tree uniformly, invoking `evaluate()` or `print()` regardless of whether the node is a leaf or a branch.
- **Composite Pattern:** The structure forms a tree where branch nodes (`OperatorNodes`) contain references to other `ASTNode` objects, modeling complex nested expressions as a single object.
---

## Prefix Transformation

The AST is traversed using **Preorder Traversal** to produce prefix notation.

Example:

```
Infix:   5 + 3 * 2
Prefix:  (+ 5 (* 3 2))
```

The prefix expression is then evaluated using a **pure recursive function**.

---
