package com.mak.apps.Utilities;

import com.mak.apps.Models.Options;
import com.mak.apps.Models.Student;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

public class JSONUtils {

    public static JSONObject getJSONStudentObj(Student s){
        JSONObject root = new JSONObject();
        root.put("name",s.getName());
        root.put("rollno",s.getRollno());
        root.put("pass",s.getPass());
        root.put("course",s.getCourse());
        root.put("dept",s.getDept());
        root.put("section",s.getSection());
        return root;
    }

    public static JSONArray getStudentDB(String path){
        JSONParser parser = new JSONParser();
        String file = FileUtils.ReadFromFile(path);
        if (file.isEmpty()){
            file = "[]";
        }
        try {
            Object obj = parser.parse(file);
            return (JSONArray)obj;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<String> getQuestions(String path) throws Exception {
        JSONParser parser = new JSONParser();
        ArrayList<String> ques = new ArrayList<>();
        String file = FileUtils.ReadFromFile(path);
        if (file.isEmpty()){
            System.out.println("File empty");
            throw new Exception("File is empty");
        }
        try {
            Object obj = parser.parse(file);
            JSONObject root = (JSONObject)obj;
            JSONArray data = (JSONArray) root.get("data");
            for (Object aData : data) {
                JSONObject object = (JSONObject) aData;
                ques.add(String.valueOf(object.get("question")));
            }
            return ques;
        } catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }
    public static ArrayList<Options> getOptions(String path) throws Exception {
        JSONParser parser = new JSONParser();
        ArrayList<Options> opts = new ArrayList<>();
        String file = FileUtils.ReadFromFile(path);
        if (file.isEmpty()){
            System.out.println("File empty");
            throw new Exception("File is empty");
        }
        try {
            Object obj = parser.parse(file);
            JSONObject root = (JSONObject) obj;
            JSONArray data = (JSONArray) root.get("data");
            for (Object aData : data) {
                JSONObject object = (JSONObject) aData;
                JSONObject options = (JSONObject) object.get("answers");
                opts.add(new Options(
                        String.valueOf(options.get("a")),
                        String.valueOf(options.get("b")),
                        String.valueOf(options.get("c"))
                ));
            }
            return opts;
        } catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }
    public static ArrayList<String> getAnswers(String path) throws Exception {
        JSONParser parser = new JSONParser();
        ArrayList<String> ans = new ArrayList<>();
        String file = FileUtils.ReadFromFile(path);
        if (file.isEmpty()){
            System.out.println("File empty");
            throw new Exception("File is empty");
        }
        try {
            Object obj = parser.parse(file);
            JSONObject root = (JSONObject) obj;
            JSONArray data = (JSONArray) root.get("data");
            for (Object aData : data) {
                JSONObject object = (JSONObject) aData;
                JSONObject options = (JSONObject) object.get("answers");
                ans.add(String.valueOf(options.get("correct")));
            }
            return ans;
        } catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

}
