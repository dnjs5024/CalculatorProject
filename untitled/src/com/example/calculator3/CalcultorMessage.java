package com.example.calculator3;

import java.util.List;
import java.util.Scanner;

public class CalcultorMessage {

    private boolean exit = false;//true면 종료

    // 입력한 결과보다 저장된 값들 중 더 큰 결과 값이 있으면 출력해줌
    public void compareResult(Scanner scanner,Calculator3<Number> calculator3){
        System.out.println("정수를 입력하고 더 큰 결과값이 있는지 존재하는지 확인하겠습니까? y 입력시 비교");
        if(scanner.next().equals("y")){
            double result = calculator3.insertDoubleValue(scanner);
            List<String> resultMessage = calculator3.comepareResultNum((double) result);//List로 변환
            if(resultMessage.isEmpty()){
                System.out.println("현재 계산결과보다 더 큰 값이 없습니다.");
            }else{
                resultMessage.stream().forEach(item -> System.out.println("더 큰 값은 :"+ item +" 입니다."));
            }
        }
        System.out.println("-----------------");
    }

    //데이터 삭제할지
    public boolean deleteCheck(Scanner scanner) {
        System.out.println("첫번째 데이터를 삭제하겠습니까? y 입력 시 삭제");
        String deleteText = scanner.next();
        if(deleteText.trim().equals("y")){
            return true;
        }
        return false;
    }
    //계속 계산할지 질문
    public boolean closeCheck(Scanner scanner){
        System.out.println("계속 계산하시겠습니까? 아무 입력 시 계속 : exit 입력 시 종료");
        String exitText = scanner.next();
        if(exitText.trim().equals("exit")){
            exit = true;
            System.out.println("----- 종료 ------");
            scanner.close();
        }
        return exit;
    }
    //삭제한 데이터 다시 복구
    public <T> void recoverData(Calculator3 calculator, Scanner scanner , List<T> resultBackUpList){
        System.out.println("데이터를 복구 하시겠습니까? y 입력 시 복구");
        String recoverText = scanner.next();
        if(recoverText.trim().equals("y")){
            calculator.setResultList(resultBackUpList);
            System.out.println("-----복구 완료------");
        }else{
            System.out.println("----- 복구 X ------");
        }
        System.out.println("resultBackUpList =>"+resultBackUpList);
        System.out.println("resultList =>"+calculator.getResultList());//현재까지 저장된 데이터 보여줌
        System.out.println("-----------------");
    }
}