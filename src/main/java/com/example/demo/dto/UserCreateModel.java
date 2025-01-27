package com.example.demo.dto;

public class UserCreateModel {

    private String name;
    private String email;
    private String password;

    public UserCreateModel(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return name;
    }

    public void setUsername(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "UserCreateModel{" +
                "username='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
