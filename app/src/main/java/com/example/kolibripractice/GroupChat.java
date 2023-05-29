package com.example.kolibripractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class GroupChat extends AppCompatActivity {
    private DatabaseReference mBase;
    private EditText message_input;
    private String GROUPKEY ="Messages";

    private ListView messageList;
    private String name;
    private ArrayAdapter<String> adapter;
    private List<String> listdata;
    private TextView nameup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);
        message_input = findViewById(R.id.message_input);
        messageList = findViewById(R.id.messageList);

        listdata = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listdata);
        messageList.setAdapter(adapter);
        mBase = FirebaseDatabase.getInstance().getReference(GROUPKEY);
        getDataFromDB();
        Intent i = getIntent();
        name = i.getStringExtra("Pname");
        nameup = findViewById(R.id.nameup);
        nameup.setText("Здравствуйте,"+i.getStringExtra("Pname"));





    }
    private void getDataFromDB(){
       ValueEventListener vListener = new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               if (listdata.size()>0)listdata.clear();

            for (DataSnapshot ds:snapshot.getChildren()) {
                MessagesClass msg = ds.getValue(MessagesClass.class);
                assert msg !=null;
                listdata.add(msg.message);
            }
            adapter.notifyDataSetChanged();





           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       };

      mBase.addValueEventListener(vListener);
    }




    private void saveData() {
        String id = mBase.push().getKey();
        String message =name+ message_input.getText().toString();


        MessagesClass msgadd = new MessagesClass(id,message);
        if (!TextUtils.isEmpty(message)) {
            if (message != null) mBase.child(id).setValue(msgadd);


        } else {
            Toast.makeText(GroupChat.this, "Возможно некоторые поля пустые!", Toast.LENGTH_SHORT).show();
        }
    }

    public void Send(View view) {
        saveData();
    }

}
