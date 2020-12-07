package com.ws.training.api_chat.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreateChatDataPOJO
{
    @SerializedName("chatName")
    public String chatName;

    @SerializedName("userIds")
    public List<Long> userIds;

    public CreateChatDataPOJO(String chatName, List<Long> userIds) {
        this.chatName = chatName;
        this.userIds = userIds;
    }
}
