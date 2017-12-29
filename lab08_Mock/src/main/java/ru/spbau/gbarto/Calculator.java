package ru.spbau.gbarto;

import org.jetbrains.annotations.NotNull;

/**
 * Class that represents functions for evaluating expressions written in normal or reverse polish notation.
 */
public class Calculator {
    private Stack<Character> operators;
    private Stack<Double> values;

    /**
     * Constructs new calculator, using given stacks.
     * @param operators stack for storing operators
     * @param values stack for storing values
     */
    public Calculator(@NotNull Stack<Character> operators, @NotNull Stack<Double> values) {
        this.operators = operators;
        this.values = values;
    }

    /**
     * Transforms expression into reverse polish notation.
     * @param expr expression in normal form
     * @return reverse polish notation
     */
    public String getReversePolishNotation(String expr) {
        StringBuilder res = new StringBuilder();
        for (char c : expr.toCharArray()) {
            if (c == '(') {
                operators.push(c);
                continue;
            }
            if (c == ')') {
                while (!operators.isEmpty() && operators.getTop() != '(') {
                    res.append(operators.pop());
                }
                operators.pop();
                continue;
            }
            if (Character.isDigit(c))
            {
                res.append(c);
                continue;
            }
            while (!operators.isEmpty() && getPriority(c) <= getPriority(operators.getTop())) {
                res.append(operators.pop());
            }
            operators.push(c);
        }
        while (!operators.isEmpty()) {
            res.append(operators.pop());
        }

        return res.toString();
    }

    /**
     * Calculates result of expression in reverse polish notation.
     * @param expr expression in reverse polish notation
     * @return result of expression
     */
    public double calculateExpression(@NotNull String expr) {
        values.clear();

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            if (Character.isDigit(c)) {
                values.push((double)(c - '0'));
            } else {
                double b = values.pop();
                double a = values.pop();
                values.push(apply(c, a, b));
            }
        }

        return values.pop();
    }

    private int getPriority(char operation) {
        switch (operation) {
            case '+' : return 1;
            case '-' : return 1;
            case '*' : return 2;
            case '/' : return 2;
            default  : return 0;
        }
    }

    private double apply(char operation, double a, double b) {
        switch (operation) {
            case '+' : return a + b;
            case '-' : return a - b;
            case '*' : return a * b;
            case '/' : return a / b;
        }

        throw new UnsupportedOperationException();
    }
}
