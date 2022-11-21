package com.dh.userservice.model;

public class UserNameDTO {

    private String username;
    private String email;

    public UserNameDTO(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public UserNameDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserNameDTO setEmail(String email) {
        this.email = email;
        return this;
    }
}
