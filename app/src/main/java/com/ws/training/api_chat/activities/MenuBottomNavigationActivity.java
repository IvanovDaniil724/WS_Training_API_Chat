package com.ws.training.api_chat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ws.training.api_chat.JSON_API_Placeholder;
import com.ws.training.api_chat.R;
import com.ws.training.api_chat.common.FullscreenOptimization;
import com.ws.training.api_chat.common.RetrofitConnect;
import com.ws.training.api_chat.pojo.AuthorizationPOJO;
import com.ws.training.api_chat.pojo.ChatsPOJO;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuBottomNavigationActivity extends AppCompatActivity
{
    FullscreenOptimization fullscreenOptimization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_bottom_navigation);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_chats, R.id.navigation_users, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        fullscreenOptimization = new FullscreenOptimization(getSupportActionBar(), getWindow().getDecorView());
        fullscreenOptimization.Optimization();

        new RetrofitConnect().CreateConnection();
        JSON_API_Placeholder service = RetrofitConnect.json_api_placeholder;

        Call<ChatsPOJO> call = service.getUserChats(SignInActivity.token);
        call.enqueue(new Callback<ChatsPOJO>()
        {
            @Override
            public void onResponse(Call<ChatsPOJO> call, Response<ChatsPOJO> response)
            {
                String chatName = response.body().chatName;
                TextView chatNameTextView = findViewById(R.id.ChatNameTextView);
                chatNameTextView.setText(chatName);
            }

            public void onFailure(Call<ChatsPOJO> call, Throwable t)
            {
                RetrofitConnect.CreateErrorMessage(MenuBottomNavigationActivity.this,
                        "Ошибка подключения к серверу", t.getMessage());
            }
        });
    }

}