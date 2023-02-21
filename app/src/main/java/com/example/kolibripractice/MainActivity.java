package com.example.kolibripractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void RegisterBtn(View view) {
        Intent intent = new Intent(MainActivity.this, ClassicRegisterActivity.class);
        startActivity(intent);
    }

    public void Login(View view) {
   Intent intent = new Intent(MainActivity.this, LoginActivity.class);
   startActivity(intent);
    }
}