package com.example.kolibripractice;

import static com.example.kolibripractice.R.id.textViewtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class Actionlister extends AppCompatActivity {
private TextView textViewtest;

    private DatabaseReference mBase;

    private String GROUPKEY ="Acticons";

    private ListView acclist;

    private ArrayAdapter<String> adapter;
    private List<String> listdata;
    private List<ClassAction> listTemp;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actionlister);
        textViewtest =findViewById(R.id.textViewtest);
        acclist = findViewById(R.id.acclist);
init();
       // Intent is = getIntent();
        //textViewtest.setText(is.getStringExtra("ItemId"));
       // String position = is.getStringExtra("ItemId");
       // Toast.makeText(Actionlister.this,"Переходим к списку услуг банка:"+position,Toast.LENGTH_SHORT).show();
    }
    public void init(){
        listdata = new ArrayList<>();
        listTemp = new ArrayList<ClassAction>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listdata);
        acclist.setAdapter(adapter);
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
                    ClassAction msg = ds.getValue(ClassAction.class);
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
        acclist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ClassAction tovarAdd =listTemp.get(i);
                Intent is = new Intent(Actionlister.this, FullAcAct.class);
                is.putExtra("img",tovarAdd.uploadUri);
                is.putExtra("id",tovarAdd.id);
                is.putExtra("nazvar",tovarAdd.nazvanie);
                is.putExtra("pricar",tovarAdd.price);
                is.putExtra("opisakar",tovarAdd.opisanie);
                startActivity(is);

            }
        });
    }
}