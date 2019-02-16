package com.mak.apps.Utilities;

import com.mak.apps.Models.Student;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AccountUtils {
    public static void registerNewStudent(Student student){
        JSONObject student_json_obj = JSONUtils.getJSONStudentObj(student);
        JSONArray db = JSONUtils.getStudentDB(Constants.db_file);
        if (db != null) {
            db.add(student_json_obj);
            FileUtils.WriteToFile(Constants.db_file,db.toJSONString());
        }
    }

    public static boolean signInStudent(String rollno,String pass){
        JSONArray db = JSONUtils.getStudentDB(Constants.db_file);
        if (db != null) {
            for (Object aDb : db) {
                JSONObject obj = (JSONObject) aDb;
                if (obj.get("rollno").equals(rollno) && obj.get("pass").equals(pass)){
                    return true;
                }
            }
        }
        return false;
    }
}
