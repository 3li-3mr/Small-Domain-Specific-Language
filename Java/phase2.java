package Java;

import java.util.*;

abstract class ASTNode {
    public abstract int evaluate();
    public abstract void print(String prefix);
}

class NumberNode extends ASTNode {
    private int value;

    public NumberNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    
    @Override
    public int evaluate(){
        return value;
    }

    @Override
    public void print(String prefix) {
        System.out.println(prefix + "|-- " + value);
    }
}

// 3. The Branch Nodes
class AddNode extends ASTNode {
    private ASTNode left;
    private ASTNode right;


    public AddNode(ASTNode left, ASTNode right){
        this.left = left;
        this.right = right;
    }

    public ASTNode getRight() {
        return right;
    }

    public ASTNode getLeft() {
        return left;
    }

    @Override
    public int evaluate(){
        return left.evaluate() + right.evaluate();
    }

    @Override
    public void print(String prefix){
        System.out.println(prefix + "|-- +");
        left.print(prefix + "    ");
        right.print(prefix + "    ");
    }
}
class SubtractNode extends ASTNode {
    private ASTNode left;
    private ASTNode right;


    public SubtractNode(ASTNode left, ASTNode right){
        this.left = left;
        this.right = right;
    }

    public ASTNode getRight() {
        return right;
    }

    public ASTNode getLeft() {
        return left;
    }

    @Override
    public int evaluate(){
        return left.evaluate() - right.evaluate();
    }

    @Override
    public void print(String prefix){
        System.out.println(prefix + "|-- -");
        left.print(prefix + "    ");
        right.print(prefix + "    ");
    }
}
class MultiplyNode extends ASTNode {
    private ASTNode left;
    private ASTNode right;


    public MultiplyNode(ASTNode left, ASTNode right){
        this.left = left;
        this.right = right;
    }

    public ASTNode getRight() {
        return right;
    }

    public ASTNode getLeft() {
        return left;
    }

    @Override
    public int evaluate(){
        return left.evaluate() * right.evaluate();
    }

    @Override
    public void print(String prefix){
        System.out.println(prefix + "|-- *");
        left.print(prefix + "    ");
        right.print(prefix + "    ");
    }
}
class DivideNode extends ASTNode {
    private ASTNode left;
    private ASTNode right;


    public DivideNode(ASTNode left, ASTNode right){
        this.left = left;
        this.right = right;
    }

    public ASTNode getRight() {
        return right;
    }

    public ASTNode getLeft() {
        return left;
    }

    @Override
    public int evaluate(){
        int r = right.evaluate();
        if(r==0) throw new IllegalArgumentException("division by zero");
        return left.evaluate() / r;
    }

    @Override
    public void print(String prefix){
        System.out.println(prefix + "|-- /");
        left.print(prefix + "    ");
        right.print(prefix + "    ");
    }
}

public class phase2 {
    
    public static ASTNode parseFactor(Queue<String> tokens) {
        String token = tokens.poll();
        if (token.equals("(")) {
            ASTNode node = parseArith(tokens);
            tokens.poll();
            return node;
        }
        return new NumberNode(Integer.parseInt(token));
    }

    public static ASTNode parseExpression(Queue<String> tokens) {
        ASTNode left = parseFactor(tokens);
        while (!tokens.isEmpty() && (tokens.peek().equals("*") || tokens.peek().equals("/"))) {
            String op = tokens.poll();
            ASTNode right = parseFactor(tokens);
            if (op.equals("*")) {
                left = new MultiplyNode(left, right);
            } else {
                left = new DivideNode(left, right);
            }
        }
        return left;
    }

    public static ASTNode parseArith(Queue<String> tokens) {
        ASTNode left = parseExpression(tokens);
        while (!tokens.isEmpty() && (tokens.peek().equals("+") || tokens.peek().equals("-"))) {
            String op = tokens.poll();
            ASTNode right = parseExpression(tokens);
            if (op.equals("+")) {
                left = new AddNode(left, right);
            } else {
                left = new SubtractNode(left, right);
            }
        }
        return left;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an expression (space-separated tokens): ");
        String input = scanner.nextLine();
        scanner.close();
        try {
            Queue<String> tokens = new LinkedList<>(Arrays.asList(input.split(" ")));
            ASTNode tree = parseArith(tokens);
            System.out.println("\nAST: ");
            tree.print("");
            System.out.println("\nResult = " + tree.evaluate());
            
        } catch (ArithmeticException e) {
            System.err.println("Math Error: Division by zero!");
        } catch (Exception e) {
            System.err.println("Syntax Error: Invalid expression format.");
        }
    }
}