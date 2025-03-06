package com.example.calculator3;

import java.util.Arrays;

enum Operation{
    PLUS("+",(a,b)->a+b),
    SUBTRACT("-",(a,b)->a-b),
    MULTIPLY("*",(a,b)->a*b),
    DIVIDE("/",(a,b)->a/b);

    private  CalculatorOperation calculatorOperation;
    private  String symbol;


    private Operation(String symbol,CalculatorOperation calculatorOperation){
        this.calculatorOperation = calculatorOperation;
        this.symbol = symbol;
    }
    public double getOperation(double a, double b){
        return calculatorOperation.setOperation(a,b);
    }

    public String getSymbol() {
        return symbol;
    }

    public static String compareSymbol(char symbol){
        return Arrays.stream(Operation.values())
                .filter(op -> op.getSymbol().equals(String.valueOf(symbol)))
                .map(Operation::getSymbol)//Operation > String 으로
                .findFirst()
                .orElse(null);
    }

}