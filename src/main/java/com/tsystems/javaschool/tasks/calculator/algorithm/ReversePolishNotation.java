package com.tsystems.javaschool.tasks.calculator.algorithm;

import com.tsystems.javaschool.tasks.calculator.exceptions.CalculatorException;
import com.tsystems.javaschool.tasks.calculator.operation.Operation;

import java.util.Stack;

public class ReversePolishNotation {

    private final char[] input;

    public ReversePolishNotation(String input) {
        validateInput(input);
        this.input = input.toCharArray();
    }

    public String rpn() {
        Stack<Character> operations = new Stack<>();
        StringBuilder result = new StringBuilder();
        boolean hasOperation = false;
        int bracketCount = 0;

        for (char symbol : input) {
            if (Character.isWhitespace(symbol)) {
                continue;
            }
            if (Character.isDigit(symbol) || symbol == '.') {
                result.append(symbol);
                hasOperation = false;
                continue;
            } else {
                if (result.length() > 0 && !Character.isWhitespace(result.charAt(result.length() - 1))) {
                    result.append(' ');
                }
            }
            if (Operation.isOperation(symbol)) {
                if (Operation.isLeftBracket(symbol)) {
                    operations.push(symbol);
                    hasOperation = false;
                    bracketCount++;
                } else if (Operation.isRightBracket(symbol)) {
                    if (hasOperation) {
                        return null;
                    } else {
                        char c;
                        while ((c = operations.pop()) != Operation.LEFT_BRACKET.getSymbol() && bracketCount > 0) {
                            result.append(c).append(' ');
                        }
                    }
                    bracketCount--;
                } else {
                    if (result.length() <= 0) {
                        return null;
                    }

                    if (!hasOperation) {
                        hasOperation = true;
                        while (Operation.getPriority(symbol) <= Operation.getPriority(operations)) {
                            result.append(operations.pop()).append(' ');
                        }
                        if (Operation.getPriority(symbol) > Operation.getPriority(operations)) {
                            operations.push(symbol);
                        }
                    }
                }
            } else {
                return null;
            }
        }
        while (!operations.isEmpty()) {
            if (!Character.isWhitespace(result.charAt(result.length() - 1))) {
                result.append(' ');
            }
            final Character ch = operations.pop();
            if (Operation.isBracket(ch)) {
                return null;
            }
            result.append(ch);
        }
        return result.toString();
    }


    protected void validateInput(String input) {
        if (input.isEmpty()) {
            throw new CalculatorException("Строка пуста!");
        }
        final char firstSymbol = input.charAt(0);
        if ((!Character.isDigit(firstSymbol)) && firstSymbol != Operation.LEFT_BRACKET.getSymbol() && !Character.isWhitespace(firstSymbol)) {
            throw new CalculatorException("Неверный первый символ:[" + firstSymbol + "]");
        }
    }

}
