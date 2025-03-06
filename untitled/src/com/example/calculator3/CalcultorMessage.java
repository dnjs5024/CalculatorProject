package com.example.calculator3;

import java.util.List;
import java.util.Scanner;

public class CalcultorMessage {

    private boolean exit = false;//true면 종료

    public int deleteCheck(Scanner scanner) {
        System.out.println("첫번째 데이터를 삭제하겠습니까? yes 입력 시 삭제");
        String deleteText = scanner.next();
        if(deleteText.trim().equals("yes")){
            return 1;
        }
        return 0;
    }

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
        System.out.println("데이터를 복구 하시겠습니까? yes 입력 시 복구");
        String recoverText = scanner.next();
        if(recoverText.trim().equals("yes")){
            calculator.setResultList(resultBackUpList);
            System.out.println("-----복구 완료------");
        }else{
            System.out.println("----- 복구 X ------");
        }
        System.out.println("resultBackUpList =>"+resultBackUpList);
        System.out.println("resultList =>"+calculator.getResultList());//현재까지 저장된 데이터 보여줌
    }
}