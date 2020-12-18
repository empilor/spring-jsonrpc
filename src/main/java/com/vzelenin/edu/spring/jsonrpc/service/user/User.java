package com.vzelenin.edu.spring.jsonrpc.service.user;

public class User {
    private String userName;
    private String firstName;
    private String password;

    public User() {
    }

    public User(String userName, String firstName, String password) {
        this.userName = userName;
        this.firstName = firstName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                //hide password in logs
                ", password='" + password.replaceAll("[a-zA-Z0-9]","*") + '\'' +
                '}';
    }
}
