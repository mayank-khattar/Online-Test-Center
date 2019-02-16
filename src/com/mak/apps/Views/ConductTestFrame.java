package com.mak.apps.Views;

import com.mak.apps.Models.Options;
import com.mak.apps.Utilities.Constants;
import com.mak.apps.Utilities.JSONUtils;
import com.mak.apps.Utilities.RandomFns;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConductTestFrame extends JFrame implements ActionListener {

    private ArrayList<String> questions;
    private ArrayList<Options> options;

    private HashMap<Integer,String> answerlist;

    private static int current_ques_index = 0;
    private static final int ques_count = 10;

    private final String question_header = "<html>Question:) ";
    private final String question_footer = "</html>";
    private Container c;

    private Font f=new Font("Arial", Font.BOLD,18);

    private JLabel iconDisplay = new JLabel();
    private JButton startbtn = new JButton("Start test");

    private JLabel question_no_label;
    private JLabel question_label;
    private JRadioButton opt1,opt2,opt3;
    private ButtonGroup opt_grp;
    private JButton prev_btn, next_btn,submit_btn;

    TimerView timerView;
    Thread test;


    public ConductTestFrame() {
        URL iconURL = getClass().getResource("../Res/exam_icon.png");
        ImageIcon icon = new ImageIcon(iconURL);
        this.setIconImage(icon.getImage());
        c=this.getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(106, 255, 235));

        iconDisplay.setBounds(225,100,250,250);
        URL iconDisp = getClass().getResource("../Res/test_exam.png");
        ImageIcon imgDisp = new ImageIcon(iconDisp);
        Image img1 = imgDisp.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        iconDisplay.setIcon(new ImageIcon(img1));

        startbtn.setBounds(225,400,250,100);    //setting location and size of JButton
        startbtn.addActionListener(this);
        startbtn.setBackground(new Color(59, 89, 182));
        startbtn.setForeground(Color.WHITE);
        startbtn.setFocusPainted(false);
        startbtn.setFont(new Font("Tahoma", Font.BOLD, 32));

        c.add(startbtn);
        c.add(iconDisplay);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startbtn) {
            c.remove(startbtn);
            c.remove(iconDisplay);
            c.validate();
            c.repaint();
            initTest();
        } else if (e.getSource() == prev_btn) {
            if (!(current_ques_index<=0)){
                current_ques_index--;
                setQuestionNo(current_ques_index);
            }
        } else if (e.getSource() == next_btn) {
            if (!(current_ques_index>=9)){
                current_ques_index++;
                setQuestionNo(current_ques_index);
            }
        } else if (e.getSource() == submit_btn) {
            EndTest();
        }
        if (e.getSource() == opt1) {
            answerlist.put(current_ques_index,"a");
        }
        if (e.getSource() == opt2) {
            answerlist.put(current_ques_index,"b");
        }
        if (e.getSource() == opt3) {
            answerlist.put(current_ques_index,"c");
        }
    }

    private void EndTest() {
        timerView.StopTimer();
        test.interrupt();
        ArrayList<String> out = new ArrayList<>();
        for (Map.Entry<Integer,String> entry:answerlist.entrySet()){
            out.add(entry.getValue());
        }
        int score = RandomFns.evaluateTest(out);
        c.removeAll();
        c.validate();
        c.repaint();
        initFinalScore(score);
    }

    private void initFinalScore(int score) {
        JLabel heading = new JLabel("Your final score is ");
        heading.setHorizontalTextPosition(SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 40));
        heading.setBounds(50,50,700,100);
        c.add(heading);
        JLabel score_title = new JLabel(String.valueOf(score));
        score_title.setFont(new Font("Arial", Font.BOLD, 150));
        score_title.setForeground(new Color(9, 16, 255));
        score_title.setHorizontalTextPosition(SwingConstants.CENTER);
        score_title.setBounds(50,160,700,200);
        c.add(score_title);
        JButton exit_btn = new JButton("EXIT");
        exit_btn.setBounds(50,400,250,100);    //setting location and size of JButton
        exit_btn.addActionListener(e -> System.exit(0));
        exit_btn.setBackground(new Color(59, 89, 182));
        exit_btn.setForeground(Color.WHITE);
        exit_btn.setFocusPainted(false);
        exit_btn.setFont(new Font("Tahoma", Font.BOLD, 32));
        c.add(exit_btn);

    }

    private void initTest(){
        startTimer();
        question_no_label = new JLabel();
        question_label = new JLabel();
        question_label.setFont(f);
        question_no_label.setFont(f);
        question_no_label.setBounds(50,155,150,40);
        question_label.setBounds(50,200,550,75);
        c.add(question_no_label);
        c.add(question_label);

        opt_grp = new ButtonGroup();
        opt1 = new JRadioButton();
        opt2 = new JRadioButton();
        opt3 = new JRadioButton();
        opt_grp.add(opt1);opt_grp.add(opt2);opt_grp.add(opt3);

        opt1.setBounds(50,290,200,30);
        opt2.setBounds(50,330,200,30);
        opt3.setBounds(50,370,200,30);

        c.add(opt1);
        c.add(opt2);
        c.add(opt3);

        prev_btn = new JButton("Previous");
        prev_btn.setBounds(30,450,140,50);
        next_btn = new JButton("Next");
        next_btn.setBounds(200,450,140,50);
        submit_btn = new JButton("Submit");
        submit_btn.setBounds(370,450,140,50);

        prev_btn.addActionListener(this);
        prev_btn.setBackground(new Color(59, 89, 182));
        prev_btn.setForeground(Color.WHITE);
        prev_btn.setFocusPainted(false);
        prev_btn.setFont(new Font("Tahoma", Font.BOLD, 12));

        next_btn.addActionListener(this);
        next_btn.setBackground(new Color(59, 89, 182));
        next_btn.setForeground(Color.WHITE);
        next_btn.setFocusPainted(false);
        next_btn.setFont(new Font("Tahoma", Font.BOLD, 12));

        submit_btn.addActionListener(this);
        submit_btn.setBackground(new Color(59, 89, 182));
        submit_btn.setForeground(Color.WHITE);
        submit_btn.setFocusPainted(false);
        submit_btn.setFont(new Font("Tahoma", Font.BOLD, 12));

        c.add(prev_btn);
        c.add(next_btn);
        c.add(submit_btn);

        answerlist = new HashMap<>();
        try {
            questions = JSONUtils.getQuestions(Constants.question_file);
            options = JSONUtils.getOptions(Constants.question_file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setQuestionNo(0);
    }

    private void startTimer() {
        timerView= new TimerView(120000);
        timerView.setBounds(30,10,150,100);
        c.add(timerView);
        test =new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(120000);
                    timerView.StopTimer();
                    EndTest();
                } catch (InterruptedException e) {
                    System.out.println("test exited");
                }
            }
        });
        test.start();
    }

    private void setQuestionNo(int i) {
        question_no_label.setText("Question no: ".concat(String.valueOf(i+1)));
        String ques = questions.get(i);
        if (ques.length() >32) {
            ques = question_header.concat(ques.substring(0, 31).concat("</br>").
                    concat(ques.substring(32, ques.length() - 1)).concat(question_footer));
        }
        question_label.setText(ques);
        if (answerlist.containsKey(i)) {
            switch (answerlist.get(i)) {
                case "a":
                    opt_grp.setSelected(opt1.getModel(), true);
                    break;
                case "b":
                    opt_grp.setSelected(opt2.getModel(), true);
                    break;
                case "c":
                    opt_grp.setSelected(opt3.getModel(), true);
                    break;
                default:
                    opt_grp.clearSelection();
                    break;
            }
        } else {
            opt_grp.clearSelection();
        }
        opt1.setText(options.get(i).getA());
        opt2.setText(options.get(i).getB());
        opt3.setText(options.get(i).getC());
        opt1.addActionListener(this);
        opt2.addActionListener(this);
        opt3.addActionListener(this);
    }

}
