package com.ws.training.api_chat.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.ws.training.api_chat.JSON_API_Placeholder;
import com.ws.training.api_chat.R;
import com.ws.training.api_chat.common.FullscreenOptimization;
import com.ws.training.api_chat.common.RetrofitConnect;
import com.ws.training.api_chat.pojo.AuthorizationPOJO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity
{
    public static String token;
    public static SharedPreferences settings;
    FullscreenOptimization fullscreenOptimization;

    public static final String APP_PREFERENCES = "settings";
    public static final String APP_PREFERENCES_TOKEN = "token";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        fullscreenOptimization = new FullscreenOptimization(getSupportActionBar(), getWindow().getDecorView());
        fullscreenOptimization.Optimization();

        settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        if (settings.contains(APP_PREFERENCES_TOKEN))
        {
            RetrofitConnect.CreateErrorMessage(SignInActivity.this,
                    "token", token);

            Intent toMainScreen = new Intent(SignInActivity.this, MenuBottomNavigationActivity.class);
            startActivity(toMainScreen); finish();
        }

        new RetrofitConnect().CreateConnection();
        JSON_API_Placeholder service = RetrofitConnect.json_api_placeholder;

        findViewById(R.id.SignIn_Button).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AuthorizationPOJO authorization = new AuthorizationPOJO();
                authorization.setEmail(((EditText) findViewById(R.id.SignIn_Email_EditText)).getText().toString());
                authorization.setPassword(((EditText) findViewById(R.id.SignIn_Password_EditText)).getText().toString());

                Call<AuthorizationPOJO> call = service.Authorization(authorization);
                call.enqueue(new Callback<AuthorizationPOJO>()
                {
                    @Override
                    public void onResponse(Call<AuthorizationPOJO> call, Response<AuthorizationPOJO> response)
                    {
                        token = response.body().getToken();

                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString(APP_PREFERENCES_TOKEN, token); editor.apply();

                        Intent toMainScreen = new Intent(SignInActivity.this, MenuBottomNavigationActivity.class);
                        startActivity(toMainScreen); finish();
                    }

                    public void onFailure(Call<AuthorizationPOJO> call, Throwable t)
                    {
                        RetrofitConnect.CreateErrorMessage(SignInActivity.this,
                                "Ошибка подключения к серверу", t.getMessage());
                    }
                });
            }
        });

        findViewById(R.id.SignUp_Button).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent toRegistrationIntent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(toRegistrationIntent); finish();
            }
        });
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        fullscreenOptimization.Optimization();
    }
}