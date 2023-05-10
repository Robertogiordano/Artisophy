package com.example.artisophy.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.artisophy.R;
import com.example.artisophy.Cache;
import com.example.artisophy.dao.elements.User;
import com.example.artisophy.dao.respuestas.user.RespuestaUserImpl;
import com.example.artisophy.services.APIServiceBuilder;
import com.example.artisophy.services.UserMavenInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    UserMavenInterface apiInterface;

    private TextView usernameField;
    private EditText usernameEditText;
    private TextView passwordField;
    private EditText passwordEditText;
    private Button loginButton;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameField=findViewById(R.id.usernameTextView);
        usernameEditText=findViewById(R.id.inputUsernameEditText);

        passwordField=findViewById(R.id.passordTextView);
        passwordEditText=findViewById(R.id.inputPasswordEditText);

        /** On click, verify the credentials on database. If true, go to home, otherwise display an error */
        loginButton=findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Retrofit retrofit= APIServiceBuilder.getClient();
                apiInterface=retrofit.create(UserMavenInterface.class);
                sendRequestCheckCredentials(usernameEditText.getText().toString(),passwordEditText.getText().toString());

                Log.e("Entro","home");
            }
        });

        /** On click, go to register a new user page */
        registerButton=findViewById(R.id.registerNowbutton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context=MainActivity.this;
                Class claseDestino=RegisterActivity.class;
                String mensaje="Inviado. Estoy en register";

                Intent intent = new Intent(context,claseDestino);
                intent.putExtra("MENSAJE",mensaje);
                intent.putExtra("CODE",23);

                startActivity(intent);
            }
        });
    }

    private void sendRequestCheckCredentials(String username, String password) {
        //Call checkCredentials servlet to verify credentials.
        Call<RespuestaUserImpl> call=apiInterface.checkCredentials(username,password);

        call.enqueue(new Callback<RespuestaUserImpl>() {
            @Override
            public void onResponse(Call<RespuestaUserImpl> call, Response<RespuestaUserImpl> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    RespuestaUserImpl respuestaUser=response.body();
                    String code=respuestaUser.getCode();

                    Log.e("code response",code);
                    if(code.equalsIgnoreCase("ok")){
                        /*If credentials are verified, create the destination class to display*/
                        Context context=MainActivity.this;
                        Class claseDestino=HomeActivity.class;
                        String mensaje="Inviado. Estoy en home";

                        Intent intent = new Intent(context,claseDestino);
                        intent.putExtra("MENSAJE",mensaje);
                        intent.putExtra("CODE",23);

                        /*Save user on current cache. User cache is deleted after logout*/
                        Cache.getInstance().setUser(
                                new User(respuestaUser.getResult().get(0).getName(),
                                respuestaUser.getResult().get(0).getSurnames(),
                                respuestaUser.getResult().get(0).getUsername(),
                                respuestaUser.getResult().get(0).getEmail(),
                                respuestaUser.getResult().get(0).getPassword()
                                ));

                        startActivity(intent);

                        Log.e("Access on response","true");
                    }else{
                        Log.e("Access on response","false");
                        Toast.makeText(MainActivity.this,"Username or password incorrected", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Log.e("Error on response","error");
                    Log.e("Response is succeful",String.valueOf(response.isSuccessful()));
                    Log.e("Body is not null",String.valueOf(response.body()!=null));
                    Toast.makeText(MainActivity.this,"Username or password incorrected", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<RespuestaUserImpl> call, Throwable t) {
                Log.e("Error on failure",t.getMessage());
            }
        });
    }
}
