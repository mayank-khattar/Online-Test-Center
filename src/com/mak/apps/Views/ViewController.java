package com.mak.apps.Views;

import javax.swing.*;

public class ViewController {
    private static LoginForm loginForm;
    private static RegisterForm registerForm;
    private static ConductTestFrame conductingFrame;

    public static void setUpLoginForm(){
        loginForm = new LoginForm();
        loginForm.setVisible(true);
        loginForm.setResizable(false);
        loginForm.setTitle("Online Exam center");
        loginForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginForm.setBounds(100,50,700,650);
    }
    public static void setUpRegisterForm(){
        registerForm = new RegisterForm();
        registerForm.setVisible(true);
        registerForm.setResizable(false);
        registerForm.setTitle("Online Exam center");
        registerForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registerForm.setBounds(100,50,700,650);
    }

    public static void disposeLoginForm(){
        if (loginForm!= null) {
            loginForm.dispose();
            loginForm = null;
        }
    }

    public static void disposeRegisterForm() {
        if (registerForm!= null) {
            registerForm.dispose();
            registerForm = null;
        }
    }

    public static void setTestCoductingFrame(){
        conductingFrame = new ConductTestFrame();
        conductingFrame.setVisible(true);
        conductingFrame.setResizable(false);
        conductingFrame.setTitle("Online Exam Center");
        conductingFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        conductingFrame.setBounds(100,50,700,650);
    }

    public static void disploseTestFrame(){
        if (conductingFrame != null){
            conductingFrame.dispose();
            conductingFrame = null;
        }
    }

}
