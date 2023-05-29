package com.example.kolibripractice;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class AdminAddAction extends AppCompatActivity {
private EditText edPrice,edOpisanie,edHeader;
private ImageView imgchooser;
private String GROUPKEY ="Acticons";
private StorageReference mStorageRef;
    private DatabaseReference mBase;

    private Uri uploadUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_action);
         mStorageRef = FirebaseStorage.getInstance().getReference("Image_db");
        mBase = FirebaseDatabase.getInstance().getReference(GROUPKEY);
        init();
        initedtext();
    }

    private void initedtext() {
        edPrice =findViewById(R.id.edPrice);
        edOpisanie =findViewById(R.id.edOpisanie);
        edHeader =findViewById(R.id.edHeader);
    }

    public void init(){
        edPrice = findViewById(R.id.edPrice);
        edOpisanie = findViewById(R.id.edOpisanie);
        edHeader = findViewById(R.id.edHeader);
        imgchooser = findViewById(R.id.imgchooser);

    }

    public void load(View view) {
        Intent intentChooser = new Intent();
        intentChooser.setType("image/*");
        intentChooser.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentChooser,1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data != null && data.getData() != null) ;
        {
            if (resultCode == RESULT_OK) {
                Log.d("Mylog", "Image URI : " + data.getData());
                imgchooser.setImageURI(data.getData());


            }
        }
    }
    private void uploadImage() {
        Bitmap bitmap = ((BitmapDrawable) imgchooser.getDrawable()).getBitmap();
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
                    saveData();
                    Toast.makeText(AdminAddAction.this, "Uspeh", Toast.LENGTH_SHORT).show();


                }


            }

            ;

        });

    }
    private void saveData() {
        String id = mBase.push().getKey();
        String nazvanie = edOpisanie.getText().toString();
        String opisanie=edPrice.getText().toString();
        String price=edHeader.getText().toString();


        ClassAction msgadd = new ClassAction(id,nazvanie,opisanie,price,uploadUri.toString());
        if (!TextUtils.isEmpty(nazvanie)) {
            if (nazvanie != null) mBase.child(id).setValue(msgadd);



        } else {
            Toast.makeText(AdminAddAction.this, "Возможно некоторые поля пустые!", Toast.LENGTH_SHORT).show();
        }
    }

    public void saveall(View view) {
      uploadImage();
    }

    public void glav(View view) {
        Intent intent = new Intent(AdminAddAction.this,MainMenu.class);
        startActivity(intent);
    }
}