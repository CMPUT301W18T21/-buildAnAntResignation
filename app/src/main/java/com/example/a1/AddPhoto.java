package com.example.a1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AddPhoto extends AppCompatActivity implements View.OnClickListener {


    ImageView imageToUpload;
    Button AddButton,BackButton;
    EditText imageName;

    private static Integer RESULT_LOAD_IMAGE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);

        imageToUpload = (ImageView)findViewById(R.id.imageView);
        AddButton = (Button)findViewById(R.id.viewAdd);
        BackButton=(Button)findViewById(R.id.viewBack);
        imageName = (EditText)findViewById(R.id.viewInputText);

        imageToUpload.setOnClickListener(this);
        AddButton.setOnClickListener(this);
        BackButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                break;
            case R.id.viewAdd:
                Bitmap image = ((BitmapDrawable) imageToUpload.getDrawable()).getBitmap();
                break;






        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode==RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data!=null){
            Uri selectedImage = data.getData();
            imageToUpload.setImageURI(selectedImage);
        }
    }



}
