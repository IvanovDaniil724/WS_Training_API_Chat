package com.ws.training.api_chat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ws.training.api_chat.R;
import com.ws.training.api_chat.pojo.ChatsPOJO;

import java.util.List;

public class GetChatsAdapter extends RecyclerView.Adapter<GetChatsAdapter.ChatsViewHolder>
{
    private int inflate_fragment_id;
    private LayoutInflater inflater;
    private List<ChatsPOJO> chats;
    private Context context;

    public GetChatsAdapter(Context context, int inflate_fragment_id, List<ChatsPOJO> chats)
    {
        this.chats = chats;
        this.inflate_fragment_id = inflate_fragment_id;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public GetChatsAdapter.ChatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = inflater.inflate(inflate_fragment_id, parent, false);
        return new GetChatsAdapter.ChatsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GetChatsAdapter.ChatsViewHolder holder, int position)
    {
        ChatsPOJO chat = chats.get(position);
        holder.chatNamesTextView.setText(chat.getChatName());
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    class ChatsViewHolder extends RecyclerView.ViewHolder
    {
        final TextView chatNamesTextView;

        public ChatsViewHolder(@NonNull View itemView)
        {
            super(itemView);
            chatNamesTextView = itemView.findViewById(R.id.ChatNameTextView);
        }
    }
}
