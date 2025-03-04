package com.example.calculator3;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Calculator3<T extends Number>{

    private List<T> resultList = new ArrayList<>();// 현재 결과값
    private List<T> resultBackUpList = new ArrayList<>() ;//백업 결과값

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

    }

    public void doOperator(char symbol,int a,int b){
        Number result = 0;
        boolean isSave = true;
        switch (symbol){
            case '+': result = Operation.PLUS.getOperation(a,b);
                break;
            case '-': result = Operation.SUBTRACT.getOperation(a,b);
                break;
            case '*': result = Operation.MULTIPLY.getOperation(a,b);
                break;
            case '/': if(a == 0 || b == 0){
                isSave = false;
                System.out.println("0 으로는 나눌 수 없어요");
            }else{
                result = (double)Math.round(Operation.DIVIDE.getOperation(a,b) * 100) /100;//소수2자리까지 반올림
            }
                break;
        }
        if(isSave){//계산이 성공했을때만 저장해줌
            resultList.add((T)result);
            resultBackUpList.add((T)result);//백업용 데이터 저장
            System.out.println("결과 : " + result);
        }
    }

    public int insertIntValue(Scanner scanner){
        int num = 0;
        try{
            System.out.println("정수를 입력하세요");
            num = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("숫자를 입력하세요");
            scanner.nextLine();//scanner 초기화
            return insertIntValue(scanner);
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

    //가장 먼저 저장된 데이터 삭제
    public void deleteResult(){
        if(!resultList.isEmpty()){//데이터가 존재하면 삭제해줌
            resultList.remove(0);
            System.out.println("-----삭제완료-----");
        }else{
            System.out.println("---삭제 할 데이터가 없습니다!---");
        }
    }

    //Getter
    public List<T> getResultList() {
        return resultList;
    }
    //Setter
    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }
    public List<T> getResultBackUpList() {
        return resultBackUpList;
    }

}
