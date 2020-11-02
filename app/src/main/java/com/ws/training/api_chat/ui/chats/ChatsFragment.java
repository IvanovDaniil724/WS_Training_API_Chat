package com.ws.training.api_chat.ui.chats;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ws.training.api_chat.JSON_API_Placeholder;
import com.ws.training.api_chat.R;
import com.ws.training.api_chat.activities.SignInActivity;
import com.ws.training.api_chat.adapters.GetChatsAdapter;
import com.ws.training.api_chat.common.RetrofitConnect;
import com.ws.training.api_chat.pojo.ChatsPOJO;
import com.ws.training.api_chat.ui.CreateNewChatFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatsFragment extends Fragment {

    //ChatsViewModel chatsViewModel;
    private List<ChatsPOJO> chats;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_chats, container, false);

        /*chatsViewModel =
                ViewModelProviders.of(this).get(ChatsViewModel.class);

        final TextView textView = root.findViewById(R.id.ChatNameTextView);
        chatsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });*/

        new RetrofitConnect().CreateConnection();
        JSON_API_Placeholder service = RetrofitConnect.json_api_placeholder;

        Call<List<ChatsPOJO>> call = service.getUserChats(SignInActivity.token);
        call.enqueue(new Callback<List<ChatsPOJO>>()
        {
            @Override
            public void onResponse(Call<List<ChatsPOJO>> call, Response<List<ChatsPOJO>> response)
            {
                List<ChatsPOJO> chats_response = response.body();

                if (chats_response != null)
                {
                    chats.addAll(chats_response);

                    RecyclerView ChatNamesRecyclerView = root.findViewById(R.id.ChatsMenu_RecyclerView);
                    GetChatsAdapter adapter = new GetChatsAdapter(root.getContext(), R.layout.fragment_chat_names, chats);
                    ChatNamesRecyclerView.setAdapter(adapter);
                    ChatNamesRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
                }
            }

            public void onFailure(Call<List<ChatsPOJO>> call, Throwable t)
            {
                RetrofitConnect.CreateErrorMessage(root.getContext(),
                        "Ошибка подключения к серверу", t.getMessage());
            }
        });

        final CreateNewChatFragment createNewChatFragment = new CreateNewChatFragment();
        root.findViewById(R.id.CreateNewChat_Button).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //createNewChatFragment.startPostponedEnterTransition();
                //ChatsFragment.this.setTargetFragment(createNewChatFragment, 0);

                Navigation.findNavController(root).navigate(R.id.action_navigation_chats_to_createNewChatFragment);
                BottomNavigationView navBar = getActivity().findViewById(R.id.nav_view); navBar.setVisibility(View.GONE);

                //FragmentTransaction ft = getFragmentManager().beginTransaction();

                //ft.show(createNewChatFragment);
                //ft.replace(root.getId(), new CreateNewChatFragment());
                //ft.hide(ChatsFragment.this);
                //ft.commit();
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        BottomNavigationView navBar = getActivity().findViewById(R.id.nav_view); navBar.setVisibility(View.VISIBLE);
    }
}