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

public class ArithmeticCalculator implements DataVerification,CalculatorFunction{

    //화면에 보일 버튼 텍스트
    private String[][] btn = {{"%","CE","C","지우기"},{" "," "," ","/"},{"7","8","9","*"},{"4","5","6","-"},{"1","2","3","+"},{"+,-","0",".","="},};
    //입력한 텍스트가 보일 UI
    private JTextField jTextField;

    //생성자
    public ArithmeticCalculator(){
        setCalculatorScreen();
    }

    // 계산기 화면에 그려주는 함수
    @Override
    public void setCalculatorScreen() {
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
            }else if(btnText.equals("C")){
                System.out.println("ActionListener ==> 리셋이요");
                jTextField.setText("");
            }else if(btnText.equals("지우기")){
                if(!getText.trim().isEmpty()){
                    String subStr = getText.substring(0,getText.length()-1);//마지막 한글자 지움
                    jTextField.setText(subStr);
                }else{
                    System.out.println("ActionListener ==> 그만 지워");
                }
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

    //사칙연산 함수를 호출해서 계산해줄 함수
    @Override
    public void doCalculate(String command) {
        String regex = "((?<=\\+)|(?=\\+)|(?<=\\*)|(?=\\*)|(?<=-)|(?=-)|(?<=/)|(?=/))";
        String[] strings = command.split(regex);
        List<String> splitItem = new ArrayList<>(Arrays.asList(strings));
        String symbol = "";
        //처음이 기호면 삭제
        if (splitItem.get(0).equals("+") || splitItem.get(0).equals("-") || splitItem.get(0).equals("*") || splitItem.get(0).equals("/") || splitItem.get(0).equals(".")) {
            splitItem.remove(0);
        }
        double sum = 0;
        double result = 0; //총 합
        MyOperation c = null;
        List<Object> sumList = new ArrayList<>();// 나중에 더하거나 빼줄 것들 다 모아줌
        double num1 = 0;//1번째 계산 값
        double num2 = 0;//2번째 계산 값
        String checkStr = splitItem.get(splitItem.size()-1);//마지막 값이 기호이면 삭제해줌
        if(checkStr.equals("*")||checkStr.equals("/")||checkStr.equals("+")||checkStr.equals("-")||checkStr.equals(".")){
            splitItem.remove(splitItem.size()-1);
        }
        for(int i = 0 ; i < splitItem.size() ; i++ ) {
            String item = (String) splitItem.get(i);
            System.out.println(item);
            //double면 더블로 저장
            try{
                sumList.add((Double.parseDouble(item)));
            }catch (NumberFormatException e){
                sumList.add(item);
            }
            symbol = (String) item;
            if (item.equals("*") || item.equals("/")) {
                num1 = (double) sumList.get(sumList.size()-2);
                num2 = Double.parseDouble(splitItem.get(i+1));
                if(symbol.equals("*")){
                    System.out.println("*********");
                    c = MyOperation.MULTIPLY;
                }else if(symbol.equals("/")){
                    System.out.println("//////");
                    c = MyOperation.DIVIDE;
                }
                sum = c.setCalculate(num1,num2);
                sumList.remove(sumList.size()-2);
                sumList.remove(sumList.size()-1);
                sumList.add(sum);// 계산 한 결과 저장
                i++;
                System.out.println("결과 => " + sum);//test
            }
        }
        //곱하기 나누기 연산 후 남은 값 전부 sumList 가져와서 연산
        num1 = (double) sumList.get(0);
        for(int i = 1 ; i < sumList.size() ; i++ ) {
            if(result!=0){
                num1 = result;
            }
            num2 = (double)sumList.get(i+1);
            if(sumList.get(i).equals("+")){
                System.out.println("++++++++++");
                c = MyOperation.PLUS;
            }else if(sumList.get(i).equals("-")){
                System.out.println("-------------");
                c = MyOperation.SUBTRACT;
            }
            result = c.setCalculate(num1,num2);
            i++;
            System.out.println("result 연산 중... => " + result);//test
        }
        if(sumList.size() == 1){
            result = num1;
        }
        result =  (double) Math.round(result * 100) /100;
        updateText("update",' ',result);
        System.out.println("값은 " + result + " 입니다");// 테스트코드
    }
    //데이터 변경 및 초기화
    @Override
    public void updateText(String order, char c ,double result) {
        SwingUtilities.invokeLater(() -> {
            if(order.equals("change")){
                String getText = jTextField.getText().trim();
                String subString = getText.substring(0,getText.length()-2) + c;
                jTextField.setText(subString);
            } else if (order.equals("reset")) {
                jTextField.setText("");
            }else if(order.equals("update")){
                jTextField.setText(String.valueOf(result));
            }
        });
    }

    //올바른 데이터인지 검증
    @Override
    public void dataVerification() {
        String getText = jTextField.getText().trim();
        if(!getText.trim().isEmpty()){
            char[] chars = getText.toCharArray();
            System.out.println("chars length => " + chars.length);
            boolean isSymbol = false;// 기호가 들어갔으면 true
            for(char c : chars){
                System.out.println("chars => " + c);
                if(45 <= c && c <= 47){ // 45 : - , 46 : . 47 : /
                    if(isSymbol){
                        updateText("change", c,0);//앞에 기호바꿔서 화면에 교체
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
                            updateText("change", c,0);//앞에 기호바꿔서 화면에 교체
                        }
                        isSymbol = true;
                    }
                }else{
                    System.out.println("입력이 잘못되었습니다.");
                    updateText("reset", ' ',0);
                    break;
                }
            }
        }
    }
    public static void main(String[] args) {
        ArithmeticCalculator a = new ArithmeticCalculator();
    }
}
