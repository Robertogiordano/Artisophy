package com.example.artisophy.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.artisophy.R;
import com.example.artisophy.Cache;

public class HomeActivity extends AppCompatActivity {

    private TextView welcomeTextView;
    private Button galeriaDeArteButton;
    private Button pintoresButton;
    private Button guiasMuseoButton;
    private Button imagenesYVideosButton;

    //private Intent intentRecibido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        welcomeTextView=findViewById(R.id.bienvenido);
        welcomeTextView.setText("Welcome "+ Cache.getInstance().getUser().getName());

        //On click goes to galeria de arte activity
        galeriaDeArteButton=findViewById(R.id.galeriaArteButton);
        galeriaDeArteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context= HomeActivity.this;
                Class claseDestino=GaleriaDeArteActivity.class;
                String mensajeEnviar="Se ha enviado correctamente. Estoy en galeria de arte";

                Intent intent = new Intent(context,claseDestino);
                intent.putExtra("MENSAJE", mensajeEnviar);
                intent.putExtra("CODE",23);
                startActivity(intent);
            }
        });

        //On click goes to galeria de arte activity
        pintoresButton=findViewById(R.id.pintoresButton);
        pintoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context= HomeActivity.this;
                Class claseDestino=PintoresActivity.class;
                String mensajeEnviar="Se ha enviado correctamente. Estoy en pintores";

                Intent intent = new Intent(context,claseDestino);
                intent.putExtra("MENSAJE", mensajeEnviar);
                intent.putExtra("CODE",23);
                startActivity(intent);
            }
        });

        //On click goes to galeria de arte activity
        guiasMuseoButton=findViewById(R.id.guiasMuseoButton);
        guiasMuseoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context= HomeActivity.this;
                Class claseDestino=GuiasMuseoActivity.class;
                String mensajeEnviar="Se ha enviado correctamente. Estoy en guias Museo";

                Intent intent = new Intent(context,claseDestino);
                intent.putExtra("MENSAJE", mensajeEnviar);
                intent.putExtra("CODE",23);
                startActivity(intent);
            }
        });

        //On click goes to galeria de arte activity
        imagenesYVideosButton=findViewById(R.id.imagenesYVideosButton);
        imagenesYVideosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context= HomeActivity.this;
                Class claseDestino=ImagenesYVideosActivity.class;
                String mensajeEnviar="Se ha enviado correctamente. Estoy en imagenes y videos";

                Intent intent = new Intent(context,claseDestino);
                intent.putExtra("MENSAJE", mensajeEnviar);
                intent.putExtra("CODE",23);
                startActivity(intent);
            }
        });
    }

    /**Creation menu to display settings and logout*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        Log.i("Creacion de menu", "El menu se ha creado correctamente");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Logout
        if(item.getItemId()==R.id.logoutItem){
            Context context= HomeActivity.this;
            Class claseDestino=MainActivity.class;
            String mensajeEnviar="Se ha enviado correctamente. Estoy en main page";

            //Clear user cache
            Cache.getInstance().setUser(null);

            /**Uncomment to clear all mobile cache after logout*/
            //Cache.closeInstance();

            Intent intent = new Intent(context,claseDestino);
            intent.putExtra("MENSAJE", mensajeEnviar);
            intent.putExtra("CODE",23);
            startActivity(intent);
        }

        //Go to settings
        if(item.getItemId()==R.id.settingsItem){
            Context context= HomeActivity.this;
            Class claseDestino=SettingsActivity.class;
            String mensajeEnviar="Se ha enviado correctamente. Estoy en setting page";

            Intent intent = new Intent(context,claseDestino);
            intent.putExtra("MENSAJE", mensajeEnviar);
            intent.putExtra("CODE",23);

            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}