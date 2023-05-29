package com.example.kolibripractice;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {
    FirebaseFirestore fstore;
    FirebaseAuth mAuth;
    private TextView twProfileName2, twProfileName3, twProfileName4,info;
    private EditText edName2, edTown2, edBirth2, edPochta, edSurname2,testedge,urlbox,edBio;
    private EditText edName2h, edSurnameh, edBirthh, edPochtah, edTownh;
    private ImageView imgViewAdd;
    private Uri uploadUri;
    private StorageReference mStorageRef;
    private Button updBtn;
    private String url;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        edName2h = findViewById(R.id.edName2h);
        edSurnameh = findViewById(R.id.edSurnameh);
        edBirthh = findViewById(R.id.edBirthh);
        edPochtah = findViewById(R.id.edPochtah);
        edTownh = findViewById(R.id.edTownh);
        twProfileName2 = findViewById(R.id.twProfilename2);
        twProfileName3 = findViewById(R.id.twProfilename3);
        twProfileName4 = findViewById(R.id.twProfilename4);
        edBio = findViewById(R.id.edBio);
        info = findViewById(R.id.info);

        testedge = findViewById(R.id.edName2h);
        imgViewAdd = findViewById(R.id.imgViewAdd);
        edName2 = findViewById(R.id.edName2);
        edTown2 = findViewById(R.id.edTown2);
        edBirth2 = findViewById(R.id.edBirth2);
        fstore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        edPochta = findViewById(R.id.edPochta);
        edSurname2 = findViewById(R.id.edSurname2);
        updBtn = findViewById(R.id.updBtn);
        mStorageRef = FirebaseStorage.getInstance().getReference("Image_db");

        Intent i = getIntent();
        twProfileName3.setText(i.getStringExtra("ifAdmin"));
        String position = i.getStringExtra("Roleid");
        init();
        updateData();
        hide();
        updBtn.setEnabled(false);


    }

    private void hide() {
        edName2h.setVisibility(View.GONE);
        edSurnameh.setVisibility(View.GONE);
        edBirthh.setVisibility(View.GONE);
        edPochtah.setVisibility(View.GONE);
        edTownh.setVisibility(View.GONE);
        edBio.setVisibility(View.GONE);
    }
    private void unhide() {
        edName2h.setVisibility(View.VISIBLE);
        edSurnameh.setVisibility(View.VISIBLE);
        edBirthh.setVisibility(View.VISIBLE);
        edPochtah.setVisibility(View.VISIBLE);
        edTownh.setVisibility(View.VISIBLE);
        edBio.setVisibility(View.VISIBLE);
        info.setVisibility(View.GONE);
    }

    private void updateData() {

        updBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Old = edName2.getText().toString();
                String New = edName2h.getText().toString();

                String Old2 = edSurname2.getText().toString();
                String New2 = edSurnameh.getText().toString();

                String Old3 = edBirth2.getText().toString();
                String New3 = edBirthh.getText().toString();

                String Old5 = edTown2.getText().toString();
                String New5 = edTownh.getText().toString();

                String Old4 = edPochta.getText().toString();
                String New4 = edPochtah.getText().toString();

                String Old6 = info.getText().toString();
                String New6 = edBio.getText().toString();

                updateData(Old,New);
                updateData2(Old2,New2);
                updateData3(Old3,New3);
                updateData4(Old4,New4);
                updateData5(Old5,New5);
                updateData6(Old6,New6);


            }

            private void updateData(String old, String New) {
                Map<String,Object>userDetail = new HashMap<>();
                userDetail.put("FullName",New);


                fstore.collection("Users")
                        .whereEqualTo("FullName",old)

                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()&& !task.getResult().isEmpty()){
                                    DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                                    String DocumentID = documentSnapshot.getId();
                                    fstore.collection("Users")
                                            .document(DocumentID)
                                            .update(userDetail)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Toast.makeText(ProfileActivity.this, "Пацан к успеху шел", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(ProfileActivity.this,ProfileActivity.class);
                                                    startActivity(intent);
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(ProfileActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }else{

                                }
                            }
                        });
            }
            private void updateData2(String Old2, String New2) {
                Map<String,Object>userDetail = new HashMap<>();
                userDetail.put("Surname",New2);


                fstore.collection("Users")
                        .whereEqualTo("Surname",Old2)

                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()&& !task.getResult().isEmpty()){
                                    DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                                    String DocumentID = documentSnapshot.getId();
                                    fstore.collection("Users")
                                            .document(DocumentID)
                                            .update(userDetail)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Toast.makeText(ProfileActivity.this, "Пацан к успеху шел", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(ProfileActivity.this,ProfileActivity.class);
                                                    startActivity(intent);
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(ProfileActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }else{

                                }
                            }
                        });
            }
            private void updateData3(String Old3, String New3) {
                Map<String,Object>userDetail = new HashMap<>();
                userDetail.put("Date",New3);


                fstore.collection("Users")
                        .whereEqualTo("Date",Old3)

                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()&& !task.getResult().isEmpty()){
                                    DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                                    String DocumentID = documentSnapshot.getId();
                                    fstore.collection("Users")
                                            .document(DocumentID)
                                            .update(userDetail)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Toast.makeText(ProfileActivity.this, "Пацан к успеху шел", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(ProfileActivity.this,ProfileActivity.class);
                                                    startActivity(intent);
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(ProfileActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }else{

                                }
                            }
                        });
            }
            private void updateData4(String Old4, String New4) {
                Map<String,Object>userDetail = new HashMap<>();
                userDetail.put("Mail",New4);


                fstore.collection("Users")
                        .whereEqualTo("Mail",Old4)

                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()&& !task.getResult().isEmpty()){
                                    DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                                    String DocumentID = documentSnapshot.getId();
                                    fstore.collection("Users")
                                            .document(DocumentID)
                                            .update(userDetail)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Toast.makeText(ProfileActivity.this, "Пацан к успеху шел", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(ProfileActivity.this,ProfileActivity.class);
                                                    startActivity(intent);
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(ProfileActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }else{

                                }
                            }
                        });
            }
            private void updateData5(String Old5, String New5) {
                Map<String,Object>userDetail = new HashMap<>();
                userDetail.put("Town",New5);


                fstore.collection("Users")
                        .whereEqualTo("Town",Old5)

                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()&& !task.getResult().isEmpty()){
                                    DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                                    String DocumentID = documentSnapshot.getId();
                                    fstore.collection("Users")
                                            .document(DocumentID)
                                            .update(userDetail)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Toast.makeText(ProfileActivity.this, "Пацан к успеху шел", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(ProfileActivity.this,ProfileActivity.class);
                                                    startActivity(intent);
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(ProfileActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }else{

                                }
                            }
                        });
            }
            private void updateData6(String Old6, String New6) {
                Map<String,Object>userDetail = new HashMap<>();
                userDetail.put("Bio",New6);


                fstore.collection("Users")
                        .whereEqualTo("Bio",Old6)

                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()&& !task.getResult().isEmpty()){
                                    DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                                    String DocumentID = documentSnapshot.getId();
                                    fstore.collection("Users")
                                            .document(DocumentID)
                                            .update(userDetail)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Toast.makeText(ProfileActivity.this, "Пацан к успеху шел", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(ProfileActivity.this,ProfileActivity.class);
                                                    startActivity(intent);
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(ProfileActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }else{

                                }
                            }
                        });
            }


        });
    }


    //load photos to db
    public void UploadPhoto(View view) {
        Intent intentChooser = new Intent();
        intentChooser.setType("image/*");
        intentChooser.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentChooser, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data != null && data.getData() != null) ;
        {
            if (resultCode == RESULT_OK) {
                Log.d("Mylog", "Image URI : " + data.getData());
                imgViewAdd.setImageURI(data.getData());


            }
        }
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

                                edName2.setText(document.get("FullName").toString());
                                edTown2.setText(document.get("Town").toString());
                                edBirth2.setText(document.get("Date").toString());
                                edPochta.setText(document.get("Mail").toString());
                                edSurname2.setText(document.get("Surname").toString());
                                twProfileName4.setText(document.get("FullName").toString());
                                info.setText(document.get("Bio").toString());


                            }
                        }
                    }

                });
    }


    private void uploadImage() {
        Bitmap bitmap = ((BitmapDrawable) imgViewAdd.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] byteArray = baos.toByteArray();
        StorageReference mRef = mStorageRef.child(System.currentTimeMillis() + "MyImg");
        UploadTask up = mRef.putBytes(byteArray);
        Task<Uri> task = up.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                return mRef.getDownloadUrl();

            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (uploadUri == null) {
                    uploadUri = task.getResult();
                    Toast.makeText(ProfileActivity.this, "Uspeh", Toast.LENGTH_SHORT).show();


                }


            }

            ;

        });

}

    public void EditStart(View view) {
        unhide();
        uploadImage();
        updBtn.setEnabled(true);

    }
    public void UpdIm(View view) {


    }

    public void createPass(View view) {
        Intent intent = new Intent(ProfileActivity.this,passwordActivity.class);
        startActivity(intent);
    }
}



