package com.ws.training.api_chat.pojo;

import com.google.gson.annotations.SerializedName;

public class ChatsPOJO
{
    @SerializedName("id")
    public int chatID;

    @SerializedName("chatName")
    public String chatName;

    @SerializedName("chatIconName")
    public String chatIconName;

    @SerializedName("users")
    public UsersPOJO[] users;

    public int getChatID() {
        return chatID;
    }
    public String getChatName() {
        return chatName;
    }
    public String getChatIconName() {
        return chatIconName;
    }
    public UsersPOJO[] getUsers() {
        return users;
    }
}
