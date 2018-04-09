/*
 * AddPhoto
 *
 * CMPUT301W18T21
 *
 * April 9, 2018
 *
 * Copyright (c) CMPUT301W18T21
 *
 */
package com.example.a1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

/**
 * Represents the Task photo activity.
 * Display the photo of task
 * User can Upload  photo from gallery
 */

public class AddPhoto extends AppCompatActivity {
    ImageView imageToUpload;
    Button AddButton;
    Button SaveButton;
    private static Task task;
    private static String username;

    private static Integer RESULT_LOAD_IMAGE=1;


    /**
     * A method that executes every time the activity is shown on screen.
     *
     * @param savedInstanceState The saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);
        Intent intent = new Intent();
        intent = getIntent();
        username = intent.getStringExtra("username");

        setupSelectImage();
        setSaveButton();

        imageToUpload = (ImageView)findViewById(R.id.imageView);
        try {
            byte[] decodedString = Base64.decode(task.getPhoto(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imageToUpload.setImageBitmap(decodedByte);
            Toast.makeText(AddPhoto.this, "already", Toast.LENGTH_SHORT).show();
        }catch (Exception   e){
            Toast.makeText(AddPhoto.this, "Please updload photo", Toast.LENGTH_SHORT).show();
            return;
        }



    }

    /**
     * when AddButton  is clicked User can selected a photo from gallery
     */
    private void setupSelectImage() {

        AddButton = (Button)findViewById(R.id.viewAdd);
        AddButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent galleryIntent = new Intent (Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE);


            }
        });

    }
    /**
     * after user selected a photo, display the photo at imageToUpload
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode==RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data!=null){
            Uri selectedImage = data.getData();
            imageToUpload.setImageURI(selectedImage);


        }
    }
    /**
     * when  Savebutton is clicked jump from Addphoto  Screen to RequesterTaskDetail screen, and convert image to
     * Bitmap and encoded Photo;
     */
    private void setSaveButton(){
        SaveButton =(Button) findViewById(R.id.save_button);
        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("onclick is good","dsdsdsdsds");
                Bitmap image = ((BitmapDrawable) imageToUpload.getDrawable()).getBitmap();
                Log.d("gitmap is good","sdsdsdsdsds");

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);



                Intent intent = new Intent(AddPhoto.this, RequesterTaskDetail.class);
                intent.putExtra("encodedImage",encodedImage);
                startActivityForResult(intent, 1);



            }
        });
    }



    /**
     * Sets the task who's info will be displayed
     * @param task Task to be displayed.
     */

    public static void setTask1(Task task){ AddPhoto.task = task; }




}
