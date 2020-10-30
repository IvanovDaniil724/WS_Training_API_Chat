package com.ws.training.api_chat.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.ws.training.api_chat.JSON_API_Placeholder;
import com.ws.training.api_chat.R;
import com.ws.training.api_chat.common.FullscreenOptimization;
import com.ws.training.api_chat.common.RetrofitConnect;
import com.ws.training.api_chat.pojo.AuthorizationPOJO;
import com.ws.training.api_chat.pojo.RegistrationPOJO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity
{
    FullscreenOptimization fullscreenOptimization;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fullscreenOptimization = new FullscreenOptimization(getSupportActionBar(), getWindow().getDecorView());
        fullscreenOptimization.Optimization();

        JSON_API_Placeholder service = RetrofitConnect.json_api_placeholder;

        findViewById(R.id.SignUp_Button).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                RegistrationPOJO registration = new RegistrationPOJO();
                registration.setFirstName(((EditText) findViewById(R.id.SignUp_FirstName_EditText)).getText().toString());
                registration.setLastName(((EditText) findViewById(R.id.SignUp_LastName_EditText)).getText().toString());
                registration.setEmail(((EditText) findViewById(R.id.SignUp_Email_EditText)).getText().toString());
                registration.setPassword(((EditText) findViewById(R.id.SignUp_Password_EditText)).getText().toString());

                Call<RegistrationPOJO> call = service.Registration(registration);
                call.enqueue(new Callback<RegistrationPOJO>()
                {
                    @Override
                    public void onResponse(Call<RegistrationPOJO> call, Response<RegistrationPOJO> response)
                    {
                        SignInActivity.token = response.body().getToken();

                        Intent toMainScreen = new Intent(SignUpActivity.this, MenuBottomNavigationActivity.class);
                        startActivity(toMainScreen); finish();
                    }

                    public void onFailure(Call<RegistrationPOJO> call, Throwable t)
                    {
                        RetrofitConnect.CreateErrorMessage(SignUpActivity.this,
                                "Ошибка подключения к серверу", t.getMessage());
                    }
                });
            }
        });

        findViewById(R.id.Back_Button).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent toAuthorizationIntent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(toAuthorizationIntent); finish();
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