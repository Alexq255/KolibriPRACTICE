package com.example.kolibripractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;


public class passwordActivity extends AppCompatActivity {
    private int code;
    private static final String codeForSave = "Account";
    private String forputty;
    SharedPreferences savedcode;
    private int i;

    private TextView textView11, textView15, textView14, textView18, textView17, textView22, textView21, textView20, textView13, textView19, textView24, textView25, textView26, textView27, textView23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        savedcode = getSharedPreferences(codeForSave, MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        textView11 = findViewById(R.id.textView11);
        textView15 = findViewById(R.id.textView15);
        textView14 = findViewById(R.id.textView14);
        textView18 = findViewById(R.id.textView18);
        textView17 = findViewById(R.id.textView17);
        textView22 = findViewById(R.id.textView22);
        textView21 = findViewById(R.id.textView21);
        textView20 = findViewById(R.id.textView20);
        textView13 = findViewById(R.id.textView13);
        textView19 = findViewById(R.id.textView19);

        textView24 = findViewById(R.id.textView24);
        textView25 = findViewById(R.id.textView25);
        textView26 = findViewById(R.id.textView26);
        textView27 = findViewById(R.id.textView27);
        textView23 = findViewById(R.id.textView23);

        String name = savedcode.getString(codeForSave, "не определено");
        textView23.setText(name);




    }

    public void code1(View view) {
        i++;
        test();

        textView11.setBackgroundResource(R.drawable.point);
        Handler handler = new Handler();
        code += 1;
        SharedPreferences.Editor prefEditor = savedcode.edit();
        prefEditor.putString(codeForSave, String.valueOf(code));//code
        prefEditor.apply();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView11.setBackgroundResource(R.drawable.custombtn);

            }
        }, 250);
    }


    public void code2(View view) {
        i++;
        test();
        code += 2;
        forputty = String.valueOf(code);
        SharedPreferences.Editor prefEditor = savedcode.edit();
        prefEditor.putString(codeForSave, String.valueOf(code));
        prefEditor.apply();
        textView15.setBackgroundResource(R.drawable.point);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView15.setBackgroundResource(R.drawable.custombtn);

            }
        }, 250);
        String name = savedcode.getString(codeForSave, "не определено");
        textView23.setText(name);
    }

    public void code3(View view) {
        i++;
        test();
        code += 3;
        SharedPreferences.Editor prefEditor = savedcode.edit();
        prefEditor.putString(codeForSave, String.valueOf(code));
        prefEditor.apply();
        textView14.setBackgroundResource(R.drawable.point);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView14.setBackgroundResource(R.drawable.custombtn);

            }
        }, 250);
        String name = savedcode.getString(codeForSave, "не определено");
        textView23.setText(name);
    }

    public void code4(View view) {
        i++;
        test();
        code += 4;
        SharedPreferences.Editor prefEditor = savedcode.edit();
        prefEditor.putString(codeForSave, String.valueOf(code));
        prefEditor.apply();
        textView19.setBackgroundResource(R.drawable.point);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView19.setBackgroundResource(R.drawable.custombtn);

            }
        }, 250);
        String name = savedcode.getString(codeForSave, "не определено");
        textView23.setText(name);
    }

    public void code5(View view) {
        i++;
        test();
        code += 5;
        SharedPreferences.Editor prefEditor = savedcode.edit();
        prefEditor.putString(codeForSave, String.valueOf(code));
        prefEditor.apply();
        textView18.setBackgroundResource(R.drawable.point);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView18.setBackgroundResource(R.drawable.custombtn);

            }
        }, 250);
        String name = savedcode.getString(codeForSave, "не определено");
        textView23.setText(name);
    }

    public void code6(View view) {
        i++;
        test();
        code += 6;
        SharedPreferences.Editor prefEditor = savedcode.edit();
        prefEditor.putString(codeForSave, String.valueOf(code));
        prefEditor.apply();
        textView17.setBackgroundResource(R.drawable.point);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView17.setBackgroundResource(R.drawable.custombtn);

            }
        }, 250);
        String name = savedcode.getString(codeForSave, "не определено");
        textView23.setText(name);
    }

    public void code7(View view) {
        i++;
        test();
        code += 7;
        SharedPreferences.Editor prefEditor = savedcode.edit();
        prefEditor.putString(codeForSave, String.valueOf(code));
        prefEditor.apply();
        textView22.setBackgroundResource(R.drawable.point);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView22.setBackgroundResource(R.drawable.custombtn);

            }
        }, 250);
        String name = savedcode.getString(codeForSave, "не определено");
        textView23.setText(name);
    }

    public void code8(View view) {
        i++;
        test();
        code += 8;
        SharedPreferences.Editor prefEditor = savedcode.edit();
        prefEditor.putString(codeForSave, String.valueOf(code));
        prefEditor.apply();
        textView21.setBackgroundResource(R.drawable.point);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView21.setBackgroundResource(R.drawable.custombtn);

            }
        }, 250);
        String name = savedcode.getString(codeForSave, "не определено");
        textView23.setText(name);
    }

    public void code9(View view) {
        i++;
        test();
        code += 9;
        SharedPreferences.Editor prefEditor = savedcode.edit();
        prefEditor.putString(codeForSave, String.valueOf(code));
        prefEditor.apply();
        textView20.setBackgroundResource(R.drawable.point);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView20.setBackgroundResource(R.drawable.custombtn);

            }
        }, 250);
        String name = savedcode.getString(codeForSave, "не определено");
        textView23.setText(name);
    }

    public void code0(View view) {
        i++;
        test();
        code += 10;
        SharedPreferences.Editor prefEditor = savedcode.edit();
        prefEditor.putString(codeForSave, String.valueOf(code));
        prefEditor.apply();
        textView13.setBackgroundResource(R.drawable.point);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView13.setBackgroundResource(R.drawable.custombtn);

            }
        }, 250);
        String name = savedcode.getString(codeForSave, "не определено");
        textView23.setText(name);
    }

    public void test() {
        if (i >= 4) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intentorner = new Intent(passwordActivity.this, PasscodeVerify.class);
                    forputty = String.valueOf(code);
                    intentorner.putExtra("codefortransfer", forputty);
                    startActivity(intentorner);
                }
            }, 300);


        }
        if (i == 0) {

        }
        if (i == 1) {
            textView24.setBackgroundResource(R.drawable.point);
        }
        if (i == 2) {
            textView25.setBackgroundResource(R.drawable.point);
        }
        if (i == 3) {
            textView26.setBackgroundResource(R.drawable.point);
        }
        if (i == 4) {
            textView27.setBackgroundResource(R.drawable.point);
        }

    }

    public void test2() {
        int position = i;
        switch (position) {
            case 0:
                textView24.setBackgroundResource(R.drawable.custombtnwhite);
                break;
            case 1:
                textView25.setBackgroundResource(R.drawable.custombtnwhite);
                break;
            case 2:
                textView26.setBackgroundResource(R.drawable.custombtnwhite);
                break;
            case 3:
                textView27.setBackgroundResource(R.drawable.custombtnwhite);
                break;
            default:

                break;
        }

    }

    public void clear(View view) {
        i--;
        test2();
    }

    public void test22() {

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intentorner = new Intent(passwordActivity.this, PasscodeVerify.class);
                    forputty = String.valueOf(code);
                    intentorner.putExtra("codefortransfer", forputty);
                    startActivity(intentorner);
                }
            }, 300);


    }
}