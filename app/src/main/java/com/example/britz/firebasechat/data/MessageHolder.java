package com.example.britz.firebasechat.data;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.britz.firebasechat.R;

import org.w3c.dom.Text;

public class MessageHolder extends RecyclerView.ViewHolder {

    private TextView textView;

    public MessageHolder(View itemView) {
        super(itemView);

        textView = (TextView) itemView.findViewById(R.id.holder_message);
    }

    public void bindView(String text){
        textView.setText(text);
    }

}

