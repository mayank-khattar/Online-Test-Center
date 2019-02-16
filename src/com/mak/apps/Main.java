package com.mak.apps;


import com.mak.apps.Utilities.Constants;
import com.mak.apps.Utilities.FileUtils;
import com.mak.apps.Views.ViewController;

public class Main {
    public static void main(String[] args) {
        new Main();
    }

    private Main(){
        if(!FileUtils.CheckDirSystem()){
            if (!FileUtils.CreateDirSystem()){
                System.exit(-1);
            }
        }
        FileUtils.WriteToFile(Constants.question_file, Constants.question_file_contents);
        ViewController.setUpLoginForm();
    }
}
