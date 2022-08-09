package com.example.taxiapp.entity;
import android.content.Context;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

public class Assets implements Serializable {

    private static String getContent(Context context, String filename) {
        try {
            InputStream is = context.getAssets().open(filename);
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            return new String(bytes);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static JSONArray publicMenus(Context context) {
        try {
            String content = getContent(context,"menus.json");
            System.out.println(content);
            return new JSONArray(content);
        } catch (JSONException ex) {
            ex.printStackTrace();
            return null;
        }
    }


}
