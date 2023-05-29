package com.example.kolibripractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {
private EditText edPassword2,edEmail2;
private FirebaseAuth mAuth;
private FirebaseFirestore fstore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edPassword2 = findViewById(R.id.edPassword2);
        edEmail2 = findViewById(R.id.edEmail2);
        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
    }

    public void Loginto(View view) {

            if (edEmail2.getText().toString().isEmpty()|| edPassword2.getText().toString().isEmpty()){
                Toast.makeText(LoginActivity.this, "Поля не могут быть пусты",Toast.LENGTH_SHORT).show();
            }else{
                mAuth.signInWithEmailAndPassword(edEmail2.getText().toString(),edPassword2.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){

                                    Toast.makeText(LoginActivity.this, "Успешный логин",Toast.LENGTH_SHORT).show();
                                    checkUserAcessLevel();
                                }else{
                                    Toast.makeText(LoginActivity.this, "Неверные данные",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }

        }
    private void checkUserAcessLevel(){
        DocumentReference df = fstore.collection("Users").document(mAuth.getUid());
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG","onSuccess"+ documentSnapshot.getData());
                if (documentSnapshot.getString("isAdmin") !=null){
                    //startActivity(new Intent(getApplicationContext(),Glavnaya.class));
                    //inish();
                    Intent intent = new Intent(LoginActivity.this, MainMenu.class);
                    FirebaseUser cUser = mAuth.getCurrentUser();
                    intent.putExtra("UserData","Админ:"+cUser.getEmail().toString());
                    intent.putExtra("UserDataNum","1");

                    startActivity(intent);

                }
                if (documentSnapshot.getString("isUser")!= null){
                    //startActivity(new Intent(getApplicationContext(),GlavnayaUser.class) );
                    Intent intent = new Intent(LoginActivity.this, MainMenu.class);
                    FirebaseUser cUser = mAuth.getCurrentUser();
                    intent.putExtra("UserData","Пользователь:"+cUser.getEmail().toString());
                    intent.putExtra("UserDataNum","2");
                    startActivity(intent);

                }
                if (documentSnapshot.getString("NotValidated")!= null){
                    //startActivity(new Intent(getApplicationContext(),GlavnayaUser.class) );
                    Intent intent = new Intent(LoginActivity.this, MainMenu.class);
                    FirebaseUser cUser = mAuth.getCurrentUser();
                    intent.putExtra("UserData","Пользователь:"+cUser.getEmail().toString());
                    intent.putExtra("UserDataNum","3");
                    startActivity(intent);

                }
                if (documentSnapshot.getString("isBanned")!= null){
                    //startActivity(new Intent(getApplicationContext(),GlavnayaUser.class) );
                    Intent intent = new Intent(LoginActivity.this, MainMenu.class);
                    FirebaseUser cUser = mAuth.getCurrentUser();
                    intent.putExtra("UserData","Пользователь:"+cUser.getEmail().toString());
                    intent.putExtra("UserDataNum","4");
                    startActivity(intent);

                }
            }
        });
    }


    public void Classic(View view) {
        Intent intent = new Intent(LoginActivity.this, ClassicRegisterActivity.class);
        startActivity(intent);
    }
}