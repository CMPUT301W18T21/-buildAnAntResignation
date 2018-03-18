package com.example.a1;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.net.Uri;

public class AddPhoto extends AppCompatActivity {
    ImageView imageToUpload,downloadedImage;
    Button BrowseButton,AddButton,BackButton;
    EditText imageName;
    private static Integer RESULT_LOAD_IMAGE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);
        imageToUpload = (ImageView)findViewById(R.id.imageView);
        AddButton = (Button)findViewById(R.id.viewAdd);
        BrowseButton=(Button)findViewById(R.id.viewBrowse);
        BackButton=(Button)findViewById(R.id.viewBack);
        imageName = (EditText)findViewById(R.id.viewInputText);
        setupBrowseButton();

    }

    private void setupBrowseButton() {

        imageToUpload = (ImageView)findViewById(R.id.imageView);
        imageToUpload.setOnClickListener(new View.OnClickListener() {

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
       }
    }
}
