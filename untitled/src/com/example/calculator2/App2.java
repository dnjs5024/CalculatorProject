package com.example.calculator2;

import java.util.List;
import java.util.Scanner;

public class App2 {

    boolean exit = false;//true면 종료
    List<Number> resultBackUpList ;//계산기 클래스에서 가져온 리스트 담아줄 리스트

    public static void main(String[] args) {
        Calculator2 calculator = new Calculator2();
        CalcultorMessage2 calcultorMessage2 = new CalcultorMessage2();
        App2 app = new App2();
        app.resultBackUpList = calculator.getResultBackUpList();
        Scanner scanner = new Scanner(System.in);
        while (!app.exit) {
            int a = calculator.insertIntValue(scanner);//정수 받아오는 함수 호출
            int b = calculator.insertIntValue(scanner);//정수 받아오는 함수 호출
            char symbol = calculator.insertStringValue(scanner);//사칙연산 기호 받아오는 함수 호출
            calculator.doOperator(symbol,a,b);//계산 함수 호출
            //데이터를 삭제 할 건지 체크함
            if(calcultorMessage2.deleteCheck(scanner) == 1){
                calculator.deleteResult();
            }
            calcultorMessage2.recoverData(calculator,scanner,app.resultBackUpList); //데이터를 복구 할 건지 체크함
            app.exit = calcultorMessage2.closeCheck(scanner);//종료 체크
        }
    }
}
