package com.example.a1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddPhoto extends AppCompatActivity {
    ImageView imageToUpload;
    Button AddButton;
    Button SaveButton;
    private static Task task;



    private static Integer RESULT_LOAD_IMAGE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);

        imageToUpload = (ImageView)findViewById(R.id.imageView);
//        imageToUpload.setImageURI(task.getPhoto());

        setupSelectImage();
        setSaveButton();

        }

    private void setupSelectImage() {

        AddButton = (Button)findViewById(R.id.viewAdd);
        AddButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent galleryIntent = new Intent (Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE);


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode==RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data!=null){
            Uri selectedImage = data.getData();
            imageToUpload.setImageURI(selectedImage);
//            task.setPhoto(selectedImage);
        }
    }

    private void setSaveButton(){
        SaveButton =(Button) findViewById(R.id.save_button);
        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddPhoto.this,RequesterTaskDetail.class);
                startActivity(intent);
            }
        });
    }


    public static void setTask(Task task){ AddPhoto.task = task; }




}
