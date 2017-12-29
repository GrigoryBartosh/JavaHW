package ru.spbau.gbarto;

/**
 * Work with the console.
 * Reads math expressions and calculates them.
 */
public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No arguments");
            return;
        }
        Calculator calculator = new Calculator(new Stack<>(), new Stack<>());
        System.out.print(args[0] + " = ");
        System.out.println(calculator.calculateExpression(calculator.getReversePolishNotation(args[0])));
    }
}
