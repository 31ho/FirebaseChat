package com.example.britz.firebasechat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.britz.firebasechat.data.Data;
import com.example.britz.firebasechat.data.MessageHolder;

import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter {

    Data data;
    ArrayList<Data> data_list;
    MessageHolder holder;

    public  RecyclerAdapter(){}

    public  RecyclerAdapter(Data data){
        this.data = data;
    }


    public RecyclerAdapter (ArrayList<Data> data_list){
        this.data_list = data_list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_masseage, parent,false);
        MessageHolder holder = new MessageHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        this.holder = (MessageHolder) holder;
        this.holder.bindView(data_list.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return data_list.size();
    }
}
