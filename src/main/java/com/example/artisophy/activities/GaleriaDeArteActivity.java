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
import com.example.artisophy.adapters.artworks.ArtworksRecyclerViewAdapter;
import com.example.artisophy.dao.elements.ArtworkInterface;
import com.example.artisophy.dao.respuestas.artwork.RespuestaArtworkImpl;
import com.example.artisophy.services.APIServiceBuilder;
import com.example.artisophy.services.ArtsMavenInterface;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GaleriaDeArteActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArtsMavenInterface apiInterface;
    private List<ArtworkInterface> listaArtworks;
    ArtworksRecyclerViewAdapter artworkAdapter;
    private Button backButton;

    private Button sendButton;
    private EditText findEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //create list to save data
        listaArtworks=new LinkedList<ArtworkInterface>();
        setContentView(R.layout.activity_galeria_de_arte);

        //associate recycler view
        recyclerView=findViewById(R.id.recyclerViewGaleriaArte);

        /** Getting data from database and updating the view **/

        //get connection to servlet
        Retrofit retrofit= APIServiceBuilder.getClient();
        apiInterface=retrofit.create(ArtsMavenInterface.class);

        //catch data from db and save in listaArtworks
        if(Cache.getInstance().getArtworks()==null){
            generaListadoArtworks();
        }else{
            listaArtworks= Cache.getInstance().getArtworks();
        }

        //update recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        artworkAdapter=new ArtworksRecyclerViewAdapter(listaArtworks);
        recyclerView.setAdapter(artworkAdapter);

        /** **/

        //connect button and on click go to home
        backButton=findViewById(R.id.backButtonGaleriaArte);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context=GaleriaDeArteActivity.this;
                Class claseDestino=HomeActivity.class;
                String mensaje="Inviado. Estoy en home";

                Intent intent = new Intent(context,claseDestino);
                intent.putExtra("MENSAJE",mensaje);
                intent.putExtra("CODE",23);

                startActivity(intent);
            }
        });

        //Connect search bar
        findEditText=findViewById(R.id.findEditTextGaleriaDeArte);
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
        sendButton=findViewById(R.id.sendButtonArtworks);

        //On click filter the list of element. No call to database is done. Display new filtered elements.
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query=findEditText.getText().toString();

                //Create filtered by list. Parsing a list to stream and filtering by name
                Log.e("Lista artworks before filter", String.valueOf(listaArtworks.size()));
                List<ArtworkInterface> listaArtworksFiltered=listaArtworks.stream().filter(a -> a.getName().toLowerCase().contains(query.toLowerCase())).collect(Collectors.toList());
                Log.e("Lista artworks after filter", String.valueOf(listaArtworksFiltered.size()));

                //Passing the new list filtered to the adapter, updating data on the screen.
                recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                artworkAdapter=new ArtworksRecyclerViewAdapter(listaArtworksFiltered);
                recyclerView.setAdapter(artworkAdapter);
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
            Log.e("Cache artworks size before cleaning",String.valueOf(Cache.getInstance().getArtworks().size()));
            Cache.getInstance().setArtworks(null);
            Log.e("Cache artworks cleaned",String.valueOf(Cache.getInstance().getArtworks()==null));
            generaListadoArtworks();
        }

        return super.onOptionsItemSelected(item);
    }
    private void generaListadoArtworks() {
        //Call servlet to get all artworks saved on database.
        Call<RespuestaArtworkImpl> call=apiInterface.getAllArtworks();
        Log.e("generalListadoArtworks: ","call get Artwork done");

        call.enqueue(new Callback<RespuestaArtworkImpl>() {
            @Override
            public void onResponse(Call<RespuestaArtworkImpl> call, Response<RespuestaArtworkImpl> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    Log.e("On response: ","response is succeful y body not null");

                    RespuestaArtworkImpl respuestaArtworks=response.body();
                    String code=respuestaArtworks.getCode();

                    if(code.equalsIgnoreCase("Ok")){
                        listaArtworks.clear();

                        //Save all in the local variable
                        listaArtworks.addAll(respuestaArtworks.getResult());

                        //Update the cache
                        Cache.getInstance().setArtworks(listaArtworks);
                        Log.e("Cache artworks size",String.valueOf(Cache.getInstance().getArtworks().size()));

                        //Update UI
                        artworkAdapter.notifyDataSetChanged();

                        Log.e("On response if code equals: ","ok");
                    }else{
                        Toast.makeText(GaleriaDeArteActivity.this,"Error"+respuestaArtworks.getMessage(),Toast.LENGTH_LONG).show();
                        Log.e("On response else: ","!ok");
                    }
                }else{
                    Toast.makeText(GaleriaDeArteActivity.this,"Error en la respuesta",Toast.LENGTH_LONG).show();
                    Log.e("On response: ","error en la respuesta");
                }
            }

            @Override
            public void onFailure(Call<RespuestaArtworkImpl> call, Throwable t) {
                Toast.makeText(GaleriaDeArteActivity.this,"Error en la llamada",Toast.LENGTH_LONG).show();
                Log.e("On failure: ",t.getMessage());
            }
        });
    }
}