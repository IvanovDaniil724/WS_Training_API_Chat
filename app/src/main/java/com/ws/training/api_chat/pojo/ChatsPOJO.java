package com.ws.training.api_chat.pojo;

import com.google.gson.annotations.SerializedName;
import com.ws.training.api_chat.common.RetrofitConnect;

import java.util.ArrayList;
import java.util.LinkedList;

public class ChatsPOJO
{
    @SerializedName("id")
    public int chatID;

    @SerializedName("chatName")
    public String chatName;

    @SerializedName("chatIconName")
    public String chatIconName;

    @SerializedName("users")
    public LinkedList<UsersPOJO> /*UsersPOJO[]*/ users;

    //@SerializedName("userIds")
    //public int[] userIds;
    //public ArrayList<Integer> userIds;

    //public ChatsPOJO(/*int chatID,*/ String chatName/*, String chatIconName*/, /*UsersPOJO[]*/ LinkedList<UsersPOJO> users)
    //{
        //this.chatName = chatName;
        //this.chatIconName = chatIconName;
        //this.users = users;

        //userIds = new int[users.size()];
        /*for (int i = 0; i < users.size(); i++)
        {
            //userIds[i] = users.get(i).getUserId();
            //userIds.add(users.get(i).getUserId());
        }*/
    //}

    //public int getChatID() {
    //    return chatID;
    //}
    public String getChatName() {
        return chatName;
    }
    public String getChatIconName() {
        return chatIconName;
    }
    //public /*UsersPOJO[]*/ LinkedList<UsersPOJO> getUsers() {
    //    return users;
    //}

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }
    public void setChatIconName(String chatIconName) {
        this.chatIconName = chatIconName;
    }
    //public void setUsers(/*UsersPOJO[]*/ LinkedList<UsersPOJO> users) {
    //    this.users = users;
    //}
}
