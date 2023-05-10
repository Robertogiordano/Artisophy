package com.example.artisophy.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.artisophy.R;
import com.example.artisophy.Cache;
import com.example.artisophy.dao.elements.Artist;
import com.example.artisophy.dao.elements.Artwork;
import com.example.artisophy.dao.elements.Museum;
import com.example.artisophy.dao.respuestas.artist.RespuestaArtistImpl;
import com.example.artisophy.dao.respuestas.museum.RespuestaMuseumImpl;
import com.example.artisophy.services.APIServiceBuilder;
import com.example.artisophy.services.ArtistMavenInterface;
import com.example.artisophy.services.MuseumMavenInterface;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SingleArtworkActivity extends AppCompatActivity {
    private ImageView imgPathImageView;
    private TextView nameTextView;
    private TextView descriptionTextView;
    private TextView museumTextView;
    private TextView authorTextView;
    private TextView yearTextView;
    private Button backButton;

    private ArtistMavenInterface apiInterfaceArtist;
    private MuseumMavenInterface apiInterfaceMuseum;

    Intent intentRecibido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_artwork);

        /**Connect variables to UI elements*/
        imgPathImageView=findViewById(R.id.imgPathImageViewSingleArtwork);
        nameTextView=findViewById(R.id.nameTextViewSingleArtwork);
        descriptionTextView=findViewById(R.id.descriptionTextViewSingleArtwork);
        museumTextView=findViewById(R.id.museumTextViewSingleArtwork);
        authorTextView=findViewById(R.id.authorTextViewSingleArtwork);
        yearTextView=findViewById(R.id.yearTextViewSingleArtwork);

        //on back click, go to galeria arte activity, showing all artworks
        backButton=findViewById(R.id.backButtonSingleArtwork);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context=SingleArtworkActivity.this;
                Class claseDestino=GaleriaDeArteActivity.class;
                String mensaje="Inviado. Estoy en home";

                Intent intent = new Intent(context,claseDestino);
                intent.putExtra("MENSAJE",mensaje);
                intent.putExtra("CODE",23);

                startActivity(intent);
            }
        });

        /**Get Intent. Could come from:
         * click on single name on GaleriaDeArte activity fired from ArtworkRecyclerAdapter nameTextView.OnClick()
         * click on back from singleArtist Activity. (In this case, updateArtist() was fired in advance)
         * click on back from singleMuseums Activity. (In this case, updateMuseum() was fired in advance)
         */

        intentRecibido = getIntent();
        if(intentRecibido.hasExtra("CODE") && intentRecibido.hasExtra("MENSAJE")){
            Integer code =intentRecibido.getIntExtra("CODE",0);

            if(code.equals(23)) {

                //Update the cache with the received intent.
                //If the intent was generated from singleArtist or singleMuseums, cache remains the same.

                Cache.getInstance().setCurrentArtwork(new Artwork(
                        intentRecibido.getIntExtra("ID", 0),
                        intentRecibido.getStringExtra("IMGPATH"),
                        intentRecibido.getStringExtra("TITLE"),
                        Integer.parseInt(intentRecibido.getStringExtra("YEAR")),
                        Integer.parseInt(intentRecibido.getStringExtra("AUTHOR")),
                        Integer.parseInt(intentRecibido.getStringExtra("MUSEUM")),
                        intentRecibido.getStringExtra("DESCRIPTION"),
                        intentRecibido.getStringExtra("WIKI")
                ));
            }

            if(code.equals(20)|| code.equals(23)){
                //Update the element texts
                nameTextView.setText(Cache.getInstance().getCurrentArtwork().getName());
                descriptionTextView.setText(Cache.getInstance().getCurrentArtwork().getDescription());
                museumTextView.setText(Cache.getInstance().getCurrentArtwork().getMuseum_id().toString());
                authorTextView.setText(Cache.getInstance().getCurrentArtwork().getAuthor_id().toString());
                yearTextView.setText(Cache.getInstance().getCurrentArtwork().getYear().toString());

                //Display the image
                String imageUrl = Cache.getInstance().getCurrentArtwork().getImgPath();

                Picasso.get()
                        .load(imageUrl)
                        .placeholder(R.drawable.placeholder_image) // immagine di caricamento mostrata prima che l'immagine venga scaricata
                        .error(R.drawable.error_image) // immagine di errore mostrata se si verifica un errore durante il caricamento
                        .into(imgPathImageView);

                /**Obtain artist info, display the name and save it on cache*/

                Retrofit retrofit= APIServiceBuilder.getClient();
                apiInterfaceArtist = retrofit.create(ArtistMavenInterface.class);

                //If the artwork's artist is not downloaded from DB, download is.
                //Otherwise, if the artist id is the same of cache's artist's id, use the artist's name saved in the cache.
                if(Cache.getInstance().getCurrentArtist()!=null){
                    if(Cache.getInstance().getCurrentArtist().getId()==Cache.getInstance().getCurrentArtwork().getAuthor_id()){
                        Log.e("Finding resources", "on cache");
                        authorTextView.setText(Cache.getInstance().getCurrentArtist().getNameArtist());
                    }else{
                        Log.e("Cache to change. Finding resources", "on database");
                        updateArtist(Cache.getInstance().getCurrentArtwork().getAuthor_id().toString());
                    }
                }else{
                    Log.e("Finding resources", "on database");
                    updateArtist(Cache.getInstance().getCurrentArtwork().getAuthor_id().toString());
                }

                authorTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Context context=SingleArtworkActivity.this;
                        Class claseDestino=SingleArtistActivity.class;
                        String message="Going to artist from artwork";

                        Intent intent=new Intent(context,claseDestino);

                        intent.putExtra("MENSAJE", message);

                        intent.putExtra("CODE",20);

                        startActivity(intent);
                    }
                });

                /**Obtain museum info, display the name and save it on cache*/
                Retrofit retrofit2= APIServiceBuilder.getClient();
                apiInterfaceMuseum = retrofit2.create(MuseumMavenInterface.class);
                //If the artwork's museum is not downloaded from DB, download is.
                //Otherwise, if the museum id is the same of cache's museum's id, use the museum's name saved in the cache.
                if(Cache.getInstance().getCurrentMuseum()!=null){
                    if(Cache.getInstance().getCurrentMuseum().getId()==Cache.getInstance().getCurrentArtwork().getMuseum_id()){
                        Log.e("Finding resources", "on cache");
                        museumTextView.setText(Cache.getInstance().getCurrentMuseum().getName());
                    }else{
                        Log.e("Cache to change. Finding resources", "on database");
                        updateMuseum(Cache.getInstance().getCurrentArtwork().getMuseum_id().toString());
                    }
                }else{
                    Log.e("Finding resources", "on database");
                    updateMuseum(Cache.getInstance().getCurrentArtwork().getMuseum_id().toString());
                }

                museumTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Context context=SingleArtworkActivity.this;
                        Class claseDestino=SingleMuseumActivity.class;
                        String message="Going to museum from artwork";

                        Intent intent=new Intent(context,claseDestino);

                        intent.putExtra("MENSAJE", message);

                        intent.putExtra("CODE",20);

                        startActivity(intent);
                    }
                });
            }
        };
    }

    //Display the menu to obtain wiki, maps and share options
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.single_element_menu,menu);
        Log.i("Creacion de menu", "El menu se ha creado correctamente");
        return super.onCreateOptionsMenu(menu);
    }

    //Hide not available options
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItem=menu.findItem(R.id.callButtonMenu);
        menuItem.setVisible(false);

        menuItem=menu.findItem(R.id.webSiteButtonMenu);
        menuItem.setVisible(false);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.wikiButtonMenu){
            String url = intentRecibido.getStringExtra("WIKI");
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        }

        if(item.getItemId()==R.id.mapsButtonMenu){

            Log.i("Pulsacion del boton maps", "El boton se ha pulsado y el toast ha salido");

            //Display the museum location on Google maps
            if(Cache.getInstance().getCurrentMuseum()!=null){
                Uri pos= Uri.parse("geo: "+Cache.getInstance().getCurrentMuseum().getGoogleMaps()+"?z=10");
                showMap(pos);
            }
        }

        if(item.getItemId()==R.id.shareButtonMenu){
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, intentRecibido.getStringExtra("TITLE"));
            shareIntent.putExtra(Intent.EXTRA_TEXT, intentRecibido.getStringExtra("TITLE")+": "+intentRecibido.getStringExtra("DESCRIPTION"));

            // Visualizzazione dell'elenco delle app con cui condividere i dati
            Intent chooserIntent = Intent.createChooser(shareIntent, "Share by");
            if (shareIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(chooserIntent);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public void showMap(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void updateMuseum(String id) {
        //Call filterMuseumById servlet
        Call<RespuestaMuseumImpl> call2=apiInterfaceMuseum.filterMuseumById(id);
        call2.enqueue(new Callback<RespuestaMuseumImpl>() {
            @Override
            public void onResponse(Call<RespuestaMuseumImpl> call, Response<RespuestaMuseumImpl> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    RespuestaMuseumImpl respuestaMuseum=response.body();
                    String code=respuestaMuseum.getCode();

                    if(code.equalsIgnoreCase("Ok")){
                        museumTextView.setText(respuestaMuseum.getResult().get(0).getName());

                        //Save on cache to avoid the same database query on click
                        Cache.getInstance().setCurrentMuseum(new Museum(
                                Integer.parseInt(id),
                                respuestaMuseum.getResult().get(0).getName(),
                                respuestaMuseum.getResult().get(0).getStreet(),
                                respuestaMuseum.getResult().get(0).getOpeningHour(),
                                respuestaMuseum.getResult().get(0).getClosingHour(),
                                respuestaMuseum.getResult().get(0).getPhone(),
                                respuestaMuseum.getResult().get(0).getDescription(),
                                respuestaMuseum.getResult().get(0).getPrice(),
                                respuestaMuseum.getResult().get(0).getWebpageUrl(),
                                respuestaMuseum.getResult().get(0).getWiki(),
                                respuestaMuseum.getResult().get(0).getGoogleMaps()
                        ));

                    }else{
                        Log.e("On response","an error occurred");
                    }
                }else{
                    Log.e("On response","error");
                }
            }

            @Override
            public void onFailure(Call<RespuestaMuseumImpl> call, Throwable t) {
                Log.e("On Failure:",t.getMessage());
            }
        });
    }

    private void updateArtist(String id) {
        //Call filterArtistById servlet
        Call<RespuestaArtistImpl> call=apiInterfaceArtist.filterArtistById(id);
        call.enqueue(new Callback<RespuestaArtistImpl>() {
            @Override
            public void onResponse(Call<RespuestaArtistImpl> call, Response<RespuestaArtistImpl> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    RespuestaArtistImpl respuestaArtist=response.body();
                    String code=respuestaArtist.getCode();

                    if(code.equalsIgnoreCase("Ok")){
                        authorTextView.setText(respuestaArtist.getResult().get(0).getNameArtist());

                        //Save on cache to avoid the same database query on click
                        Cache.getInstance().setCurrentArtist(new Artist(
                                Integer.parseInt(id),
                                respuestaArtist.getResult().get(0).getNameArtist(),
                                respuestaArtist.getResult().get(0).getName(),
                                respuestaArtist.getResult().get(0).getSurname(),
                                respuestaArtist.getResult().get(0).getBirthYear(),
                                respuestaArtist.getResult().get(0).getDeathYear(),
                                respuestaArtist.getResult().get(0).getDescription(),
                                respuestaArtist.getResult().get(0).getWiki()
                        ));

                        Log.e("On response","artist saved");
                    }else{
                        Log.e("On response","an error occurred");
                    }
                }else{
                    Log.e("On response","error");
                }
            }

            @Override
            public void onFailure(Call<RespuestaArtistImpl> call, Throwable t) {
                Log.e("On Failure:",t.getMessage());
            }
        });    }




}