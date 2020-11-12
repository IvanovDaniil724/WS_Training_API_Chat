package com.ws.training.api_chat.ui;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ws.training.api_chat.JSON_API_Placeholder;
import com.ws.training.api_chat.R;
import com.ws.training.api_chat.activities.SignInActivity;
import com.ws.training.api_chat.adapters.GetAllUsersAdapter;
import com.ws.training.api_chat.adapters.GetChatsAdapter;
import com.ws.training.api_chat.common.RetrofitConnect;
import com.ws.training.api_chat.pojo.ChatsPOJO;
import com.ws.training.api_chat.pojo.RegistrationPOJO;
import com.ws.training.api_chat.pojo.UsersPOJO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateNewChatFragment extends Fragment
{
    List<UsersPOJO> allUsers;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_create_new_chat, container, false);

        JSON_API_Placeholder service = RetrofitConnect.json_api_placeholder;

        Call<List<UsersPOJO>> call = service.getAllUsers(SignInActivity.token);
        call.enqueue(new Callback<List<UsersPOJO>>()
        {
            @Override
            public void onResponse(Call<List<UsersPOJO>> call, Response<List<UsersPOJO>> response)
            {
                allUsers = response.body();

                RecyclerView CreateNewChat_AddMembers_RecyclerView = root.findViewById(R.id.CreateNewChat_AddMembers_RecyclerView);
                GetAllUsersAdapter adapter = new GetAllUsersAdapter(root.getContext(), R.layout.select_users_item, allUsers);
                CreateNewChat_AddMembers_RecyclerView.setAdapter(adapter);
                CreateNewChat_AddMembers_RecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
            }

            public void onFailure(Call<List<UsersPOJO>> call, Throwable t)
            {
                RetrofitConnect.CreateErrorMessage(root.getContext(),
                        "Ошибка подключения к серверу", t.getMessage());
            }
        });

        return root;
    }
}