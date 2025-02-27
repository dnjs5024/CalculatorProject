package com.example.calculator;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculator  {

    List<String> symbolList = new ArrayList<>();
    String[][] btn = {{"%","CE","C","지우기"},{" "," "," ","/"},{"7","8","9","*"},{"4","5","6","-"},{"1","2","3","+"},{"+,-","0",".","="},};
    JTextField jTextField;
    //Enum
    enum Operation {
        SUBTRACT("-",(a,b)-> a-b),
        MULTIPLY("*",(a,b)-> a*b),
        DIVIDE("/",(a,b)-> a/b),
        PLUS("+",(a,b)-> a+b),;

        CalculatorOperation calcOperation;

        String symbol;

        private Operation(String symbol,CalculatorOperation calcOperation) {
            this.symbol = symbol;
            this.calcOperation = calcOperation;
        }

        public double  setCalculate(double a,double b){
            return calcOperation.calculatorOperation(a,b);
        }
    }

    public Calculator(){
        // 계산기 메인 창 생성
        JFrame frame = new JFrame("계산기");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // 계산기 계산식 나오는 UI Text 필드
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4; // 차지하는 열의 개수
        gbc.gridheight = 1; // 차지하는 행의 개수
        gbc.weightx = 1.0; // x 방향으로 여백을 얼마나 차지할지
        gbc.weighty = 1.0; // y 방향으로 여백을 얼마나 차지할지
        gbc.fill = GridBagConstraints.BOTH;
        jTextField = new JTextField();//텍스트 필드
        jTextField.setFont(new Font("Serif", Font.BOLD, 40));//글꼴
        jTextField.setHorizontalAlignment(JTextField.RIGHT);
        frame.add(jTextField, gbc);

        // 실시간 입력 감지 이벤트
        jTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                System.out.println("insertUpdate() ==> " + jTextField.getText());
                dataVerification();//올바른 데이터인지 체크
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                System.out.println("removeUpdate() ==> " + jTextField.getText());
            }
            @Override
            public void changedUpdate(DocumentEvent e) { //사용 x 강제 구현
            }
        });

        // Enter 키 입력 이벤트 처리
        jTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = jTextField.getText();
                doCalculate(inputText);//계산하는 함수 호출
            }
        });

        // 버튼 클릭 시 이벤트 리스너, 버튼 텍스트 가져옴
        ActionListener listener = (e) -> {
            String getText = jTextField.getText();// 화면에 보이는 계산식
            String btnText = e.getActionCommand();// 입력받은 텍스트
            if(btnText.equals("=")){
                doCalculate(getText);//계산하는 함수 호출
            }else if(btnText.equals("지우기")){
                System.out.println("ActionListener ==> 리셋이요");
                jTextField.setText("");
            }else{
                getText += btnText;// 계산식에 입력받은 텍스트 추가
                jTextField.setText(getText);
            }
        };

        // btn배열에서 데이터 가져와서 버튼 UI  생성
        gbc.gridwidth = 1; // 차지하는 열의 개수
        gbc.gridheight = 1; // 차지하는 행의 개수
        gbc.weightx = 1.0; // x 방향으로 여백을 얼마나 차지할지
        gbc.weighty = 1.0; // y 방향으로 여백을 얼마나 차지할지
        for(int i = 0; i < btn.length; i++){
            gbc.gridy = i + 1; // 열 위치
            for(int j = 0; j < btn[i].length; j++){
                JButton button = new JButton(btn[i][j]);
                gbc.gridx = j; // 행 위치
                button.addActionListener(listener);
                frame.add(button, gbc);
            }
        }
        frame.setVisible(true);//프레임 출력
    }


    // 입력값 숫자와 사칙연산 기호만 가능하게 검사
    public void dataVerification(){
        String getText = jTextField.getText().trim();
        symbolList.clear();
        if(!getText.trim().isEmpty()){
            char[] chars = getText.toCharArray();
            System.out.println("chars length => " + chars.length);
            int cnt = 0;// 입력한 문자열에서 자리수 기억하기 위한 변수
            boolean isSymbol = false;// 기호가 들어갔으면 true
            for(char c : chars){
                System.out.println("chars => " + c);
                if(45 <= c && c <= 47){ // 45 : - , 46 : . 47 : /
                    if(isSymbol){
                        symbolList.set(symbolList.size()-1, String.valueOf(c));//앞에 입력한 기호 바꿔줌
                        updateText("change", c);//앞에 기호바꿔서 화면에 교체
                    }else{
                        symbolList.add(String.valueOf(c));
                    }
                    isSymbol = true;
                }else if(48 <= c && c <= 57){// 0 ~ 9
                    System.out.println("숫자입력 :" + c);
                    isSymbol = false;
                }else if(c == 61 || c == 42 || c == 43){ // 61 : = , 42 : * , 43 : +
                    if(c == 61){// = 이면 true
                        String inputText = jTextField.getText();// 화면에 보이는 계산식 가져옴
                        isSymbol = false;//초기화 테스트
                        doCalculate(inputText);//계산하는 함수 호출
                    }else{
                        if(isSymbol){
                            symbolList.set(symbolList.size()-1, String.valueOf(c));//앞에 입력한 기호 바꿔줌
                            updateText("change", c);//앞에 기호바꿔서 화면에 교체
                        }else{
                            symbolList.add(String.valueOf(c));
                        }
                        isSymbol = true;
                    }
                }else{
                    System.out.println("입력이 잘못되었습니다.");
                    updateText("reset", ' ');
                    break;
                }
                cnt++;
            }
        }
    }

    //계산하는 함수
    public void doCalculate(String command){
        String regex = "((?<=\\+)|(?=\\+)|(?<=\\*)|(?=\\*)|(?<=-)|(?=-)|(?<=/)|(?=/))";
        String[] strings = command.split(regex);
        for(String string : strings){
            System.out.println(string);
        }
        System.out.println("symbolList ==> " + symbolList);
        String symbol = symbolList.get(0);
        double a = 0,b =0;//test
        if(symbol.equals("+")){
            System.out.println("++++++++++");
            Operation c = Operation.PLUS;
            double result = c.setCalculate(Double.parseDouble(strings[0]),Double.parseDouble(strings[2]));//test
            System.out.println("결과 => " + result);//test
            ;
        }else if(symbol.equals("-")){
            System.out.println("==============");
            Operation c = Operation.SUBTRACT;
        }else if(symbol.equals("*")){
            System.out.println("*********");
            Operation c = Operation.MULTIPLY;
        }else if(symbol.equals("/")){
            System.out.println("//////");
            Operation c = Operation.DIVIDE;
        }
        System.out.println("값은 " + command + " 입니다");// 테스트코드
    }
    //잘못된 데이터 초기화
    private void updateText(String order, char c) {
        SwingUtilities.invokeLater(() -> {
            if(order.equals("change")){
                String getText = jTextField.getText().trim();
                String subString = getText.substring(0,getText.length()-2) + c;
                jTextField.setText(subString);
            } else if (order.equals("reset")) {
                jTextField.setText("");
            }
        });
    }

    public static void main(String[] args) {
        new JframeTest();//생성자 호출 UI 그려줌
    }
}
