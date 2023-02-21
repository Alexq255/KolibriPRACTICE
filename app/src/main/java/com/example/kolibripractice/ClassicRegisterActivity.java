package com.example.kolibripractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Source;

import java.util.HashMap;
import java.util.Map;

public class ClassicRegisterActivity extends AppCompatActivity {
private EditText edEmail,edPass,Surname,DateOfBirth,Town,Name;
private TextView regText,Licenge;
private ImageView imageViewn1,imageViewn2,imageViewn3,countinue2to3,imageView8,next,mailic,imgKey,imageView6,Nameicon,Dateicon,townIcon,bot;
private CheckBox Checker;
private Button Completed,completed2;
private VideoView vid;
private FirebaseAuth mAuth;
private FirebaseFirestore fstore;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classic_register);
        edEmail = findViewById(R.id.edEmail);
        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        edPass = findViewById(R.id.edPass);
        Licenge = findViewById(R.id.Licenge);
        regText = findViewById(R.id.regText);
        imageViewn1 = findViewById(R.id.imageViewn1);
        imgKey = findViewById(R.id.imgKey);
        mailic = findViewById(R.id.mailic);
        countinue2to3 = findViewById(R.id.countinue2to3);
        imageViewn2 = findViewById(R.id.imageViewn2);
        imageView6 = findViewById(R.id.imageView6);
        bot = findViewById(R.id.bot);
        imageViewn3 = findViewById(R.id.imageViewn3);
        imageView8 = findViewById(R.id.imageView8);
        townIcon = findViewById(R.id.townIcon);
        Dateicon = findViewById(R.id.Dateicon);
        Nameicon = findViewById(R.id.Nameicon);
        Checker = findViewById(R.id.Checker);
        Completed = findViewById(R.id.Completed);
        completed2 = findViewById(R.id.completed2);
        next = findViewById(R.id.next);
        tab1();
        //3tab
        Name = findViewById(R.id.Name);
        Surname = findViewById(R.id.Surname);
        DateOfBirth = findViewById(R.id.DateOfBirth);
        Town = findViewById(R.id.Town);
Completed = findViewById(R.id.Completed);
        Name.setVisibility(View.GONE);
        Surname.setVisibility(View.GONE);
        DateOfBirth.setVisibility(View.GONE);
        Town.setVisibility(View.GONE);
        Checker.setVisibility(View.GONE);
        Licenge.setVisibility(View.GONE);
        Completed.setVisibility(View.GONE);
        completed2.setVisibility(View.GONE);
        Glide.with(this)
                        .load(R.drawable.giphy)
                                .into(bot);
bot.setVisibility(View.GONE);
        fstore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();


init();
init2();
        Completed.setOnClickListener(new View.OnClickListener()
        {
            @Override

            public void onClick(View view) {

                if (edEmail.getText().toString().isEmpty()|| edPass.getText().toString().isEmpty()){
                    Toast.makeText(ClassicRegisterActivity.this,"Поля пустые",Toast.LENGTH_SHORT).show();
                }else{
                    mAuth.createUserWithEmailAndPassword(edEmail.getText().toString(), edPass.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    FirebaseUser users = mAuth.getCurrentUser();
                                    DocumentReference df = fstore.collection("Users").document(users.getUid());
                                    Map<String,Object> userinfo = new HashMap<>();
                                    userinfo.put("Mail",users.getEmail().toString());
                                    userinfo.put("FullName",Name.getText().toString());
                                    userinfo.put("Surname",Surname.getText().toString());
                                    userinfo.put("Date",DateOfBirth.getText().toString());
                                    userinfo.put("Town",Town.getText().toString());
                                    userinfo.put("Bio","Bio......").toString();

                                    userinfo.put("NotValidated","1");
                                    df.set(userinfo);
                                    if (task.isSuccessful()){

                                        Intent intent = new Intent(ClassicRegisterActivity.this, LoginActivity.class);
                                        FirebaseUser cUser = mAuth.getCurrentUser();
                                        intent.putExtra("UserData","Пользователь:"+cUser.getEmail().toString());
                                        startActivity(intent);

                                    }else{
                                        Toast.makeText(ClassicRegisterActivity.this,"Ошибка",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }
        });


    }
    public void init (){
        Checker.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (Checker.isChecked()){
                    Button bg = (Button)Completed;
                    bg.setEnabled(true);

                }else{
                    Button bg = (Button)Completed;
                    bg.setEnabled(false);

                }
            }
        });
    }
