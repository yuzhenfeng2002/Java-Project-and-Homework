package com.Yuzhen.ExerciseOnline.entity;

import org.springframework.web.multipart.MultipartFile;

public class Image {
    private int id;
    private String user_email;
    private String filename;
    private String new_name;
    private MultipartFile address;

    public MultipartFile getAddress() {
        return address;
    }

    public void setAddress(MultipartFile address) {
        this.address = address;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getNew_name() {
        return new_name;
    }

    public void setNew_name(String new_name) {
        this.new_name = new_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}