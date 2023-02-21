package com.example.kolibripractice;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import io.grpc.internal.DnsNameResolver;

public class MainMenu extends AppCompatActivity {
    private TextView twProfilename,twProfilename2;
     ListView acList;
private String Role = null;
private int Roleid = 0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        twProfilename = findViewById(R.id.twProfilename);
        Intent i = getIntent();
        Intent intent = getIntent();


        twProfilename.setText(i.getStringExtra("UserData"));
        String position = i.getStringExtra("UserDataNum");


        switch(Integer.parseInt(position)) {
            case 1:
                twProfilename.setTextColor(Color.parseColor("#FF0000"));
                Role = "Админ";
                Roleid =1;
                break;
            case 2:

                Roleid =2; //user;
                Role = "Пользователь";
                break;
            case 3:

                Roleid =3; //user;
                Role = "Пользователь";
                break;
            case 4:

                break;



        }

        acList = findViewById(R.id.acList);
        ArrayList<Actions> arrayList = new ArrayList<>();
        arrayList.add(new Actions(R.drawable.useric,"Tinkoff","Карта лучшего банка в стране"));
        arrayList.add(new Actions(R.drawable.useric,"Tinkoff","Карта лучшего банка в стране"));
        arrayList.add(new Actions(R.drawable.useric,"Tinkoff","Карта лучшего банка в стране"));
        arrayList.add(new Actions(R.drawable.useric,"Tinkoff","Карта лучшего банка в стране"));
        arrayList.add(new Actions(R.drawable.useric,"Tinkoff","Карта лучшего банка в стране"));
        arrayList.add(new Actions(R.drawable.useric,"Tinkoff","Карта лучшего банка в стране"));

        AdapterList adapters = new AdapterList(this,R.layout.list_row,arrayList);
        acList.setAdapter(adapters);


    }

    public void Profile(View view) {
Intent intent = new Intent(MainMenu.this,ProfileActivity.class);
        intent.putExtra("ifAdmin","Роль:"+Role);
        intent.putExtra("Roleid","Роль:"+Roleid);
startActivity(intent);


    }

    public void messenger(View view) {
        Intent intent = new Intent(MainMenu.this,GroupChat.class);
        startActivity(intent);
    }
}