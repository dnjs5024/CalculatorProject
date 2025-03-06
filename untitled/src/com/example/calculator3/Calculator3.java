package com.example.calculator3;


import java.util.*;
import java.util.stream.Collectors;

public class Calculator3<T extends Number> implements MyCalculatorFunction,AdditionalCalculatorFunction{

    private List<T> resultList = new ArrayList<>();// 현재 결과값
    private List<T> resultBackUpList = new ArrayList<>() ;//백업 결과값

    @Override
    public void doOperator(char symbol,double a,double b){
        Number result = 0;
        boolean isSave = true;
        switch (symbol){
            case '+': result = Operation.PLUS.getOperation(a,b);
                break;
            case '-': result = Operation.SUBTRACT.getOperation(a,b);
                break;
            case '*': result = Operation.MULTIPLY.getOperation(a,b);
                break;
            case '/': if(b == 0){
                isSave = false;
                System.out.println("0 으로는 나눌 수 없어요");
            }else{
                result = (double)Math.round(Operation.DIVIDE.getOperation(a,b) * 100) /100;//소수2자리까지 반올림
            }
                break;
        }
        if(isSave){//계산이 성공했을때만 저장해줌
            saveResult((T) result);
        }
    }

    //성공한 계산 결과 값 저장
    @Override
    public void saveResult(Number result){
        resultList.add((T)result);
        resultBackUpList.add((T)result);//백업용 데이터 저장
        System.out.println("결과 : " + result);
    }

    //현재 계산 결과보다 큰 값 있는지 비교
    @Override
    public List<String> comepareResultNum(double result){
        return resultList.stream()
                .filter(resultItem -> (double)resultItem > result )
                .map(resultItem -> resultItem+"")//String 변환
                .collect(Collectors.toList());
    }

    //정수입력검사메소드
    @Override
    public double insertDoubleValue(Scanner scanner){
        double num = 0;
        try{
            System.out.println("정수를 입력하세요");
            num = scanner.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("올바른 정수를 입력하세요");
            scanner.nextLine();//scanner 초기화
            return insertDoubleValue(scanner);
        }
        return num;
    }
    //사칙연산입력검사메소드
    @Override
    public char insertStringValue(Scanner scanner) {
        while (true) { // 올바른 입력을 받을 때까지 반복
            System.out.println("사칙연산 기호를 입력하세요 { *, /, +, - } 중에서 입력:");
            char symbol = scanner.next().charAt(0);
            // 입력한 기호 옳은 값 인지 체크
            if (Operation.compareSymbol(symbol) != null) {
                return Operation.compareSymbol(symbol).charAt(0);
            }
            System.out.println("사칙연산를 입력하세요");
        }
    }

    //가장 먼저 저장된 데이터 삭제
    @Override
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
