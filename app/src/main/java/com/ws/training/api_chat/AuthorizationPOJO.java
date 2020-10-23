package com.ws.training.api_chat;

import com.google.gson.annotations.SerializedName;

public class AuthorizationPOJO
{
    @SerializedName("email")
    public String email;

    @SerializedName("password")
    public String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
