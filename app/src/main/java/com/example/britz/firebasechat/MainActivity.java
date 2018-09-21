package com.example.britz.firebasechat;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.britz.firebasechat.data.Data;
import com.example.britz.firebasechat.data.Pref;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference = firebaseDatabase.getInstance().getReference();

    ArrayList<Data> list;
    RecyclerAdapter adapter;
    RecyclerView recyclerView;
    EditText editText;
    Pref pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = Pref.getInstance(this);
        list = new ArrayList<Data>();

        recyclerView = (RecyclerView) findViewById(R.id.main_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new RecyclerAdapter(this, list);
        recyclerView.setAdapter(adapter);

        editText = (EditText) findViewById(R.id.main_chat_edittext);
        Button button = (Button) findViewById(R.id.main_chat_send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String nowTime = df.format(c.getTime());
                Data data = new Data(pref.getGoogleAdId(), pref.getUserName(), editText.getText().toString(), nowTime);

                databaseReference.child("data").push().setValue(data);
                editText.setText("");
            }
        });

        databaseReference.child("data").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Data data = dataSnapshot.getValue(Data.class);
                    list.add(data);
                    adapter.notifyDataSetChanged();
                    recyclerView.smoothScrollToPosition(list.size() - 1);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        if (Build.VERSION.SDK_INT >= 11) {
            recyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v,
                                           int left, int top, int right, int bottom,
                                           int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    if (bottom < oldBottom) {
                        recyclerView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                recyclerView.smoothScrollToPosition(list.size());
                            }
                        }, 100);
                    }
                }
            });
        }
    }
}
