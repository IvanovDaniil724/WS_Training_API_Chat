package com.ws.training.api_chat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.ws.training.api_chat.R;
import com.ws.training.api_chat.pojo.ChatsPOJO;
import com.ws.training.api_chat.pojo.RegistrationPOJO;
import com.ws.training.api_chat.pojo.UsersPOJO;

import java.util.List;

import static com.ws.training.api_chat.common.RetrofitConnect.BASE_URL;

public class GetAllUsersAdapter extends RecyclerView.Adapter<GetAllUsersAdapter.ChatsViewHolder>
{
    private int inflate_fragment_id;
    private LayoutInflater inflater;
    private List<UsersPOJO> users;
    private Context context;

    public GetAllUsersAdapter(Context context, int inflate_fragment_id, List<UsersPOJO> users)
    {
        this.users = users;
        this.inflate_fragment_id = inflate_fragment_id;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public GetAllUsersAdapter.ChatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = inflater.inflate(inflate_fragment_id, parent, false);
        return new GetAllUsersAdapter.ChatsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GetAllUsersAdapter.ChatsViewHolder holder, int position)
    {
        UsersPOJO user = users.get(position);

        holder.UserName_TextView.setText(user.getFirstName() + " " + user.getLastName());
        //Picasso.with(context).load(BASE_URL + "/chats/allusers/" + user.getAvatarName());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class ChatsViewHolder extends RecyclerView.ViewHolder
    {
        final ImageView UserAvatar_ImageView;
        final TextView UserName_TextView;
        final ToggleButton SelectUser_ToggleButton;

        public ChatsViewHolder(@NonNull View itemView)
        {
            super(itemView);
            UserAvatar_ImageView = itemView.findViewById(R.id.UserAvatar_ImageView);
            UserName_TextView = itemView.findViewById(R.id.UserName_TextView);
            SelectUser_ToggleButton = itemView.findViewById(R.id.SelectUser_ToggleButton);
        }
    }
}
