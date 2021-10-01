package database.programming.week4;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App2 {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("D:\\project\\personal\\database-programming\\src\\main\\resources\\depositor.json"));
        String jsonString = "";
        while (true) {
            String line = r.readLine();
            if (line == null) {
                break;
            }
            jsonString += line;
        }
        r.close();

        JSONArray array = new JSONArray(jsonString);
        System.out.println(array.toString(4));

        List<Depositor> depositorList = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            Object obj = array.get(i);
            if (obj instanceof JSONObject) {
                JSONObject data = (JSONObject) obj;
                depositorList.add(new Depositor(data.getString("customer_name"),
                        data.getString("account_number")));
            }
        }
        for (Depositor depositor : depositorList) {
            System.out.println(depositor);
        }
    }
}
