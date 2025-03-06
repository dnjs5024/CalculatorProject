package com.example.calculator3;

import java.util.List;
import java.util.Scanner;

public class App3<T> {

    private boolean exit = false;//true면 종료

    List<T> resultBackUpList ;//계산기 클래스에서 가져온 리스트 담아줄 리스트

    public static void main(String[] args) {
        Calculator3<Number> calculator = new Calculator3<>();//계산기 기능 제공 클래스
        CalcultorMessage calcultorMessage = new CalcultorMessage();//메시지 제공 객체
        App3<Number> app = new App3<>();
        app.resultBackUpList = calculator.getResultBackUpList();
        Scanner scanner = new Scanner(System.in);
        while (!app.exit) {
            int a = calculator.insertIntValue(scanner);//정수 받아오는 함수 호출
            int b = calculator.insertIntValue(scanner);//정수 받아오는 함수 호출
            char symbol = calculator.insertStringValue(scanner);//사칙연산 기호 받아오는 함수 호출5
            calculator.doOperator(symbol,a,b);//계산 함수 호출
            //데이터를 삭제 할 건지 체크함
            if(calcultorMessage.deleteCheck(scanner) == 1){
                calculator.deleteResult();
            }
            calcultorMessage.recoverData(calculator,scanner,app.resultBackUpList); //데이터를 복구 할 건지 체크함
            app.exit = calcultorMessage.closeCheck(scanner);//종료 체크
        }
    }
}
