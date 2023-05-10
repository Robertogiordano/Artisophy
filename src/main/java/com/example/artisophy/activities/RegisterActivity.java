package com.example.artisophy.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.artisophy.R;
import com.example.artisophy.dao.elements.User;
import com.example.artisophy.dao.respuestas.user.RespuestaUserImpl;
import com.example.artisophy.services.APIServiceBuilder;
import com.example.artisophy.services.UserMavenInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {
    UserMavenInterface apiInterface;

    private EditText nameEditText;
    private EditText surnameEditText;
    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText repeatPasswordEditText;

    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Connect  all variables to UI elements
        nameEditText=findViewById(R.id.nameEditText);
        surnameEditText=findViewById(R.id.surnameEditText);
        usernameEditText=findViewById(R.id.usernameEditText);
        emailEditText=findViewById(R.id.emailEditText);
        passwordEditText=findViewById(R.id.passwordEditText);
        repeatPasswordEditText=findViewById(R.id.repeatPasswordEditText);

        //On send, register the new user on the database. If true, come back to main activity
        sendButton=findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**If all fiels are not empty and the two passwords fields are the same, create a connection to servlet and create user*/
                if(nameEditText.getText().toString()!=""&&surnameEditText.getText().toString()!=""&&usernameEditText.getText().toString()!=""&&emailEditText.getText().toString()!=""&&passwordEditText.getText().toString()!=""&&repeatPasswordEditText.getText().toString()!=""){
                    if(passwordEditText.getText().toString().equals(repeatPasswordEditText.getText().toString())){
                        /** NO PASSWORD CONSTRAINTS ARE REQUIRED IN THIS VERSION*/

                        Retrofit retrofit= APIServiceBuilder.getClient();
                        apiInterface=retrofit.create(UserMavenInterface.class);
                        sendRequestRegisterUser(nameEditText.getText().toString(),surnameEditText.getText().toString(),usernameEditText.getText().toString(),emailEditText.getText().toString(),passwordEditText.getText().toString());

                        Log.e("Entro","home");
                    }else{
                        //Alert the user about error. Not matched passwords.
                        Toast.makeText(RegisterActivity.this, "Password fileds are not equals", Toast.LENGTH_LONG).show();
                    }
                }else{
                    //Alert the user about error. Empty fields.
                    Toast.makeText(RegisterActivity.this, "Fields can't be empty!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void sendRequestRegisterUser(String name, String surname, String username, String email, String password) {
        //Call register user servlet with POST
        Call<RespuestaUserImpl> call=apiInterface.registerUser(name,surname,username,email,password);

        call.enqueue(new Callback<RespuestaUserImpl>() {
            @Override
            public void onResponse(Call<RespuestaUserImpl> call, Response<RespuestaUserImpl> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    RespuestaUserImpl respuestaUser=response.body();
                    String code=respuestaUser.getCode();
                    Log.e("code response",code);

                    if(code.equalsIgnoreCase("ok")){
                        //If code response is ok, the user is registered.
                        Context context=RegisterActivity.this;
                        Class claseDestino=MainActivity.class;
                        String mensaje="Inviado. Estoy en main";

                        Intent intent = new Intent(context,claseDestino);
                        intent.putExtra("MENSAJE",mensaje);
                        intent.putExtra("CODE",23);

                        //Go to main activity.
                        startActivity(intent);

                        //Alert the user about success
                        Toast.makeText(RegisterActivity.this, "Succefully registered", Toast.LENGTH_LONG).show();

                        Log.e("Access on response","true");
                    }else{
                        Log.e("Access on response","false");
                    }
                }else{
                    Log.e("Error on response","error");
                    Log.e("Response is succeful",String.valueOf(response.isSuccessful()));
                    Log.e("Body is not null",String.valueOf(response.body()!=null));
                }
            }

            @Override
            public void onFailure(Call<RespuestaUserImpl> call, Throwable t) {
                Log.e("Error on failure","error");
            }
        });
    }
}