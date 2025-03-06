package com.example.calculator3;

import java.util.Scanner;

public interface MyCalculatorFunction {
    public void doOperator(char symbol,int a,int b);
    public int insertIntValue(Scanner scanner);
    public char insertStringValue(Scanner scanner);
}
