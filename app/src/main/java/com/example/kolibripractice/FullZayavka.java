package com.example.kolibripractice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import io.grpc.internal.DnsNameResolver;

public class FullZayavka extends AppCompatActivity {
private TextView idx,nazvax,pricax,opisankax,imyax,familyx,numberx;
private TextView statusText,numberx2;
private ImageView imageView19;
private Button payringBtn;
    private DatabaseReference mBase;
    private DatabaseReference mBase2;
    private String GROUPKEY ="Orders";
    private String GROUPKEY2 =null;

    private Uri uploadUri2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_zayavka);
        init();
    }

    public void init(){
        mBase = FirebaseDatabase.getInstance().getReference(GROUPKEY);
        statusText = findViewById(R.id.statusText);
        idx = findViewById(R.id.idx);
        nazvax = findViewById(R.id.nazvax);
        pricax = findViewById(R.id.pricax);
        payringBtn = findViewById(R.id.payringBtn);
        opisankax= findViewById(R.id.opisankax);
        numberx2 = findViewById(R.id.numberx2);
        imyax = findViewById(R.id.imyax);
        familyx = findViewById(R.id.familyx);
        numberx = findViewById(R.id.numberx);
        imageView19 = findViewById(R.id.imageView19);
        payringBtn.setVisibility(View.GONE);

        getIntentMain();
    }
    private void getIntentMain(){
        Intent is = getIntent();
        if (is!=null){
            Picasso.get().load(is.getStringExtra("img")).into(imageView19);
            nazvax.setText(is.getStringExtra("nazvar"));
            String scamchecker = is.getStringExtra("roleid");
            if (scamchecker!=null){
              statusText.setVisibility(View.GONE);
                numberx2.setText("Id заявки");
                payringBtn.setVisibility(View.VISIBLE);
            }
            pricax.setText(is.getStringExtra("pricar"));
            opisankax.setText(is.getStringExtra("opisakar"));
            imyax.setText(is.getStringExtra("imyar"));
            familyx.setText(is.getStringExtra("familyr"));
            numberx.setText(is.getStringExtra("numberr"));
            idx.setText(is.getStringExtra("id"));
            uploadUri2 = Uri.parse(is.getStringExtra("img"));






        }
    }
    private void saveData() {
        String id = mBase2.push().getKey();
        String nazvanie = nazvax.getText().toString();
        String opisanie=opisankax.getText().toString();
        String price=pricax.getText().toString();
        String imya=imyax.getText().toString();
        String family=familyx.getText().toString();
        String number=numberx.getText().toString();
        String uploadUri = uploadUri2.toString();



        ClassAction2 addac = new ClassAction2(id, nazvanie, opisanie, price, uploadUri.toString(), imya, family, number);
        if (!TextUtils.isEmpty(nazvanie)) {
            if (nazvanie != null) mBase2.child(id).setValue(addac);



        } else {
            Toast.makeText(FullZayavka.this, "Возможно некоторые поля пустые!", Toast.LENGTH_SHORT).show();
        }
    }

    public void AdminChangeStatus(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(FullZayavka.this);
        builder.setTitle("Изменить статус")
                .setCancelable(false)
                .setIcon(R.drawable.useric)
                .setMessage("Выберите один из доступных статусов, он будет изменен в таблице БД, не забудьте нажать сохранить перед выходом!")
                .setPositiveButton("Удалить заявку",

                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int id) {


                                GROUPKEY2 ="Deleted";
                                mBase2 = FirebaseDatabase.getInstance().getReference(GROUPKEY2);

                                Toast.makeText(FullZayavka.this,"Успех",Toast.LENGTH_SHORT).show();

                            }

                        })
                .setNegativeButton("Отклонена",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int id) {

                                GROUPKEY2 ="Declined";
                                mBase2 = FirebaseDatabase.getInstance().getReference(GROUPKEY2);

                                Toast.makeText(FullZayavka.this,"Успех",Toast.LENGTH_SHORT).show();
                            }
                        })
                .setNeutralButton("Выполнена",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int id) {

                                GROUPKEY2 ="Completed";
                                mBase2 = FirebaseDatabase.getInstance().getReference(GROUPKEY2);

                                Toast.makeText(FullZayavka.this,"Успех",Toast.LENGTH_SHORT).show();
                            }
                        });

        AlertDialog alert =builder.create();
        alert.show();
    }

    public void svbtn(View view) {
        saveData();
    }

    public void call(View view) {
        Uri number = Uri.parse("tel:88005553535");
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        startActivity(callIntent);

    }

    public void gchat(View view) {
        Intent intent = new Intent(FullZayavka.this, GroupChat.class);
        startActivity(intent);
    }

    public void payZakaz(View view) {
        Intent intent = new Intent(FullZayavka.this,Payeed.class);
        startActivity(intent);
        String url = "https://invoicenpd.nalog.ru/?inn=362903058690&uuid=90fab15f-6994-43e7-83c3-ee9cd0e12234";
        Intent openPage= new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(openPage);

    }
}
