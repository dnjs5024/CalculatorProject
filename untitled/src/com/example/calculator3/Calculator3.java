package com.example.calculator3;


import java.util.*;
import java.util.stream.Collectors;

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

        public String getSymbol() {
            return symbol;
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
            // 현재 계산 결과보다 저장된 값들 중 더 큰 결과 값이 있으면 출력해줌
            double finalResult = (double) result;//람다에서 사용하기 위해 변수 선언
            List<String> resultMessage = resultList.stream()
             .filter(resultItem -> (double)resultItem > finalResult )
             .map(resultItem -> resultItem+"")//String 변환
             .collect(Collectors.toList());//List로 변환
            if(resultMessage.isEmpty()){
                System.out.println("현재 계산결과보다 더 큰 값이 없습니다.");
            }else{
                resultMessage.stream().forEach(item -> System.out.println("더 큰 값은 :"+ item +" 입니다."));
            }

        }
    }

    public int insertIntValue(Scanner scanner){
        int num = 0;
        while(true){
        try{
            System.out.println("정수를 입력하세요");
            num = scanner.nextInt();
            break;
        } catch (InputMismatchException e) {
            System.out.println("올바른 정수를 입력하세요");
            scanner.nextLine();//scanner 초기화
            return insertIntValue(scanner);
        }
        }
        return num;
    }

    public static char insertStringValue(Scanner scanner) {
        while (true) { // 올바른 입력을 받을 때까지 반복
            System.out.println("사칙연산 기호를 입력하세요 { *, /, +, - } 중에서 입력:");
            char symbol = scanner.next().charAt(0);
            String result = Arrays.stream(Operation.values())
                    .filter(op -> op.getSymbol().equals(String.valueOf(symbol)))
                    .map(Operation::getSymbol)//Operation > String 으로
                    .findFirst()
                    .orElse(null); // 존재하지 않으면 null 반환
            if (result != null) {
                return result.charAt(0);
            }
            System.out.println("사칙연산를 입력하세요");
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
