package com.example.kolibripractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class PasscodeVerify extends AppCompatActivity {
    private TextView textView42,textView43,textView44,textView45,textView46,textView40,textView39,textView41,textView38,textView36,textView37,textView35,textView33,textView32,textView34;
    private int inputCc;
    private String scamina;
    private int i;

    private static final String PREFS_FILE = "Account";
    private static final String PREF_NAME = "Name";
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passcode_verify);
        textView42 = findViewById(R.id.textView42);
        textView43 = findViewById(R.id.textView43);
        textView44 = findViewById(R.id.textView44);
        textView45 = findViewById(R.id.textView45);
        textView46 = findViewById(R.id.textView46);
        settings = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);
        scamina = settings.getString(PREF_NAME,"не определено");
        textView42.setText(scamina);
        textView40 = findViewById(R.id.textView40);
        textView39 = findViewById(R.id.textView39);
        textView41 = findViewById(R.id.textView41);
        textView38 = findViewById(R.id.textView38);
        textView36 = findViewById(R.id.textView36);
        textView37 = findViewById(R.id.textView37);
        textView35 = findViewById(R.id.textView35);
        textView33 = findViewById(R.id.textView33);
        textView32 = findViewById(R.id.textView32);
        textView34 = findViewById(R.id.textView34);
        Intent intentorner = getIntent();
        if (intentorner != null) {
            textView42.setText("NiceCock");
            scamina = (intentorner.getStringExtra("codefortransfer"));
            textView42.setText(scamina);
            SharedPreferences.Editor prefEditor = settings.edit();
            prefEditor.putString(PREF_NAME, scamina);
            prefEditor.apply();
        } else {

        }


    }

    public void code11(View view) {
        inputCc += 1;
        i++;
        test();
        test2();
        textView40.setBackgroundResource(R.drawable.point);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView40.setBackgroundResource(R.drawable.custombtn);

            }
        }, 250);
    }

    public void code21(View view) {
        inputCc += 2;
        i++;
        test();
        test2();
        textView39.setBackgroundResource(R.drawable.point);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView39.setBackgroundResource(R.drawable.custombtn);

            }
        }, 250);
    }

    public void code31(View view) {
        inputCc += 3;
        i++;
        test();
        test2();
        textView41.setBackgroundResource(R.drawable.point);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView41.setBackgroundResource(R.drawable.custombtn);

            }
        }, 250);
    }

    public void code41(View view) {
        inputCc += 4;
        i++;
        test();
        test2();
        textView38.setBackgroundResource(R.drawable.point);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView38.setBackgroundResource(R.drawable.custombtn);

            }
        }, 250);
    }

    public void code51(View view) {
        inputCc += 5;
        i++;
        test();
        test2();
        textView36.setBackgroundResource(R.drawable.point);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView36.setBackgroundResource(R.drawable.custombtn);

            }
        }, 250);
    }

    public void code61(View view) {
        inputCc += 6;
        i++;
        test();
        test2();
        textView37.setBackgroundResource(R.drawable.point);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView37.setBackgroundResource(R.drawable.custombtn);

            }
        }, 250);
    }

    public void code71(View view) {
        inputCc += 7;
        i++;
        test();
        test2();
        textView35.setBackgroundResource(R.drawable.point);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView35.setBackgroundResource(R.drawable.custombtn);

            }
        }, 250);
    }

    public void code81(View view) {
        inputCc += 8;
        i++;
        test();
        test2();
        textView33.setBackgroundResource(R.drawable.point);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView33.setBackgroundResource(R.drawable.custombtn);

            }
        }, 250);
    }

    public void code91(View view) {
        inputCc += 9;
        i++;
        test();
        test2();
        textView32.setBackgroundResource(R.drawable.point);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView32.setBackgroundResource(R.drawable.custombtn);

            }
        }, 250);
    }

    public void code01(View view) {
        inputCc += 10;
        i++;
        test();
        test2();
        textView34.setBackgroundResource(R.drawable.point);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView34.setBackgroundResource(R.drawable.custombtn);

            }
        }, 250);
    }

    public void test() {
        if (i >= 4) {
            int ForCheck = Integer.parseInt(scamina);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    if (ForCheck == inputCc) {
                        textView42.setText("ItsRightcode");
                    } else {
                        textView42.setText("pososi");
                    }
                }
            }, 300);


        }
        if (i == 0) {


        }
        if (i==0){

        }if(i==1){
            textView43.setBackgroundResource(R.drawable.point);
        }if(i==2){
            textView44.setBackgroundResource(R.drawable.point);
        }if(i==3){
            textView45.setBackgroundResource(R.drawable.point);
        }if(i==4){
            textView46.setBackgroundResource(R.drawable.point);
        }
    }
    public void test2(){
        int position =i;
        switch(position) {
            case 0:
                textView43.setBackgroundResource(R.drawable.custombtnwhite);
                break;
            case 1:
                textView44.setBackgroundResource(R.drawable.custombtnwhite);
                break;
            case 2:
                textView45.setBackgroundResource(R.drawable.custombtnwhite);
                break;
            case 3:
                textView46.setBackgroundResource(R.drawable.custombtnwhite);
                break;
            default:

                break;
        }

    }
}
