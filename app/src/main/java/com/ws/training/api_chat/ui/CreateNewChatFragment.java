package com.ws.training.api_chat.ui;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.ws.training.api_chat.JSON_API_Placeholder;
import com.ws.training.api_chat.R;
import com.ws.training.api_chat.activities.SignInActivity;
import com.ws.training.api_chat.adapters.GetAllUsersAdapter;
import com.ws.training.api_chat.adapters.GetChatsAdapter;
import com.ws.training.api_chat.common.RetrofitConnect;
import com.ws.training.api_chat.pojo.ChatsPOJO;
import com.ws.training.api_chat.pojo.CreateChatDataPOJO;
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
                GetAllUsersAdapter allUsersAdapter = new GetAllUsersAdapter(root.getContext(), R.layout.select_users_item, allUsers);
                CreateNewChat_AddMembers_RecyclerView.setAdapter(allUsersAdapter);
                CreateNewChat_AddMembers_RecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

                root.findViewById(R.id.CreateNewChat_Button).setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        //RetrofitConnect.CreateErrorMessage(root.getContext(), "selectedUserIds", String.valueOf(allUsersAdapter.selectedUserIds));

                        EditText CreateNewChat_ChatName_EditText = root.findViewById(R.id.CreateNewChat_ChatName_EditText);
                        if (CreateNewChat_ChatName_EditText.getText().toString().equals(""))
                        {
                            RetrofitConnect.CreateErrorMessage(root.getContext(), "Отсутствует название чата",
                                    "Для создания нового чата требуется ввести его название"); return;
                        }
                        else
                        {
                            /*ChatsPOJO newChat = new ChatsPOJO(SignInActivity.chatsCount + 50,
                                    CreateNewChat_ChatName_EditText.getText().toString(),
                                    null, allUsersAdapter.selectedUsers);*/ //allUsersAdapter.selectedUserIds);

                            //if (newChat.getChatIconName() == null) { newChat.chatIconName = "null"; }

                            /*ChatsPOJO newChat = new ChatsPOJO(CreateNewChat_ChatName_EditText.getText().toString(),
                                    allUsersAdapter.selectedUsers);*/

                            //RetrofitConnect.CreateErrorMessage(root.getContext(), "newChat", newChat.toString());

                            //RetrofitConnect.CreateErrorMessage(root.getContext(), "usersIds",
                            //        String.valueOf(newChat.userIds[1]));

                            //Call<ChatsPOJO> call = service.createNewChat(SignInActivity.token, newChat);
                            Call<ChatsPOJO> call = service.createNewChat(SignInActivity.token,
                                    new CreateChatDataPOJO(SignInActivity.token, allUsersAdapter.selectedUserIds));
                            call.enqueue(new Callback<ChatsPOJO>()
                            {
                                @Override
                                public void onResponse(Call<ChatsPOJO> call, Response<ChatsPOJO> response)
                                {
                                    //SignInActivity.chatsCount++;
                                    //SharedPreferences.Editor editor = SignInActivity.settings.edit();
                                    //editor.putInt(SignInActivity.APP_PREFERENCES_CHATS_COUNT, SignInActivity.chatsCount);
                                    //editor.apply();
                                }

                                public void onFailure(Call<ChatsPOJO> call, Throwable t)
                                {
                                    RetrofitConnect.CreateErrorMessage(root.getContext(),
                                            "Ошибка подключения к серверу", t.getMessage());
                                }
                            });
                        }
                    }
                });
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