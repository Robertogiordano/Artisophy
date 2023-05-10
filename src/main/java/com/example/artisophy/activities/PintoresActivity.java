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
import com.example.artisophy.adapters.artists.ArtistsRecyclerViewAdapter;
import com.example.artisophy.dao.elements.ArtistInterface;
import com.example.artisophy.dao.respuestas.artist.RespuestaArtistImpl;
import com.example.artisophy.services.APIServiceBuilder;
import com.example.artisophy.services.ArtistMavenInterface;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PintoresActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArtistMavenInterface apiInterface;
    private List<ArtistInterface> listaArtists;
    ArtistsRecyclerViewAdapter artistAdapter;
    private Button backButton;

    private Button sendButton;
    private EditText findEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //create list to save data
        listaArtists=new LinkedList<ArtistInterface>();
        setContentView(R.layout.activity_pintores);

        //associate recycler view
        recyclerView=findViewById(R.id.recyclerViewPintores);

        /** Getting data from database and updating the view **/

        //get connection to servlet
        Retrofit retrofit= APIServiceBuilder.getClient();
        apiInterface=retrofit.create(ArtistMavenInterface.class);

        //catch data from db and save in listaArtists. Use cache if exists.
        if(Cache.getInstance().getArtists()==null){
            generaListadoArtists();
        }else{
            listaArtists= Cache.getInstance().getArtists();
        }

        //update recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        artistAdapter=new ArtistsRecyclerViewAdapter(listaArtists);
        recyclerView.setAdapter(artistAdapter);

        /** **/

        //connect button and on click go to home
        backButton=findViewById(R.id.backButtonPintores);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context=PintoresActivity.this;
                Class claseDestino=HomeActivity.class;
                String mensaje="Inviado. Estoy en home";

                Intent intent = new Intent(context,claseDestino);
                intent.putExtra("MENSAJE",mensaje);
                intent.putExtra("CODE",23);

                startActivity(intent);
            }
        });

        //Connect search bar
        findEditText=findViewById(R.id.findTextTextArtists);
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
        sendButton=findViewById(R.id.sendButtonPintores);

        //On click filter the list of element. No call to database is done. Display new filtered elements.
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query=findEditText.getText().toString();

                //Create filtered by list. Parsing a list to stream and filtering by name
                Log.e("Lista pintores before filter", String.valueOf(listaArtists.size()));
                List<ArtistInterface> listaArtistsFiltered=listaArtists.stream().filter(a -> a.getNameArtist().toLowerCase().contains(query.toLowerCase())).collect(Collectors.toList());
                Log.e("Lista pintores after filter", String.valueOf(listaArtistsFiltered.size()));

                //Passing the new list filtered to the adapter, updating data on the screen.
                recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                artistAdapter =new ArtistsRecyclerViewAdapter(listaArtistsFiltered);
                recyclerView.setAdapter(artistAdapter);
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
            Log.e("Cache artists size before cleaning",String.valueOf(Cache.getInstance().getArtists().size()));
            Cache.getInstance().setArtists(null);
            Log.e("Cache artists cleaned",String.valueOf(Cache.getInstance().getArtists()==null));
            generaListadoArtists();
        }

        return super.onOptionsItemSelected(item);
    }
    private void generaListadoArtists() {
        //Call servlet to get all artists saved on database.
        Call<RespuestaArtistImpl> call=apiInterface.getAllArtists();
        Log.e("generalListadoArtists: ","call get Artists done");

        call.enqueue(new Callback<RespuestaArtistImpl>() {
            @Override
            public void onResponse(Call<RespuestaArtistImpl> call, Response<RespuestaArtistImpl> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    Log.e("On response: ","response is succeful y body not null");

                    RespuestaArtistImpl respuestaArtists=response.body();
                    String code=respuestaArtists.getCode();

                    if(code.equalsIgnoreCase("Ok")){
                        listaArtists.clear();

                        //Save all in the local variable
                        listaArtists.addAll(respuestaArtists.getResult());

                        //Update the cache
                        Cache.getInstance().setArtists(listaArtists);
                        Log.e("Cache artists size",String.valueOf(Cache.getInstance().getArtists().size()));

                        //Update UI
                        artistAdapter.notifyDataSetChanged();

                        Log.e("On response if code equals: ","ok");
                    }else{
                        Toast.makeText(PintoresActivity.this,"Error"+respuestaArtists.getMessage(),Toast.LENGTH_LONG).show();
                        Log.e("On response else: ","!ok");
                    }
                }else{
                    Toast.makeText(PintoresActivity.this,"Error en la respuesta",Toast.LENGTH_LONG).show();
                    Log.e("On response: ","error en la respuesta");
                }
            }

            @Override
            public void onFailure(Call<RespuestaArtistImpl> call, Throwable t) {
                Toast.makeText(PintoresActivity.this,"Error en la llamada",Toast.LENGTH_LONG).show();
                Log.e("On failure: ",t.getMessage());
            }
        });
    }
}