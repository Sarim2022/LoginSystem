package com.playapi.playgroundAPILogs.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.playapi.playgroundAPILogs.model.LoginRequest;
import com.playapi.playgroundAPILogs.model.SignupRequest;
import com.playapi.playgroundAPILogs.storage.UserLocalTest;
import com.playapi.playgroundAPILogs.storage.userlocaldata;

@Service
public class AuthService {

    public Boolean authenticateUser(LoginRequest requestuser){
       if (requestuser == null) {
           return false;
       }
       if (userlocaldata.users.stream().anyMatch(u -> u.getEmail().equals(requestuser.getEmail())) && userlocaldata.users.stream().anyMatch(u -> u.getPassword().equals(requestuser.getPassword()))){
        return true;
       }
       return false;
    }

    public String CheckEmptyState(LoginRequest requestuser){
        if (!StringUtils.hasText(requestuser.getEmail()) && !StringUtils.hasText(requestuser.getPassword())) {
            return "Email and password are empty";
        }
        if (!StringUtils.hasText(requestuser.getEmail())){
            return "Email is empty";
        } else if (!StringUtils.hasText(requestuser.getPassword())){
            return "Password is empty";
        }
        return null;
    }

    public String UserRegistractionProcess(SignupRequest request){
        if (request == null) {
            return "Invalid request";
        }

        if (!StringUtils.hasText(request.getName())) {
            return "Name is empty";
        }
        if (!StringUtils.hasText(request.getEmail())) {
            return "Email is empty";
        }
        if (!StringUtils.hasText(request.getPassword())) {
            return "Password is empty";
        }

        // Prevent registering the same email twice (in-memory check)
        boolean alreadyExists = userlocaldata.users.stream()
            .anyMatch(u -> u.getEmail().equalsIgnoreCase(request.getEmail()));
        if (alreadyExists) {
            return "Email is already registered";
        }

        // create user object
        UserLocalTest user = new UserLocalTest(
            request.getName(),
            request.getEmail(),
            request.getPassword()
        );

        // save in memory
        userlocaldata.users.add(user);

        return "User registered successfully";

    }


}
