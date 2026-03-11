package Java;

import java.util.*;

public class phase3 {
    public static String toPrefixString(ASTNode node) {
        if(node instanceof NumberNode){
            NumberNode n = (NumberNode) node;
            return String.valueOf(n.getValue());
        }
        else if (node instanceof AddNode) {
            AddNode n = (AddNode) node;
            return "(+ " + toPrefixString(n.getLeft()) + " " + toPrefixString(n.getRight()) + ")";
        }
        else if (node instanceof SubtractNode) {
            SubtractNode n = (SubtractNode) node;
            return "(- " + toPrefixString(n.getLeft()) + " " + toPrefixString(n.getRight()) + ")";
        }
        else if (node instanceof MultiplyNode) {
            MultiplyNode n = (MultiplyNode) node;
            return "(* " + toPrefixString(n.getLeft()) + " " + toPrefixString(n.getRight()) + ")";
        }
        else if (node instanceof DivideNode) {
            DivideNode n = (DivideNode) node;
            return "(/ " + toPrefixString(n.getLeft()) + " " + toPrefixString(n.getRight()) + ")";
        }
        return "";
    }
    public static int evaluatePrefix(Queue<String> tokens) {
        String token = tokens.poll();
        if(token.equals("+")){
            return evaluatePrefix(tokens) + evaluatePrefix(tokens);
        }
        else if(token.equals("-")){
            return evaluatePrefix(tokens) - evaluatePrefix(tokens);
        }
        else if(token.equals("*")){
            return evaluatePrefix(tokens) * evaluatePrefix(tokens);
        }
        else if(token.equals("/")){
            return evaluatePrefix(tokens) / evaluatePrefix(tokens);
        }
        return Integer.parseInt(token);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an expression (space-separated tokens): ");
        String input = scanner.nextLine();
        scanner.close();
        try {
            Queue<String> tokens = new LinkedList<>(Arrays.asList(input.split(" ")));
            ASTNode tree = phase2.parseArith(tokens);

            String prefixForm = toPrefixString(tree);
            System.out.println("\nPrefix Form:");
            System.out.println(prefixForm);

            String exp = prefixForm.replace("(", "").replace(")", "").trim();
            Queue<String> prefixTokens = new LinkedList<>(Arrays.asList(exp.split("\\s+")));
            
            int result = evaluatePrefix(prefixTokens);
            System.out.println("\nFinal Result:");
            System.out.println(result);
            
        } catch (ArithmeticException e) {
            System.err.println("Math Error: Division by zero!");
        } catch (Exception e) {
            System.err.println("Syntax Error: Invalid expression format.");
        }
    }
}
