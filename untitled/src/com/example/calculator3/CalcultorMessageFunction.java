package com.example.calculator3;

import java.util.List;
import java.util.Scanner;

public interface CalcultorMessageFunction {
    // 입력한 결과보다 저장된 값들 중 더 큰 결과 값이 있으면 출력할지 입력받는 메소드
    public void compareResult(Scanner scanner, Calculator3<Number> calculator3);
    //데이터 삭제할지 입력받는 메소드
    public boolean deleteCheck(Scanner scanner);
    //계속 계산할지 입력받는 메소드
    public boolean closeCheck(Scanner scanner);
    //삭제한 데이터 다시 복구할지 입력받는 메소드
    public <T> void recoverData(Calculator3 calculator, Scanner scanner , List<T> resultBackUpList);
}
