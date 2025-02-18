/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package movie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 *
 * @author 회원가입과 로그인 / 사용자와 관리자 선택
 */
public class Main extends JFrame {

    Main() {
        setTitle("CINEMA");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new MainPanel());
        setSize(700, 500);
        setLocationRelativeTo(null);

        Container contentPane = getContentPane();
        contentPane.setBackground(Color.WHITE); // 첫 시작 프레임

        // < id 입력 >
        JTextField id = new JTextField(20);
        id.setBounds(290, 150, 200, 35); // 가로 위치, 세로 위치, 가로 길이, 세로 길이
        contentPane.add(id);

        // < pw 입력 >
        JPasswordField pw = new JPasswordField(20);
        pw.setBounds(290, 210, 200, 35);
        contentPane.add(pw);

        // < 회원가입 버튼 만들기 >
        JButton join = new JButton("회원가입");
        join.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 15));
        join.setLocation(230, 300);
        join.setSize(100, 40);
        contentPane.add(join);
        join.setBackground(Color.WHITE);
        contentPane.setLayout(null);

        // < 로그인 버튼 만들기 >
        JButton login = new JButton("로그인");
        login.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 15));
        login.setLocation(380, 300);
        login.setSize(80, 40);
        contentPane.add(login);
        login.setBackground(Color.WHITE);
        contentPane.setLayout(null);

        // < 개인정보변경 버튼 만들기 >
        /*JButton change = new JButton("개인정보변경");
        change.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 20));
        change.setLocation(210, 220);
        change.setSize(75, 30);
        contentPane.add(change);
        change.setBackground(Color.WHITE);
        contentPane.setLayout(null);*/
        
        // < 로그인 버튼 누르면 MovieChart 창 뜸 >
        login.addActionListener((var e) -> {
            try {
                BufferedReader reader = new BufferedReader(new FileReader("member.txt"));
                String line = null;
                boolean loginSuccess = false;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data[0].equals(id.getText()) && data[1].equals(String.valueOf(pw.getPassword()))) {
                        loginSuccess = true;
                        break;
                    }
                }
                reader.close();
                if (loginSuccess) {
                    new MovieChart();
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "로그인에 실패하셨습니다.");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "로그인에 실패하셨습니다.");
            }
        }
        );

        // < 회원가입 버튼 누르면 ManagerMode 창 뜸 >
        join.addActionListener((ActionEvent e) -> {
            new SignUp();
            setVisible(false);
        }
        );
        setVisible(true);
    }

    class MainPanel extends JPanel {

        @Override
        public void paintComponent(Graphics p) {
            super.paintComponent(p);
            p.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 35));
            p.drawString("영화관에 오신 걸 환영합니다.", 150, 55); // 로그인

            p.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
            p.drawString("ID", 240, 175); // ID
            p.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
            p.drawString("PW", 230, 235); // PW

        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
