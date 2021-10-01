package database.programming.week4;

import org.json.JSONArray;
import org.json.JSONObject;

public class App {
    public static void main(String[] args) {
        JSONObject obj = new JSONObject();
        obj.put("customer_name", "Adams");
        obj.put("loan_number", "L-16");
        System.out.println(obj);

        JSONArray arr = new JSONArray();
        arr.put(true);
        arr.put(0d);
        arr.put(0f);
        arr.put(0);
        arr.put(0l);
        System.out.println(arr);

//        obj.put("array", arr);
//        System.out.println(obj);

        arr.put(obj);
        System.out.println(arr);
    }
}
