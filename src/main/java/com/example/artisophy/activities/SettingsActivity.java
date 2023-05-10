package com.example.artisophy.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.example.artisophy.R;
import com.example.artisophy.Cache;

public class SettingsActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText surnamesEditText;
    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Switch switchSeePassword;
    private Button backButton;
    private Button modifyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        /**Connect all the variables to the UI elements.*/
        nameEditText=findViewById(R.id.nameEditText);
        surnamesEditText=findViewById(R.id.surnameEditText);
        usernameEditText=findViewById(R.id.usernameEditText);
        emailEditText=findViewById(R.id.emailEditText);
        passwordEditText=findViewById(R.id.passwordEditText);
        switchSeePassword=findViewById(R.id.switchSeePassword);

        /**Get data from cache*/
        nameEditText.setText(Cache.getInstance().getUser().getName());
        surnamesEditText.setText(Cache.getInstance().getUser().getSurnames());
        usernameEditText.setText(Cache.getInstance().getUser().getUsername());
        emailEditText.setText(Cache.getInstance().getUser().getEmail());
        passwordEditText.setText(Cache.getInstance().getUser().getPassword());

        //Display password as circles
        passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        //On switch change, toogle display password as circles
        switchSeePassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e("Check","changed to "+isChecked);

                if(isChecked){
                    passwordEditText.setInputType(~(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD));
                }else{
                    passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        //On back click, go back to home activity.
        backButton=findViewById(R.id.backButtonSettings);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context=SettingsActivity.this;
                Class claseDestino=HomeActivity.class;
                String mensaje="Inviado. Estoy en register";

                Intent intent = new Intent(context,claseDestino);
                intent.putExtra("MENSAJE",mensaje);
                intent.putExtra("CODE",23);

                startActivity(intent);
            }
        });

        //On modify click, go to modify activity.
        modifyButton=findViewById(R.id.ModifyButtonSettings);
        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context=SettingsActivity.this;
                Class claseDestino=ModifyDataActivity.class;
                String mensaje="Inviado. Estoy en register";

                Intent intent = new Intent(context,claseDestino);
                intent.putExtra("MENSAJE",mensaje);
                intent.putExtra("CODE",23);

                startActivity(intent);
            }
        });
    }
}