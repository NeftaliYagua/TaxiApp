package com.example.taxiapp.entity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONObject;

public class Person {

    @NonNull private String id;
    @NonNull private String identification;
    @NonNull private String names;
    @NonNull private String surnames;
    @Nullable private String birthday;

    public Person() {}

    public Person(@NonNull JSONObject object) {
        try {
            id = object.getString("id");
            identification = object.getString("identification");
            names = object.getString("names");
            surnames = object.getString("surnames");
            birthday = object.getString("birthday");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Person(@NonNull String id, @NonNull String identification, @NonNull String names, @NonNull String surnames, @Nullable String birthday) {
        this.id = id;
        this.identification = identification;
        this.names = names;
        this.surnames = surnames;
        this.birthday = birthday;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getIdentification() {
        return identification;
    }

    public void setIdentification(@NonNull String identification) {
        this.identification = identification;
    }

    @NonNull
    public String getNames() {
        return names;
    }

    public void setNames(@NonNull String names) {
        this.names = names;
    }

    @NonNull
    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(@NonNull String surnames) {
        this.surnames = surnames;
    }

    @Nullable
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(@Nullable String birthday) {
        this.birthday = birthday;
    }
}
