package com.ws.training.api_chat.pojo;

import com.google.gson.annotations.SerializedName;

public class UsersPOJO
{
    @SerializedName("id")
    public int userId;

    @SerializedName("firstName")
    public String firstName;

    @SerializedName("lastName")
    public String lastName;

    @SerializedName("avatarName")
    public String avatarName;

    public int getUserId() {
        return userId;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getAvatarName() {
        return avatarName;
    }
}
