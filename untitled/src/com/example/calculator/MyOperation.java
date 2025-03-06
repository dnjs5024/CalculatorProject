package com.example.calculator;

// enum
public enum MyOperation {
    SUBTRACT("-",(a,b)-> a-b),
    MULTIPLY("*",(a,b)-> a*b),
    DIVIDE("/",(a,b)-> a/b),
    PLUS("+",(a,b)-> a+b);

    ArithmeticOperation arithmeticOperation;

    String symbol;

    private MyOperation(String symbol, ArithmeticOperation arithmeticOperation) {
        this.symbol = symbol;
        this.arithmeticOperation = arithmeticOperation;
    }
    public String getSymbol() {
        return symbol;
    }

    public  double setCalculate(double a,double b){
        return arithmeticOperation.calculatorOperation(a,b);
    }
}