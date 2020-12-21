package com.example.xbree.Entities;

public class User {
    private int id;
    private String name;
    private String lname;
    private String email;
    private String password;
    private  int phone;
    private String image;

    public User(int id, String name, String lname, String email, String password, int phone, String image) {
        this.id = id;
        this.name = name;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.image = image;
    }

    public User(String name, String lname, String email, String password, int phone, String image) {
        this.name = name;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLname() {
        return lname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getPhone() {
        return phone;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
