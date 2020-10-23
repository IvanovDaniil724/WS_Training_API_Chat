package com.ws.training.api_chat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInActivity extends AppCompatActivity
{
    public Retrofit retrofit; public String token;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://fspobot.tw1.ru:8080")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSON_API_Placeholder service = retrofit.create(JSON_API_Placeholder.class);

        findViewById(R.id.SignIn_Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                AuthorizationPOJO authorization = new AuthorizationPOJO();
                authorization.setEmail(((EditText) findViewById(R.id.Email_EditText)).getText().toString());
                authorization.setPassword(((EditText) findViewById(R.id.Password_EditText)).getText().toString());

                Call<AuthorizationPOJO> call = service.Authorization(authorization);
                call.enqueue(new Callback<AuthorizationPOJO>()
                {
                    @Override
                    public void onResponse(Call<AuthorizationPOJO> call, Response<AuthorizationPOJO> response)
                    {
                        AuthorizationPOJO authorizationPOJO = response.body();
                        //TextView text = findViewById(R.id.SignIn_TextView);
                        //text.setText(token);
                    }

                    public void onFailure(Call<AuthorizationPOJO> call, Throwable t)
                    {

                    }
                });
            }
        });




    }
}