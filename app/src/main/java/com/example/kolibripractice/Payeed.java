package com.example.kolibripractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Payeed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payeed);
    }

    public void glavka(View view) {
        Intent intent = new Intent(Payeed.this,MainMenu.class);
        startActivity(intent);
    }
}