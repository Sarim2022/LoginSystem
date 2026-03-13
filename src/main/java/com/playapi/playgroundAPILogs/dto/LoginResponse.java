package com.playapi.playgroundAPILogs.dto;

import java.time.LocalDateTime;


public class LoginResponse {
    public int status;
    public String message;
    public String userid;
    public LocalDateTime  loginTime=LocalDateTime.now();
    public LoginResponse(int status, String message, String userid, LocalDateTime loginTime) {
        this.status = status;
        this.message = message;
        this.userid = userid;
        this.loginTime = loginTime;
    }
    public int getStatus() {
        return status;
    }

    public static String getsuccessResponse(LoginResponse Backresponse){
        return (Backresponse.getMessage()+"user ID: "+Backresponse.getUserid()+"with status: "+Backresponse.getStatus()+" and login time: "+Backresponse.getLoginTime()).toString();
    }
     public static String geterrorResponse(LoginResponse Backresponse){
        return (Backresponse.getMessage()+"user ID: "+Backresponse.getUserid()+"with status: "+Backresponse.getStatus()+" and login time: "+Backresponse.getLoginTime()).toString();
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }
    
    public String getMessage() {
        return message;
    }
    public String getUserid() {
        return userid;
    }

}
