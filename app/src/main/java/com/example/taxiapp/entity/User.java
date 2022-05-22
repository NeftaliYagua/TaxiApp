package com.example.taxiapp.entity;
import androidx.annotation.NonNull;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;
import javax.mail.internet.InternetAddress;

public class User implements Serializable {

    private String uid;
    private String email;
    private String password;
    private String photoURL;
    private Person person;

    public User(){
        uid = null;
        email = null;
        password = null;
        photoURL = null;
        person = null;
    }

    public User(JSONObject object) {
        try {
            uid = object.getString("uid");
            email = object.getString("email");
            password = object.getString("password");
            photoURL = object.getString("photoURL");
            person = new Person(object.getJSONObject("person"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public User(String uid, String email, String password, String photoURL, Person person) {
        this.uid = uid;
        this.email = email;
        this.password = password;
        this.photoURL = photoURL;
        this.person = person;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public static boolean isValidEmail(String email) {
        try {
            InternetAddress address = new InternetAddress(email);
            address.validate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @NonNull
    @Override
    public String toString() {
        return "{" +
                "\"uid\":\"" + uid + "\"" +
                ", \"email\":\"" + email + "\"" +
                ", \"password\":\"" + password + "\"" +
                ", \"photoURL\":\"" + photoURL + "\"" +
                "}";
    }
}
