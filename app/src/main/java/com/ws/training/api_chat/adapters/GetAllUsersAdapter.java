package com.ws.training.api_chat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.ws.training.api_chat.R;
import com.ws.training.api_chat.common.RetrofitConnect;
import com.ws.training.api_chat.pojo.UsersPOJO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.ws.training.api_chat.common.RetrofitConnect.BASE_URL;

public class GetAllUsersAdapter extends RecyclerView.Adapter<GetAllUsersAdapter.ChatsViewHolder>
{
    private int inflate_fragment_id;
    private LayoutInflater inflater;
    private List<UsersPOJO> users;
    private Context context;

    public LinkedList<Long> selectedUserIds;
    //public LinkedList<UsersPOJO> selectedUsers;

    public GetAllUsersAdapter(Context context, int inflate_fragment_id, List<UsersPOJO> users)
    {
        this.users = users;
        this.inflate_fragment_id = inflate_fragment_id;
        this.inflater = LayoutInflater.from(context);
        this.context = context;

        this.selectedUserIds = new LinkedList<>();
        //this.selectedUsers = new LinkedList<>();
    }

    @NonNull
    @Override
    public GetAllUsersAdapter.ChatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = inflater.inflate(inflate_fragment_id, parent, false);
        return new GetAllUsersAdapter.ChatsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatsViewHolder holder, int position)
    {
        UsersPOJO user = users.get(position);

        holder.UserName_TextView.setText(user.getFirstName() + " " + user.getLastName());
        Picasso.get().load(BASE_URL + "/profile/getImage/" + user.getAvatarName()).into(holder.UserAvatar_ImageView);

        holder.SelectUsers_ConstraintLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                holder.SelectUser_ToggleButton.setChecked(!holder.SelectUser_ToggleButton.isChecked());
            }
        });

        holder.SelectUser_ToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if (b) { /*selectedUsers.add(user);*/ selectedUserIds.add((long) user.getUserId()); }
                else { /*selectedUsers.remove(user);*/ selectedUserIds.remove((long) user.getUserId()); }
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class ChatsViewHolder extends RecyclerView.ViewHolder
    {
        final ConstraintLayout SelectUsers_ConstraintLayout;
        final ImageView UserAvatar_ImageView;
        final TextView UserName_TextView;
        final ToggleButton SelectUser_ToggleButton;

        public ChatsViewHolder(@NonNull View itemView)
        {
            super(itemView);
            SelectUsers_ConstraintLayout = itemView.findViewById(R.id.SelectUsers_ConstraintLayout);
            UserAvatar_ImageView = itemView.findViewById(R.id.UserAvatar_ImageView);
            UserName_TextView = itemView.findViewById(R.id.UserName_TextView);
            SelectUser_ToggleButton = itemView.findViewById(R.id.SelectUser_ToggleButton);
        }
    }
}
