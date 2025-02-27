package com.example.calculator1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator1 {

    public void doCalculate(char symbol,double a,double b){
        switch (symbol){
            case '+': System.out.println("결과값은 : " + (a + b) + "입니다");
                break;
            case '-':  System.out.println("결과값은 : " + (a - b) + "입니다");
                break;
            case '*':  System.out.println("결과값은 : " + (a * b) + "입니다");
                break;
            case '/': if(a == 0 || b == 0){
                System.out.println("0 으로는 나눌 수 없어요");
            }else{
                System.out.println("결과값은 : " + (a / b) + "입니다");
            }
                break;
            default: System.out.println("사칙연산 기호를 넣어주세요");
                break;
        }
    }

    public double insertDoubleValue(Scanner scanner){
        double num = 0;
        try{
            System.out.println("정수를 입력하세요");
            num = scanner.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("숫자를 입력하세요");
            scanner.nextLine();//scanner 초기화
            return insertDoubleValue(scanner);
        }
        return num;
    }

    public char insertStringValue(Scanner scanner){
        System.out.println("사칙연산 기호를 입력하세요 { * , / , + , - }중에서 입력");
        char symbol = scanner.next().charAt(0);
        if(symbol == '*' || symbol == '/' || symbol == '+' || symbol == '-'){
            return symbol;
        }else{
            System.out.println("사칙연산를 입력하세요");
            return insertStringValue(scanner);
        }
    }
    public static void main(String[] args) {
            Calculator1 calculator = new Calculator1();
            Scanner scanner = new Scanner(System.in);
            boolean exit = false;//true면 종료
            while (!exit) {
                double a = calculator.insertDoubleValue(scanner);//정수 받아오는 함수 호출
                double b = calculator.insertDoubleValue(scanner);//정수 받아오는 함수 호출
                char symbol = calculator.insertStringValue(scanner);//사칙연산 기호 받아오는 함수 호출
                calculator.doCalculate(symbol,a,b);//계산 함수 호출
                System.out.println("계속 계산하시겠습니까? 아무 입력 시 계속 : exit 입력 시 종료");
                String exitText = scanner.next();
                if(exitText.trim().equals("exit")){
                    exit = true;
                    scanner.close();
                }
            }
    }
}
