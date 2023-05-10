package com.example.artisophy.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.artisophy.R;
import com.example.artisophy.Cache;
import com.example.artisophy.adapters.museums.MuseumsRecyclerViewAdapter;
import com.example.artisophy.dao.elements.MuseumInterface;
import com.example.artisophy.dao.respuestas.museum.RespuestaMuseumImpl;
import com.example.artisophy.services.APIServiceBuilder;
import com.example.artisophy.services.MuseumMavenInterface;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GuiasMuseoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MuseumMavenInterface apiInterface;
    private List<MuseumInterface> listaMuseums;
    MuseumsRecyclerViewAdapter museumAdapter;
    private Button backButton;

    private Button sendButton;
    private EditText findEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //create list to save data
        listaMuseums = new LinkedList<MuseumInterface>();
        setContentView(R.layout.activity_guias_museo);

        //associate recycler view
        recyclerView = findViewById(R.id.recyclerViewGuiasMuseo);

        /** Getting data from database and updating the view **/

        //get connection to servlet
        Retrofit retrofit = APIServiceBuilder.getClient();
        apiInterface = retrofit.create(MuseumMavenInterface.class);

        //catch data from db and save in listaArtworks
        if(Cache.getInstance().getMuseums()==null){
            generaListadoMuseums();
        }else{
            listaMuseums= Cache.getInstance().getMuseums();
        }

        //update recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        museumAdapter = new MuseumsRecyclerViewAdapter(listaMuseums);
        recyclerView.setAdapter(museumAdapter);

        /** **/

        //connect button and on click go to home
        backButton=findViewById(R.id.backButtonGuiasMuseo);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context=GuiasMuseoActivity.this;
                Class claseDestino=HomeActivity.class;
                String mensaje="Inviado. Estoy en home";

                Intent intent = new Intent(context,claseDestino);
                intent.putExtra("MENSAJE",mensaje);
                intent.putExtra("CODE",23);

                startActivity(intent);
            }
        });

        //Connect search bar
        findEditText=findViewById(R.id.findTextTextGuiasMuseo);
        //On focus clear the label
        findEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    findEditText.setText("");
                } else {
                    findEditText.setText("Find...");
                }
            }
        });

        //Connect the send button
        sendButton=findViewById(R.id.sendButtonMuseums);

        //On click filter the list of element. No call to database is done. Display new filtered elements.
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query=findEditText.getText().toString();

                //Create filtered by list. Parsing a list to stream and filtering by name
                Log.e("Lista museums before filter", String.valueOf(listaMuseums.size()));
                List<MuseumInterface> listaMuseumsFiltered=listaMuseums.stream().filter(a -> a.getName().toLowerCase().contains(query.toLowerCase())).collect(Collectors.toList());
                Log.e("Lista museums after filter", String.valueOf(listaMuseumsFiltered.size()));

                //Passing the new list filtered to the adapter, updating data on the screen.
                recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                museumAdapter =new MuseumsRecyclerViewAdapter(listaMuseumsFiltered);
                recyclerView.setAdapter(museumAdapter);
            }
        });
    }

    /**Create a menu to update new incoming data not saved in cache.*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.update_data_menu,menu);
        Log.i("Creacion de menu", "El menu se ha creado correctamente");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.updateItem){
            Log.e("Cache museums size before cleaning",String.valueOf(Cache.getInstance().getMuseums().size()));
            Cache.getInstance().setMuseums(null);
            Log.e("Cache museums cleaned",String.valueOf(Cache.getInstance().getMuseums()==null));
            generaListadoMuseums();
        }

        return super.onOptionsItemSelected(item);
    }
    private void generaListadoMuseums() {
        //Call servlet to get all museums saved on database.
        Call<RespuestaMuseumImpl> call = apiInterface.getAllMuseums();
        Log.e("generalListadoMuseums: ", "call get Museums done");

        call.enqueue(new Callback<RespuestaMuseumImpl>() {
            @Override
            public void onResponse(Call<RespuestaMuseumImpl> call, Response<RespuestaMuseumImpl> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.e("On response: ", "response is succeful y body not null");

                    RespuestaMuseumImpl respuestaMuseums = response.body();
                    String code = respuestaMuseums.getCode();

                    if (code.equalsIgnoreCase("Ok")) {
                        listaMuseums.clear();

                        //Save all in the local variable
                        listaMuseums.addAll(respuestaMuseums.getResult());

                        //Update the cache
                        Cache.getInstance().setMuseums(listaMuseums);
                        Log.e("Cache museums size",String.valueOf(Cache.getInstance().getMuseums().size()));

                        //Update UI
                        museumAdapter.notifyDataSetChanged();

                        Log.e("On response if code equals: ", "ok");
                    } else {
                        Toast.makeText(GuiasMuseoActivity.this, "Error" + respuestaMuseums.getMessage(), Toast.LENGTH_LONG).show();
                        Log.e("On response else: ", "!ok");
                    }
                } else {
                    Toast.makeText(GuiasMuseoActivity.this, "Error en la respuesta", Toast.LENGTH_LONG).show();
                    Log.e("On response: ", "error en la respuesta");
                }
            }

            @Override
            public void onFailure(Call<RespuestaMuseumImpl> call, Throwable t) {
                Toast.makeText(GuiasMuseoActivity.this, "Error en la llamada", Toast.LENGTH_LONG).show();
                Log.e("On failure: ", t.getMessage());
            }
        });
    }
}