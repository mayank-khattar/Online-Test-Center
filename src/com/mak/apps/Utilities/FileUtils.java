package com.mak.apps.Utilities;

import java.io.*;

public class FileUtils {

    public static boolean CheckDirSystem(){
        File data_file =new File("C:\\Online Exam\\data\\historytest.json");
        File db_dir = new File("C:\\Online Exam\\data");
        File db_file = new File("C:\\Online Exam\\data\\users.json");
        return db_dir.exists() && db_file.exists() && data_file.exists();
    }

    public static boolean CreateDirSystem(){
        File db_dir = new File("C:\\Online Exam\\data");
        File db_file = new File("C:\\Online Exam\\data\\users.json");
        File data_file =new File("C:\\Online Exam\\data\\historytest.json");
        if (!db_dir.exists() || !db_file.exists() || !data_file.exists()) {
            try {
                return db_dir.mkdirs() && db_file.createNewFile() && data_file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            return true;
        }
        return false;
    }

    public static void WriteToFile(String path,String content)  {
        FileOutputStream fout= null;
        try {
            fout = new FileOutputStream(path);
            byte b[]=content.getBytes();//converting string into byte array
            fout.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fout != null) {
                    fout.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static String ReadFromFile(String path) {
        StringBuilder out = new StringBuilder();
        FileInputStream fin= null;
        try {
            fin = new FileInputStream(path);
            int i=0;
            while((i=fin.read())!=-1){
                out.append(String.valueOf((char) i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return String.valueOf(out);
    }
}
