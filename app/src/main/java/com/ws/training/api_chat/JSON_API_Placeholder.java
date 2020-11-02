package com.ws.training.api_chat;

import com.ws.training.api_chat.activities.SignInActivity;
import com.ws.training.api_chat.pojo.AuthorizationPOJO;
import com.ws.training.api_chat.pojo.ChatsPOJO;
import com.ws.training.api_chat.pojo.RegistrationPOJO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JSON_API_Placeholder
{
    @POST("/auth/login")
    Call<AuthorizationPOJO> Authorization(@Body AuthorizationPOJO user);

    @POST("/auth/register")
    Call<RegistrationPOJO> Registration(@Body RegistrationPOJO user);

    @GET("/chats/myChats")
    Call<List<ChatsPOJO>> getUserChats(@Header("Authorization") String token);

    @POST("/chats/createChat")
    Call<ChatsPOJO> createNewChat(@Header("Authorization") String token, @Body ChatsPOJO chat);
}
