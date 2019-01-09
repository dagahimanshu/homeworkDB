package com.example.hello.homeworkdb.model;

public class User {
    private String userName;
    private String password;
    private String description;
    private int img;
    private int id;

    public User(){

    }
    public User(String userName, String password, String description, int img,int id) {
        this.userName = userName;
        this.password = password;
        this.description = description;
        this.img = img;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getUserName() {

        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getDescription() {
        return description;
    }

    public int getImg() {
        return img;
    }
}
