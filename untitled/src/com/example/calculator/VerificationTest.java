package com.example.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VerificationTest {

    // 입력값 숫자만 가능하게
    public static void dataVerification(){

            Scanner scanner = new Scanner(System.in);
            char c = scanner.next().charAt(0);
            if(45 <= c && c <= 57){ // - , . / ,0 ~ 9
                System.out.println("굳");
                System.out.println(c);
            }else if(c == 61 || c == 42 || c == 43){ // 61 : = , 42 : * , 43 : +
                System.out.println("굳");
            }else{
                System.out.println("뭐꼬 나가");
                System.out.println(c);
            }
    }

    public static void main(String[] args) {
        System.out.println(5.0-3.0);
    }
}
