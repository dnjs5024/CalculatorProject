package com.example.calculator2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Calculator2 {

    private List<Double> resultList = new ArrayList<>();

    public double doOperator(char symbol,double a,double b){
        double resurt = 0;
        boolean isSave = true;
        switch (symbol){
            case '+': resurt = a + b;
                break;
            case '-': resurt = a - b;
                break;
            case '*': resurt = a * b;
                break;
            case '/': if(a == 0 || b == 0){
                isSave = false;
                System.out.println("0 으로는 나눌 수 없어요");
                return Double.NaN;
            }else{
                resurt = a / b;
            }
                break;
        }
        if(isSave){//계산이 성공했을때만 저장해줌
            resultList.add(resurt);
        }
        return resurt;
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
    public List<Double> getResultList() {
        return resultList;
    }
    //Setter
    public void setResultList(List<Double> resultList) {
        this.resultList = resultList;
    }

    public static void main(String[] args) {
        Calculator2 calculator = new Calculator2();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;//true면 종료
        while (!exit) {
            double a = calculator.insertDoubleValue(scanner);//정수 받아오는 함수 호출
            double b = calculator.insertDoubleValue(scanner);//정수 받아오는 함수 호출
            char symbol = calculator.insertStringValue(scanner);//사칙연산 기호 받아오는 함수 호출
            double resurt = calculator.doOperator(symbol,a,b);//계산 함수 호출
            System.out.println("결과값은 : " + resurt + "입니다");
            System.out.println("계속 계산하시겠습니까? 아무 입력 시 계속 : exit 입력 시 종료");
            String exitText = scanner.next();
            if(exitText.trim().equals("exit")){
                exit = true;
                scanner.close();
            }
        }
    }
}
