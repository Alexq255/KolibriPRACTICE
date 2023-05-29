package com.example.kolibripractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void Add(View view) {
        Intent intent = new Intent(AdminActivity.this,AdminAddAction.class);
        startActivity(intent);
    }

    public void Requests(View view) {
     Intent intent = new Intent(AdminActivity.this,Zayavki.class);
     startActivity(intent);
    }

    public void back(View view) {
     Intent intent = new Intent(AdminActivity.this, MainActivity.class);
     startActivity(intent);
    }

    public void Completed(View view) {
      Intent intent = new Intent(AdminActivity.this, ComplededzayavkaAct.class);
      startActivity(intent);
    }
}