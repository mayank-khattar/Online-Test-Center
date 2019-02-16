package com.mak.apps.Utilities;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class RandomFns {
    public static void showErrorMsgLR(String[] errors, JLabel msg) {
        new Thread(() -> {
            for (String error : errors) {
                msg.setText(error);
                try {
                    Thread.sleep(2000);
                    msg.setText("");
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }).start();
    }

    public static int evaluateTest(ArrayList<String> answers){
        int score = 0;
        try {
            ArrayList<String> correct = JSONUtils.getAnswers(Constants.question_file);
            for (int i = 0; i<answers.size();i++){
                if (correct != null && correct.get(i).equals(answers.get(i))) {
                    score++;
                }
            }
            return score;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}
