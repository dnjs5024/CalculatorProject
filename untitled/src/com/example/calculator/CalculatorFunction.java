package com.example.calculator;

public interface CalculatorFunction {

    public void setCalculatorScreen();// 계산기 화면에 그려주는 함수
    public void doCalculate(String command);//사칙연산 함수를 호출해서 계산해줄 함수
}
