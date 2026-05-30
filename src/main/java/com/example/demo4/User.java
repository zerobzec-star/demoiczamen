package com.example.demo4;

public class User {
    public User(String role, String fio) {
        this.role = role;
        this.fio = fio;
    }

    public String getFio() {
        return fio;
    }

    public String getRole() {
        return role;
    }

    private String fio;
    private String role;

}
