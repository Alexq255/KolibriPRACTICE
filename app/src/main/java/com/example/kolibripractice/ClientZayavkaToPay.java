package com.example.kolibripractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ClientZayavkaToPay extends AppCompatActivity {
    private DatabaseReference mBase;

    private String GROUPKEY ="Orders";

    private ListView aclist3;

    private ArrayAdapter<String> adapter;
    private List<String> listdata;
    private List<ClassAction2> listTemp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_zayavka_to_pay);
        aclist3 = findViewById(R.id.aclist3);
        init();

    }
    public void init(){

        listdata = new ArrayList<>();
        listTemp = new ArrayList<ClassAction2>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listdata);
        aclist3.setAdapter(adapter);
        mBase = FirebaseDatabase.getInstance().getReference(GROUPKEY);
        getDataFromDB();
        setOnclickItem();

    }


    private void getDataFromDB(){
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (listdata.size()>0)listdata.clear();
                if (listTemp.size()<0)listTemp.clear();
                for (DataSnapshot ds:snapshot.getChildren()) {
                    ClassAction2 msg = ds.getValue(ClassAction2.class);
                    assert msg !=null;
                    listdata.add(msg.nazvanie);
                    listTemp.add(msg);
                }
                adapter.notifyDataSetChanged();





            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        mBase.addValueEventListener(vListener);
    }
    private void setOnclickItem(){
        aclist3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ClassAction2 tovarAdd =listTemp.get(i);
                Intent is = new Intent(ClientZayavkaToPay.this, FullZayavka.class);
                is.putExtra("img",tovarAdd.uploadUri);
                is.putExtra("id",tovarAdd.id);
                is.putExtra("nazvar",tovarAdd.nazvanie);
                is.putExtra("pricar",tovarAdd.price);
                is.putExtra("opisakar",tovarAdd.opisanie);
                is.putExtra("imyar",tovarAdd.imya);
                is.putExtra("familyr",tovarAdd.family);
                is.putExtra("numberr",tovarAdd.number);
                is.putExtra("roleid","scam");
                startActivity(is);

            }
        });
    }
}