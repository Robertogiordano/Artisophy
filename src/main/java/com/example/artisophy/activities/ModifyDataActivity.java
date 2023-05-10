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
import android.widget.Toast;

import com.example.artisophy.R;
import com.example.artisophy.Cache;
import com.example.artisophy.dao.respuestas.user.RespuestaUserImpl;
import com.example.artisophy.services.APIServiceBuilder;
import com.example.artisophy.services.UserMavenInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ModifyDataActivity extends AppCompatActivity {

    UserMavenInterface apiInterface;

    private EditText nameEditTextModifyProfile;
    private EditText surnamesEditTextModifyProfile;
    private EditText usernameEditTextModifyProfile;
    private EditText emailEditTextModifyProfile;
    private EditText passwordEditTextModifyProfile;
    private Switch switchSeePasswordModifyProfile;
    private Button backButtonModifyProfile;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_data);

        /**Connect all the variables to the UI elements.*/
        nameEditTextModifyProfile=findViewById(R.id.nameEditTextModifyProfile);
        surnamesEditTextModifyProfile=findViewById(R.id.surnameEditTextModifyProfile);
        usernameEditTextModifyProfile=findViewById(R.id.usernameEditTextModifyProfile);
        emailEditTextModifyProfile=findViewById(R.id.emailEditTextModifyProfile);
        passwordEditTextModifyProfile=findViewById(R.id.passwordEditTextModifyProfile);
        switchSeePasswordModifyProfile=findViewById(R.id.switchSeePasswordModifyProfile);

        /**Get data from cache*/
        nameEditTextModifyProfile.setText(Cache.getInstance().getUser().getName());
        surnamesEditTextModifyProfile.setText(Cache.getInstance().getUser().getSurnames());
        usernameEditTextModifyProfile.setText(Cache.getInstance().getUser().getUsername());
        emailEditTextModifyProfile.setText(Cache.getInstance().getUser().getEmail());
        passwordEditTextModifyProfile.setText(Cache.getInstance().getUser().getPassword());

        //Display password as circles
        passwordEditTextModifyProfile.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        //On switch change, toogle display password as circles
        switchSeePasswordModifyProfile.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e("Check","changed to "+isChecked);

                if(isChecked){
                    passwordEditTextModifyProfile.setInputType(~(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD));
                }else{
                    passwordEditTextModifyProfile.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        //On back click, go back to settings activity.
        backButtonModifyProfile=findViewById(R.id.backButtonModifyProfile);
        backButtonModifyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context=ModifyDataActivity.this;
                Class claseDestino=SettingsActivity.class;
                String mensaje="Inviado. Estoy en register";

                Intent intent = new Intent(context,claseDestino);
                intent.putExtra("MENSAJE",mensaje);
                intent.putExtra("CODE",23);

                startActivity(intent);
            }
        });

        //On save button click, save all the new data inserted on database and go back to setting activity.
        saveButton=findViewById(R.id.SaveButtonModifyProfile);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Catch the connection*/
                Retrofit retrofit= APIServiceBuilder.getClient();
                apiInterface = retrofit.create(UserMavenInterface.class);

                /*Get all the fields input*/
                String name=nameEditTextModifyProfile.getText().toString();
                String surnames=surnamesEditTextModifyProfile.getText().toString();
                String username=usernameEditTextModifyProfile.getText().toString();
                String email=emailEditTextModifyProfile.getText().toString();
                String password=passwordEditTextModifyProfile.getText().toString();

                Log.e("Fields",name + " " + surnames+" "+username+" "+email+" "+password);

                sendRequestModifyCredentials(name,surnames,username,email,password);
            }
        });
    }

    private void sendRequestModifyCredentials(String name, String surnames, String username, String email, String password) {
        //Call ModifyUser servlet. Pass the old user data saved on cache and the new collected data modified by user
        Call<RespuestaUserImpl> call=apiInterface.modifyUser(
                Cache.getInstance().getUser().getName(),
                Cache.getInstance().getUser().getSurnames(),
                Cache.getInstance().getUser().getUsername(),
                Cache.getInstance().getUser().getEmail(),
                Cache.getInstance().getUser().getPassword(),
                name,
                surnames,
                username,
                email,
                password
                );

        Log.e("Executing call","send");

        call.enqueue(new Callback<RespuestaUserImpl>() {
            @Override
            public void onResponse(Call<RespuestaUserImpl> call, Response<RespuestaUserImpl> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    RespuestaUserImpl respuestaUser=response.body();
                    String code=respuestaUser.getCode();

                    if(code.equalsIgnoreCase("Ok")){
                        //If mmodification is done, prepare the new destination class and intent.
                        Context context=ModifyDataActivity.this;
                        Class claseDestino=SettingsActivity.class;
                        String mensaje="Inviado. Estoy en register";

                        Intent intent = new Intent(context,claseDestino);
                        intent.putExtra("MENSAJE",mensaje);
                        intent.putExtra("CODE",23);

                        //Save the update user received on cache
                        Cache.getInstance().getUser().setName(respuestaUser.getResult().get(0).getName());
                        Cache.getInstance().getUser().setSurnames(respuestaUser.getResult().get(0).getSurnames());
                        Cache.getInstance().getUser().setUsername(respuestaUser.getResult().get(0).getUsername());
                        Cache.getInstance().getUser().setEmail(respuestaUser.getResult().get(0).getEmail());
                        Cache.getInstance().getUser().setPassword(respuestaUser.getResult().get(0).getPassword());

                        //Alert the user
                        Toast.makeText(ModifyDataActivity.this,"Successfully changed",Toast.LENGTH_LONG).show();

                        startActivity(intent);
                    }else{
                        Log.e("On response else: ","!ok");
                        Toast.makeText(ModifyDataActivity.this,"Something gone wrong!",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Log.e("On response: ","error en la respuesta");
                    Toast.makeText(ModifyDataActivity.this,"Something gone wrong!",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RespuestaUserImpl> call, Throwable t) {
                Log.e("On failure: ",t.getMessage());
                Toast.makeText(ModifyDataActivity.this,"Something gone wrong!",Toast.LENGTH_LONG).show();

            }
        });
    }

}