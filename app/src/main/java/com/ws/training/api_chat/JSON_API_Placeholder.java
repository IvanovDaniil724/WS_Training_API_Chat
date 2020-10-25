package com.ws.training.api_chat;

import com.ws.training.api_chat.pojo.AuthorizationPOJO;
import com.ws.training.api_chat.pojo.RegistrationPOJO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JSON_API_Placeholder
{
    @POST("/auth/login")
    Call<AuthorizationPOJO> Authorization(@Body AuthorizationPOJO user);

    @POST("/auth/register")
    Call<RegistrationPOJO> Registration(@Body RegistrationPOJO user);
}
