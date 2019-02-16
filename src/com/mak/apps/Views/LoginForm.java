package com.mak.apps.Views;

import com.mak.apps.Utilities.AccountUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import static com.mak.apps.Utilities.RandomFns.showErrorMsgLR;

public class LoginForm extends JFrame implements ActionListener {
    private Container c;

    private JLabel uLabel=new JLabel("Roll Number");
    private JLabel pLabel =new JLabel("Password");

    private JLabel msgbox = new JLabel();

    private JTextField uname=new JTextField();
    private JPasswordField pass=new JPasswordField();

    private Font f=new Font("Arial", Font.BOLD,18);

    private JButton loginbtn=new JButton("Login");
    private JButton registerbtn=new JButton("Register");

    public LoginForm(){
        URL iconURL = getClass().getResource("../Res/exam_icon.png");
        ImageIcon icon = new ImageIcon(iconURL);
        this.setIconImage(icon.getImage());
        c=this.getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(106, 255, 235));

        uLabel.setBounds(155,135,140,40);
        pLabel.setBounds(155,225,140,40);

        uname.setBounds(305,135,200,40);
        pass.setBounds(305,225,200,40);

        loginbtn.setBounds(285,335,140,50);    //setting location and size of JButton
        loginbtn.addActionListener(this);
        loginbtn.setBackground(new Color(59, 89, 182));
        loginbtn.setForeground(Color.WHITE);
        loginbtn.setFocusPainted(false);
        loginbtn.setFont(new Font("Tahoma", Font.BOLD, 12));

        registerbtn.setBounds(285,400,140,50);    //setting location and size of JButton
        registerbtn.addActionListener(this);
        registerbtn.setBackground(new Color(59, 89, 182));
        registerbtn.setForeground(Color.WHITE);
        registerbtn.setFocusPainted(false);
        registerbtn.setFont(new Font("Tahoma", Font.BOLD, 12));

        Cursor cur=new Cursor(Cursor.HAND_CURSOR);
        loginbtn.setCursor(cur);
        registerbtn.setCursor(cur);

        msgbox.setBounds(120,480,500,200);
        msgbox.setFont(f);

        uLabel.setFont(f);
        pLabel.setFont(f);

        uname.setFont(f);
        pass.setFont(f);

        c.add(uLabel);
        c.add(pLabel);

        c.add(uname);
        c.add(pass);

        c.add(loginbtn);
        c.add(registerbtn);
        c.add(msgbox);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginbtn) {
            boolean everythingfine = true;
            String error_src = "";
            if (uname.getText().length()<=0 || uname.getText().length()>16){
                error_src = error_src.concat("Roll no must be within range of 1 to 15 characters &&");
                everythingfine = false;
            }
            if (new String(pass.getPassword()).length()<= 0 || new String(pass.getPassword()).length()>10){
                error_src = error_src.concat("Password must be within range of 1 to 30 characters &&");
                everythingfine = false;
            }
            if (everythingfine) {
                if(AccountUtils.signInStudent(uname.getText(),new String(pass.getPassword()))){
                    ViewController.disposeLoginForm();
                    ViewController.setTestCoductingFrame();
                } else {
                    String[] err = {"Login Failed, please verify your credentials"};
                    showErrorMsgLR(err,msgbox);
                }
            } else {
                String[] errors = error_src.split("&&");
                showErrorMsgLR(errors, msgbox);
            }
        }
        if (e.getSource() == registerbtn){
            ViewController.disposeLoginForm();
            ViewController.setUpRegisterForm();
        }
    }


}
