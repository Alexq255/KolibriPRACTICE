package com.example.kolibripractice;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import io.grpc.internal.DnsNameResolver;

public class MainMenu extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore fstore;
    private String name;
    private TextView twProfilename,twProfilename2;
    private ImageView imageView7,imageView23,client,imageView12;
     ListView acList;
private String Role = null;
private int Roleid = 0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        client= findViewById(R.id.client);
        twProfilename = findViewById(R.id.twProfilename);
        Intent i = getIntent();
        Intent intent = getIntent();
        fstore = FirebaseFirestore.getInstance();
        init();
        twProfilename.setText(i.getStringExtra("UserData"));
        String position = i.getStringExtra("UserDataNum");
        imageView7 = findViewById(R.id.imageView7);
        imageView12 = findViewById(R.id.imageView12);
        imageView23 = findViewById(R.id.imageView23);




        switch(Integer.parseInt(position)) {
            case 1:
                twProfilename.setTextColor(Color.parseColor("#FF0000"));
                Role = "Админ";
                client.setVisibility(View.GONE);
                imageView23.setVisibility(View.GONE);
                Roleid =1;
                break;
            case 2:

                Roleid =2; //user;
                Role = "User";
                imageView12.setVisibility(View.GONE);
                imageView7.setVisibility(View.GONE);
                imageView23.setVisibility(View.VISIBLE);
                break;
            case 3:

                Roleid =3; //user;
                Role = "User";
                imageView7.setVisibility(View.GONE);
                imageView12.setVisibility(View.GONE);
                imageView23.setVisibility(View.VISIBLE);
                break;
            case 4:

                break;



        }


        acList = findViewById(R.id.acList);
        ArrayList<Actions> arrayList = new ArrayList<>();

        arrayList.add(new Actions(R.drawable.tinkoff,"Тинкофф","Пожалуй один из лучших банков для молодых и не очень граждан, кешбек, акции и многое другое"));
        arrayList.add(new Actions(R.drawable.alfa,"Альфабанк","Хорошие условия по кредиту, бонусы, топливные карты и куча других продуктов, они не оставят вас равнодушными"));
        arrayList.add(new Actions(R.drawable.binance2,"Binance","Самая крупная в мире криптобиржа, по совместительству платежная система и рынок криптовалют и акций"));
        arrayList.add(new Actions(R.drawable.sber,"Сбербанк","Банк не сильно щедрый в плане наград и бонусов, однако проверен временем, неплохие условия по кредитам"));
        arrayList.add(new Actions(R.drawable.rosselhoz,"Россельхоз банк","У банка слабая инфраструктура, но очень выгодные кредиты при больших обьемах"));
        arrayList.add(new Actions(R.drawable.raiff,"Райффайзен банк","Новый банк у нас, пока только собираем информацию"));



        AdapterList adapters = new AdapterList(this,R.layout.list_row,arrayList);
        acList.setAdapter(adapters);
        acList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(MainMenu.this,"Переходим к списку услуг банка:"+i,Toast.LENGTH_SHORT).show();

                Intent is = new Intent(MainMenu.this,Actionlister.class);
                is.putExtra("ItemId",i);
                startActivity(is);

            }
        });


    }





    public void Profile(View view) {
Intent intent = new Intent(MainMenu.this,ProfileActivity.class);
        intent.putExtra("ifAdmin","Роль:"+Role);
        intent.putExtra("Roleid","Роль:"+Roleid);
startActivity(intent);


    }
    public void init () {
        fstore.collection("Users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + "=>" + document.getData());


                                name =  (document.get("FullName").toString());


                            }
                        }
                    }

                });
    }


    public void messenger(View view) {
        Intent intent = new Intent(MainMenu.this,GroupChat.class);
        intent.putExtra("Pname",name+":");
        startActivity(intent);


    }

    public void Admin(View view) {
        Intent intent = new Intent(MainMenu.this,AdminActivity.class);
        startActivity(intent);
    }



    public void next(View view) {
        Intent intent = new Intent(MainMenu.this,Actionlister.class);
        int catid ;

        intent.putExtra("catid","i");
        startActivity(intent);
    }

    public void callback(View view) {

        Uri number = Uri.parse("tel:88005553535");
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        startActivity(callIntent);
    }

    public void korza(View view) {
        Intent intent = new Intent(MainMenu.this,Zayavki.class);

        intent.putExtra("Roleid","Роль:"+Roleid);
        startActivity(intent);
    }

    public void Admindeny(View view) {
        Toast.makeText(MainMenu.this,"Нет прав, авторизуйтесь как Администратор или сотрудник",Toast.LENGTH_SHORT).show();
    }

    public void klients(View view) {
        Intent intent = new Intent(MainMenu.this, ClientZayavkaToPay.class);
        startActivity(intent);
    }
}