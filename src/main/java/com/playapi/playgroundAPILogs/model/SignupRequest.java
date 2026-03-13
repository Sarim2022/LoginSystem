package com.playapi.playgroundAPILogs.model;

public class SignupRequest {
    public String Name;
    public String Email;
    public String Password;
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;        
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        Password = password;
    }
}
