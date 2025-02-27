package com.example.calculator2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App2 {

    static boolean exit = false;//true면 종료
    List<Double> resultBackUpList ;//계산기 클래스에서 가져온 리스트 담아줄 리스트

    public int deleteCheck(Scanner scanner) {
        System.out.println("첫번째 데이터를 삭제하겠습니까? yes 입력 시 삭제");
        String deleteText = scanner.next();
        if(deleteText.trim().equals("yes")){
            return 1;
        }
        return 0;
    }

    public void closeCheck(Scanner scanner){
        System.out.println("계속 계산하시겠습니까? 아무 입력 시 계속 : exit 입력 시 종료");
        String exitText = scanner.next();
        if(exitText.trim().equals("exit")){
            exit = true;
            scanner.close();
        }
    }
    //삭제한 데이터 다시 복구
    public void recoverData(Calculator2 calculator,Scanner scanner){
        System.out.println("데이터를 복구 하시겠습니까? yes 입력 시 복구");
        String recoverText = scanner.next();
        if(recoverText.trim().equals("yes")){
            calculator.setResultList(resultBackUpList);
            System.out.println("-----복구 완료------");
        }else{
            System.out.println("----- 복구 X ------");
        }
        System.out.println("resultBackUpList =>"+resultBackUpList);
        System.out.println(calculator.getResultList());//현재까지 저장된 데이터 보여줌
    }

    public static void main(String[] args) {
        Calculator2 calculator = new Calculator2();
        App2 app = new App2();
        app.resultBackUpList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (!exit) {
            double a = calculator.insertDoubleValue(scanner);//정수 받아오는 함수 호출
            double b = calculator.insertDoubleValue(scanner);//정수 받아오는 함수 호출
            char symbol = calculator.insertStringValue(scanner);//사칙연산 기호 받아오는 함수 호출
            double result = calculator.doOperator(symbol,a,b);//계산 함수 호출
            if(!Double.isNaN(result)){//계산 실패한 값 저장 x
                app.resultBackUpList.add(result);//백업용 데이터 저장
            }
            //데이터를 삭제 할 건지 체크함
            if(app.deleteCheck(scanner) == 1){
                calculator.deleteResult();
            }
            app.recoverData(calculator,scanner); //데이터를 복구 할 건지 체크함
            app.closeCheck(scanner);//종료 체크
        }
    }
}
