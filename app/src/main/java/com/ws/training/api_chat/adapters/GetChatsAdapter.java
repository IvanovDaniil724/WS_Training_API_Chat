package com.ws.training.api_chat.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GetChatsAdapter extends RecyclerView.Adapter<GetChatsAdapter.ChatsViewHolder>
{

    @NonNull
    @Override
    public GetChatsAdapter.ChatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull GetChatsAdapter.ChatsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ChatsViewHolder extends RecyclerView.ViewHolder
    {


        public ChatsViewHolder(@NonNull View itemView)
        {
            super(itemView);
        }
    }
}
