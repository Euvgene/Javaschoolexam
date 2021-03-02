package com.tsystems.javaschool.tasks.calculator.algorithm;


import com.tsystems.javaschool.tasks.calculator.operation.Operation;

import java.text.DecimalFormat;
import java.util.Stack;
import java.util.StringTokenizer;

import static com.tsystems.javaschool.tasks.calculator.operation.Operation.isOperation;


public class SimpleCalculator {
    public String evaluate(String expression) {
        String result;
        Stack<Double> stack = new Stack<>();

        try {
            String rpn = new ReversePolishNotation(expression).rpn();
            if (rpn.replaceAll("\\s", "").length() == expression.replaceAll("[\\[\\](){}]", "").length()) {
                double d1;
                double pop1;
                double pop2;

                StringTokenizer tokenizer = new StringTokenizer(rpn);

                String token;

                while (tokenizer.hasMoreTokens()) {

                    token = tokenizer.nextToken().trim();
                    if (isNumber(token)) {
                        stack.push(Double.parseDouble(token));
                    } else if (stack.size() > 1) {
                        pop1 = stack.pop();
                        pop2 = stack.pop();
                        d1 = isOperation(token) ? Operation.find(token.charAt(0)).perform(pop2, pop1) : Double.parseDouble(token);
                        stack.push(d1);
                    } else {
                        return null;
                    }
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
        double finalRes = stack.pop();
        if (finalRes % 1 == 0) {
            DecimalFormat decimalFormat = new DecimalFormat("#");
            result = decimalFormat.format(finalRes);
            return result;
        } else {
            DecimalFormat decimalFormat = new DecimalFormat("#.####");
            result = decimalFormat.format(finalRes);
            return result.replace(",", ".");
        }


    }

    private boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
