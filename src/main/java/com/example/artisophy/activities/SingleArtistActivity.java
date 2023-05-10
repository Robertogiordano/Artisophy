package com.example.artisophy.activities;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.artisophy.R;
import com.example.artisophy.Cache;
import com.example.artisophy.dao.elements.Artwork;

public class SingleArtistActivity extends AppCompatActivity {
    private ImageView imgPathImageView;
    private TextView nameTextView;
    private TextView descriptionTextView;
    private TextView birthYearTextView;
    private TextView countryTextView;
    private TextView deathYearTextView;
    private Button backButton;

    Intent intentRecibido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_artist);

        /*Get intent from "ArtistsRecyclerViewAdapter click on single name button"*/
        intentRecibido = getIntent();

        /**Connect all varibles to UI elements*/
        imgPathImageView=findViewById(R.id.imgPathImageViewSingleArtist);
        nameTextView=findViewById(R.id.nameTextViewSingleArtist);
        descriptionTextView=findViewById(R.id.descriptionTextViewSingleArtist);
        birthYearTextView=findViewById(R.id.birthyearTextViewSingleArtist);
        countryTextView=findViewById(R.id.countryTextViewSingleArtist);
        deathYearTextView=findViewById(R.id.deathYearTextViewSingleArtist);

        /**On back click, go to
         * ArtistsList, if this activity was called from the list, displaying all the artists element on DB.
         * SingleArtworkActivity, if this activity was called from a single artwork, displaying the caller artwork.
         * */
        backButton=findViewById(R.id.backButtonSingleArtist);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context=SingleArtistActivity.this;
                Class claseDestino;
                String mensaje="Sent from single artist.";

                if(intentRecibido.hasExtra("CODE")) {
                    if (intentRecibido.getIntExtra("CODE",0)==23) {
                        claseDestino = PintoresActivity.class;

                        Intent intent = new Intent(context, claseDestino);
                        intent.putExtra("MENSAJE", mensaje);
                        intent.putExtra("CODE", 23);

                        startActivity(intent);
                    } else {
                        if (intentRecibido.getIntExtra("CODE",0)==20) {
                        claseDestino = SingleArtworkActivity.class;

                        Intent intent = new Intent(context, claseDestino);
                        intent.putExtra("MENSAJE", mensaje);
                        intent.putExtra("CODE", 20);

                        startActivity(intent);
                        } else {
                            Toast.makeText(SingleArtistActivity.this,"Error: code not recognised",Toast.LENGTH_LONG).show();
                        }
                    }
                }else{
                    Log.e("Has intent context","not");
                }
            }
        });

        if(intentRecibido.hasExtra("CODE") && intentRecibido.hasExtra("MENSAJE")){
            Integer code =intentRecibido.getIntExtra("CODE",0);

            /**display info.
             * code 23: called by list. Get intent. Database called done yet.
             * code 20: called by single artwork. Get data from cache.
             */

            if(code.equals(23)){
                nameTextView.setText(intentRecibido.getStringExtra("TITLE"));
                descriptionTextView.setText(intentRecibido.getStringExtra("DESCRIPTION"));
                birthYearTextView.setText(intentRecibido.getStringExtra("BIRTHYEAR"));
                countryTextView.setText(intentRecibido.getStringExtra("COUNTRY"));
                deathYearTextView.setText(intentRecibido.getStringExtra("DEATHYEAR"));
            }

            if(code.equals(20)){
                nameTextView.setText(Cache.getInstance().getCurrentArtist().getNameArtist());
                descriptionTextView.setText(Cache.getInstance().getCurrentArtist().getDescription());
                birthYearTextView.setText(Cache.getInstance().getCurrentArtist().getBirthYear().toString());
                deathYearTextView.setText(Cache.getInstance().getCurrentArtist().getDeathYear().toString());
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.single_element_menu,menu);
        Log.i("Creacion de menu", "El menu se ha creado correctamente");
        return super.onCreateOptionsMenu(menu);
    }

    //Hide unavailable options
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItem=menu.findItem(R.id.callButtonMenu);
        menuItem.setVisible(false);

        menuItem=menu.findItem(R.id.webSiteButtonMenu);
        menuItem.setVisible(false);

        menuItem=menu.findItem(R.id.mapsButtonMenu);
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
}