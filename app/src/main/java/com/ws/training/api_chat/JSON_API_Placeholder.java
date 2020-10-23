package com.ws.training.api_chat;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JSON_API_Placeholder
{
    @POST("/auth/login")
    Call<AuthorizationPOJO> Authorization(@Body AuthorizationPOJO user);

   // @POST("/auth/login")
    //Call<AuthorizationPOJO> Registration(@Body AuthorizationPOJO user);
}
