package com.playapi.playgroundAPILogs.dto;

public class SignupResponse {
    public int statusCode;
    public String message;
    public String email;
    public SignupResponse(int statusCode, String message, String email) {
        this.statusCode = statusCode;
        this.message = message;
        this.email = email;
    }
    public int getStatusCode() {
        return statusCode;
    }
    public static SignupResponse getResponse(SignupResponse response){
        return new SignupResponse(response.getStatusCode(), response.getMessage(), response.getEmail());
    }
    public String getMessage() {
        return message;
    }
    public String getEmail() {
        return email;
    }
}
