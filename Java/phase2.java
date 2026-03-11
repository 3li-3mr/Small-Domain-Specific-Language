package Java;

import java.util.Scanner;

abstract class ASTNode {
    public abstract int evaluate();
    public abstract void print(String prefix);
}

class NumberNode extends ASTNode {
    private int value;

    public NumberNode(int value) {
        this.value = value;
    }
    
    @Override
    public int evaluate(){
        return value;
    }

    @Override
    public void print(String prefix){
        System.out.println(prefix + value);
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

    @Override
    public int evaluate(){
        return left.evaluate() + right.evaluate();
    }

    @Override
    public void print(String prefix){
        System.out.println(prefix + '+');
        left.print(prefix + " |--");
        right.print(prefix + " \\--");
    }
}
class SubtractNode extends ASTNode {
    private ASTNode left;
    private ASTNode right;


    public SubtractNode(ASTNode left, ASTNode right){
        this.left = left;
        this.right = right;
    }

    @Override
    public int evaluate(){
        return left.evaluate() - right.evaluate();
    }

    @Override
    public void print(String prefix){
        System.out.println(prefix + '-');
        left.print(prefix + " |--");
        right.print(prefix + " \\--");
    }
}
class MultiplyNode extends ASTNode {
    private ASTNode left;
    private ASTNode right;


    public MultiplyNode(ASTNode left, ASTNode right){
        this.left = left;
        this.right = right;
    }

    @Override
    public int evaluate(){
        return left.evaluate() * right.evaluate();
    }

    @Override
    public void print(String prefix){
        System.out.println(prefix + '*');
        left.print(prefix + " |--");
        right.print(prefix + " \\--");
    }
}
class DivideNode extends ASTNode {
    private ASTNode left;
    private ASTNode right;


    public DivideNode(ASTNode left, ASTNode right){
        this.left = left;
        this.right = right;
    }

    @Override
    public int evaluate(){
        int r = right.evaluate();
        if(r==0) throw new IllegalArgumentException("division by zero");
        return left.evaluate() / right.evaluate();
    }

    @Override
    public void print(String prefix){
        System.out.println(prefix + '/');
        left.print(prefix + " |--");
        right.print(prefix + " \\--");
    }
}

public class phase2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an expression: ");
        String input = scanner.nextLine();
        scanner.close();
        String[] tokens = input.split(" ");
        // parse to AST

        // tree.print("")

        // tree.evaluate()
    }
}
