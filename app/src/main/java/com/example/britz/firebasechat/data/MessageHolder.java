package com.example.britz.firebasechat.data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.britz.firebasechat.R;

public class MessageHolder extends RecyclerView.ViewHolder {

    private TextView textView;
    private TextView textViewName;
    private TextView textViewTime;

    public MessageHolder(View itemView) {
        super(itemView);

        textView = (TextView) itemView.findViewById(R.id.holder_message);
        textViewName = (TextView) itemView.findViewById(R.id.holder_name);
        textViewTime = (TextView) itemView.findViewById(R.id.holder_time);
    }

    public void bindView(Context context, Data data){
        String name = data.getUser_name();
        if(data.getGoogle_ad_id()!=null && data.getGoogle_ad_id().equals(Pref.getInstance(context).getGoogleAdId())) {
            name += "(당신)";
        }
        textViewName.setText(name);
        textView.setText(data.getMessage());
        textViewTime.setText(data.getTime());
    }

}

