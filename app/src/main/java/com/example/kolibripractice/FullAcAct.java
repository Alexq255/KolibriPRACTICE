package com.example.kolibripractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class FullAcAct extends AppCompatActivity {
private TextView nazva,prica,opisaka;
private ImageView tovarimg;
private String GROUPKEY ="Orders";
private Uri uloadUri2;
private EditText edName2h2,edName2h3,edName2h4;
private DatabaseReference mBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_ac);
        init();
    }
    public void init(){
        nazva = findViewById(R.id.nazva);
        prica = findViewById(R.id.prica);
        opisaka = findViewById(R.id.opisaka);
        tovarimg = findViewById(R.id.tovarimg);
        mBase = FirebaseDatabase.getInstance().getReference(GROUPKEY);
        getIntentMain();
        edName2h2 = findViewById(R.id.edName2h2);
        edName2h3 = findViewById(R.id.edName2h3);
        edName2h4 = findViewById(R.id.edName2h4);
    }
    private void getIntentMain(){
        Intent is = getIntent();
        if (is!=null){
            Picasso.get().load(is.getStringExtra("img")).into(tovarimg);
            nazva.setText(is.getStringExtra("nazvar"));
            prica.setText(is.getStringExtra("pricar"));
            opisaka.setText(is.getStringExtra("opisakar"));
            uloadUri2 = Uri.parse(is.getStringExtra("img"));



        }
    }
    private void saveData() {
        String id = mBase.push().getKey();
        String nazvanie = nazva.getText().toString();
        String opisanie=opisaka.getText().toString();
        String price=prica.getText().toString();
        String imya=edName2h2.getText().toString();
        String family=edName2h3.getText().toString();
        String number=edName2h4.getText().toString();
        String uploadUri = uloadUri2.toString();



        ClassAction2 addac = new ClassAction2(id, nazvanie, opisanie, price, uploadUri.toString(), imya, family, number);
        if (!TextUtils.isEmpty(nazvanie)) {
            if (nazvanie != null) mBase.child(id).setValue(addac);



        } else {
            Toast.makeText(FullAcAct.this, "Возможно некоторые поля пустые!", Toast.LENGTH_SHORT).show();
        }
    }

    public void savedataa(View view) {
        saveData();
        Intent intent = new Intent(FullAcAct.this, sendOtpActivity.class);
        startActivity(intent);
    }
}