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

    public int getChatID() {
        return chatID;
    }

    public String getChatName() {
        return chatName;
    }

    public String getChatIconName() {
        return chatIconName;
    }
}
