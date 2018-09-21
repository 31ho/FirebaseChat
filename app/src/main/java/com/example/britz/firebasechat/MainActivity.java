package com.example.britz.firebasechat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.britz.firebasechat.data.Data;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ArrayList<Data> list;
    RecyclerAdapter adapter;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<Data>();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_recycler);
//        RecyclerAdapter adapter = new RecyclerAdapter(list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new RecyclerAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        editText = (EditText) findViewById(R.id.main_chat_edittext);
        Button button = (Button) findViewById(R.id.main_chat_send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add(new Data("ho",editText.getText().toString(),"11:00"));
                adapter.notifyDataSetChanged();
            }
        });

    }
}
