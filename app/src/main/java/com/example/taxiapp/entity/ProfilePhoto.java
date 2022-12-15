package com.example.taxiapp.entity;
import androidx.annotation.NonNull;

public class ProfilePhoto {

    private String filename = null, url = null;

    public ProfilePhoto() {}

    public ProfilePhoto(String filename, String url) {
        this.filename = filename;
        this.url = url;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @NonNull
    @Override
    public String toString() {
        return "{" +
                "\"filename\":\"" + filename + '"' +
                ", \"url\":\"" + url + '"' +
                '}';
    }
}
