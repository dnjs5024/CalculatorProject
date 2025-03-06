package com.example.calculator3;

import java.util.Scanner;

public interface MyCalculatorFunction {
    public void doOperator(char symbol,double a,double b);
    public double insertDoubleValue(Scanner scanner);
    public char insertStringValue(Scanner scanner);
}