public void init2(){
    Button b = (Button)Completed;
    b.setEnabled(false);
    mailic.setVisibility(View.GONE);
    imgKey.setVisibility(View.GONE);
    Nameicon.setVisibility(View.GONE);
    townIcon.setVisibility(View.GONE);
    Dateicon.setVisibility(View.GONE);



}
    public void Bold(View view) {
        edEmail.setHintTextColor(Color.parseColor("#090909"));
    }
    public void tab1(){
        edPass.setVisibility(View.GONE);
        countinue2to3.setVisibility(View.GONE);
        next.setVisibility(View.GONE);
       mailic.setVisibility(View.VISIBLE);



    }
    public void tab2(){
        imageView8.setVisibility(View.GONE);
        countinue2to3.setVisibility(View.VISIBLE);
        edPass.setVisibility(View.VISIBLE);
        edEmail.setVisibility(View.GONE);
        regText.setText("Введите свой пароль");
        imageViewn2.setImageResource(R.drawable.blackline);
        imageViewn1.setImageResource(R.drawable.whiteline);
        imageView6.setVisibility(View.GONE);
        imgKey.setVisibility(View.VISIBLE);

    }
    public void tab3(){
        imageViewn3.setImageResource(R.drawable.blackline);
        imageViewn2.setImageResource(R.drawable.whiteline);
        imageViewn1.setImageResource(R.drawable.whiteline);
        regText.setText("Завершение регистрации");
        edPass.setVisibility(View.GONE);
        countinue2to3.setVisibility(View.GONE);
        next.setVisibility(View.GONE);
        Completed.setVisibility(View.VISIBLE);

        Name.setVisibility(View.VISIBLE);
        Surname.setVisibility(View.VISIBLE);
        DateOfBirth.setVisibility(View.VISIBLE);
        Town.setVisibility(View.VISIBLE);
        Checker.setVisibility(View.VISIBLE);
        Licenge.setVisibility(View.VISIBLE);
        imgKey.setVisibility(View.GONE);
        Nameicon.setVisibility(View.VISIBLE);
        townIcon.setVisibility(View.VISIBLE);
        Dateicon.setVisibility(View.VISIBLE);
        imageView6.setVisibility(View.VISIBLE);

    }


    public void Count(View view) {
        tab2();
    }

    public void GoBack(View view) {
        tab1();
    }

    public void twotothree(View view) {

    tab3();

    }



    public void LastStep(){
        regText.setText("Верификация");
        edPass.setVisibility(View.GONE);
        countinue2to3.setVisibility(View.GONE);
        next.setVisibility(View.GONE);
        Completed.setVisibility(View.GONE);
        edEmail.setVisibility(View.GONE);
        Name.setVisibility(View.GONE);
        Surname.setVisibility(View.GONE);
        DateOfBirth.setVisibility(View.GONE);
        Town.setVisibility(View.GONE);
        Checker.setVisibility(View.GONE);
        Licenge.setVisibility(View.VISIBLE);
        imgKey.setVisibility(View.GONE);
        Nameicon.setVisibility(View.GONE);
        townIcon.setVisibility(View.GONE);
        Dateicon.setVisibility(View.GONE);
        imageView6.setVisibility(View.GONE);
        bot.setVisibility(View.VISIBLE);
        completed2.setVisibility(View.VISIBLE);
        Licenge.setText("Для полной функциональности подтвердите что вы не робот,аккаунтом уже можно пользоваться в ограниченном режиме");
    }


}