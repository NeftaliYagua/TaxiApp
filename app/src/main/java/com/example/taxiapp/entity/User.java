package com.example.taxiapp.entity;
import androidx.annotation.NonNull;
import java.io.Serializable;
import javax.mail.internet.InternetAddress;

public class User implements Serializable {

    private String uid = null;
    private String name = null;
    private String lastName = null;
    private String birthDate = null;
    private String email = null;
    private String phone = null;
    private ProfilePhoto photo = null;
    private String role = null;

    public User() {}

    public User(String uid, String name, String lastName, String birthDate, String email, String phone, ProfilePhoto photo, String role) {
        this.uid = uid;
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.phone = phone;
        this.photo = photo;
        this.role = role;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return name+" "+lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ProfilePhoto getPhoto() {
        return photo;
    }

    public void setPhoto(ProfilePhoto photo) {
        this.photo = photo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
                "\"uid\": \"" + uid + '"' +
                ", \"name\": \"" + name + '"' +
                ", \"lastName\": \"" + lastName + '"' +
                ", \"birthDate\": \"" + birthDate + '"' +
                ", \"email\": \"" + email + '"' +
                ", \"phone\": \"" + phone + '"' +
                ", \"photo\": " + photo +
                ", \"role\": \"" + role + '"' +
                '}';
    }
}
