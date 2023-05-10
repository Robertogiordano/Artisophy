package com.example.artisophy.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.artisophy.R;

/** This page will be available on the next release*/
public class ImagenesYVideosActivity extends AppCompatActivity {
    Button backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagenes_yvideos);

        backButton=findViewById(R.id.backButtonImagesVideo);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context=ImagenesYVideosActivity.this;
                Class claseDestino=HomeActivity.class;
                String message="Going to home";

                Intent intent=new Intent(context,claseDestino);
                intent.putExtra("code","ok");
                intent.putExtra("message",message);

                startActivity(intent);
            }
        });
    }
}