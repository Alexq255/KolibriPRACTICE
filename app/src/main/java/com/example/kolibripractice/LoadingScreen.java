package com.example.kolibripractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

public class LoadingScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ConnectivityManager connectivityManager = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    Toast toast1 = Toast.makeText(getApplicationContext(),
                            "Успешное подключение",Toast.LENGTH_LONG);
                    toast1.show();
                    Intent intent = new Intent(LoadingScreen.this, PasscodeVerify.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast toast2 = Toast.makeText(getApplicationContext(),
                            "Нет подключения\n Повт попытку",Toast.LENGTH_LONG);
                    Intent intent = new Intent(LoadingScreen.this,NoInternet.class);
                    startActivity(intent);
                    toast2.show();
                }

            }
        }, 3000);



    }
    }


