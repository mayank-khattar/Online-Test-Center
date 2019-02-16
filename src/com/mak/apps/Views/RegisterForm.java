package com.mak.apps.Views;

import com.mak.apps.Models.Student;
import com.mak.apps.Utilities.AccountUtils;
import com.mak.apps.Utilities.JSONUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import static com.mak.apps.Utilities.RandomFns.showErrorMsgLR;

public class RegisterForm extends JFrame implements ActionListener {
    private Container c;

    private JLabel nLabel = new JLabel("Name");
    private JLabel uLabel=new JLabel("Roll Number");
    private JLabel pLabel =new JLabel("Password");

    private JTextField nname=new JTextField();
    private JTextField uname=new JTextField();
    private JPasswordField pass=new JPasswordField();

    private String[] courses = {"Engineering (B.E/ B.Tech)"};
    private String[] departments = {"CSE","ECE","ME"};
    private String[] sections = {"A","B","C","D"};

    private JLabel msgbox = new JLabel();
    private Font f=new Font("Arial", Font.BOLD,18);

    private JButton loginbtn=new JButton("Login");
    private JButton registerbtn=new JButton("Register");

    private JComboBox<String> coursesDropDown = new JComboBox<>(courses);
    private JComboBox<String> deptDropDown = new JComboBox<>(departments);
    private JComboBox<String> sectionsDropDown = new JComboBox<>(sections);

    public RegisterForm(){
        URL iconURL = getClass().getResource("../Res/exam_icon.png");
        ImageIcon icon = new ImageIcon(iconURL);
        this.setIconImage(icon.getImage());
        c=this.getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(106, 255, 235));

        nLabel.setBounds(155,65,140,40);
        nname.setBounds(305,65,140,40);
        nLabel.setFont(f);
        nname.setFont(f);

        uLabel.setBounds(155,155,140,40);
        uname.setBounds(305,155,140,40);
        uLabel.setFont(f);
        uname.setFont(f);

        pLabel.setBounds(155,245,140,40);
        pass.setBounds(305,245,140,40);
        pLabel.setFont(f);
        pass.setFont(f);

        coursesDropDown.setBounds(155,310,110,40);
        deptDropDown.setBounds(285,310,110,40);
        sectionsDropDown.setBounds(415,310,110,40);

        registerbtn.setBounds(285,365,140,50);    //setting location and size of JButton
        registerbtn.addActionListener(this);
        registerbtn.setBackground(new Color(59, 89, 182));
        registerbtn.setForeground(Color.WHITE);
        registerbtn.setFocusPainted(false);
        registerbtn.setFont(new Font("Tahoma", Font.BOLD, 12));

        loginbtn.setBounds(285,430,140,50);    //setting location and size of JButton
        loginbtn.addActionListener(this);
        loginbtn.setBackground(new Color(59, 89, 182));
        loginbtn.setForeground(Color.WHITE);
        loginbtn.setFocusPainted(false);
        loginbtn.setFont(new Font("Tahoma", Font.BOLD, 12));

        Cursor cur=new Cursor(Cursor.HAND_CURSOR);
        loginbtn.setCursor(cur);
        registerbtn.setCursor(cur);

        msgbox.setBounds(120,480,500,200);
        msgbox.setFont(f);

        c.add(nLabel);
        c.add(nname);
        c.add(uLabel);
        c.add(uname);
        c.add(pLabel);
        c.add(pass);

        c.add(loginbtn);
        c.add(registerbtn);
        c.add(msgbox);
        c.add(coursesDropDown);
        c.add(deptDropDown);
        c.add(sectionsDropDown);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginbtn) {
            ViewController.disposeRegisterForm();
            ViewController.setUpLoginForm();
        }
        if (e.getSource() == registerbtn){
            boolean everythingfine = true;
            String error_src = "";
            if (uname.getText().length()<=0 || uname.getText().length()>16){
                error_src = error_src.concat("Roll no must be within range of 1 to 15 characters &&");
                everythingfine = false;
            }
            if (nname.getText().length()<= 0 || nname.getText().length()>30){
                error_src = error_src.concat("Name must be within range of 1 to 30 characters &&");
                everythingfine = false;
            }
            if (new String(pass.getPassword()).length()<= 0 || new String(pass.getPassword()).length()>10){
                error_src = error_src.concat("Password must be within range of 1 to 10 characters &&");
                everythingfine = false;
            }
            if (everythingfine) {
                AccountUtils.registerNewStudent(new Student(uname.getText(), nname.getText(), new String(pass.getPassword()),
                        courses[coursesDropDown.getSelectedIndex()], departments[deptDropDown.getSelectedIndex()],
                        sections[sectionsDropDown.getSelectedIndex()]));
                uname.setText("");
                nname.setText("");
                pass.setText("");
                registerbtn.setEnabled(false);
                msgbox.setText("User Registered, now you can login in");
                new Thread(() -> {
                    try {
                        Thread.sleep(1500);
                        registerbtn.setEnabled(true);
                        msgbox.setText("");
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }).start();

            } else {
                String[] errors = error_src.split("&&");
                showErrorMsgLR(errors, msgbox);
            }
        }
    }
}
