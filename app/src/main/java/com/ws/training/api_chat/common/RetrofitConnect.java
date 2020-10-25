package com.ws.training.api_chat.common;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;

import androidx.appcompat.app.AlertDialog;

import com.ws.training.api_chat.JSON_API_Placeholder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConnect
{
    public static final String BASE_URL = "http://fspobot.tw1.ru:8080";

    public Retrofit retrofit; public static JSON_API_Placeholder json_api_placeholder;

    private OkHttpClient CreateOkHttpClient()
    {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    public void CreateConnection()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(CreateOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        json_api_placeholder = retrofit.create(JSON_API_Placeholder.class);
    }

    public static void CreateErrorMessage(Context context, String title_text, String error_message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int id) { }
        });
        builder.setMessage(error_message).setTitle(title_text);
        AlertDialog dialog = builder.create(); dialog.show();
    }
}
