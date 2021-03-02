package com.tsystems.javaschool.tasks.calculator.operation;


import com.tsystems.javaschool.tasks.calculator.exceptions.CalculatorException;

public class OperationUnrealized extends CalculatorException {

    public OperationUnrealized() {
        this("Operation unrealized");
    }

    private OperationUnrealized(String message) {
        super(message);
    }
}
