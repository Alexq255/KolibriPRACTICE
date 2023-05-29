package com.example.kolibripractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ComplededzayavkaAct extends AppCompatActivity {
    private DatabaseReference mBase;

    private String GROUPKEY ="Completed";

    private ListView complist;

    private ArrayAdapter<String> adapter;
    private List<String> listdata;
    private List<ClassAction2> listTemp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complededzayavka);
        init();
    }
    public void init(){
       complist = findViewById(R.id.complist);
        listdata = new ArrayList<>();
        listTemp = new ArrayList<ClassAction2>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listdata);
        complist.setAdapter(adapter);
        mBase = FirebaseDatabase.getInstance().getReference(GROUPKEY);
        getDataFromDB();


    }

    private void getDataFromDB() {
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (listdata.size()>0)listdata.clear();
                for (DataSnapshot ds:snapshot.getChildren()) {
                    ClassAction2 msg = ds.getValue(ClassAction2.class);
                    assert msg !=null;
                    listdata.add(msg.nazvanie);

                }
                adapter.notifyDataSetChanged();





            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        mBase.addValueEventListener(vListener);
    }
}