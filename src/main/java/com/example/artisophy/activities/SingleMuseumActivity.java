package com.example.artisophy.activities;

import static android.Manifest.permission.CALL_PHONE;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.artisophy.R;
import com.example.artisophy.Cache;
import com.example.artisophy.dao.elements.Artwork;

public class SingleMuseumActivity extends AppCompatActivity {
    private ImageView imgPathImageView;
    private TextView nameTextView;
    private TextView descriptionTextView;
    private TextView openingHourTextView;
    private TextView locationTextView;
    private TextView closingHourTextView;

    private Button backButton;

    Intent intentRecibido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_museum);

        /*Get intent from "MuseumRecyclerViewAdapter click on single name button"*/
        intentRecibido = getIntent();

        /**Connect all varibles to UI elements*/
        imgPathImageView=findViewById(R.id.imgPathImageViewSingleMuseum);
        nameTextView=findViewById(R.id.nameTextViewSingleMuseum);
        descriptionTextView=findViewById(R.id.descriptionTextViewSingleMuseum);
        openingHourTextView=findViewById(R.id.openingHourTextViewSingleMuseum);
        locationTextView=findViewById(R.id.locationTextViewSingleMuseum);
        closingHourTextView=findViewById(R.id.closingHourTextViewSingleMuseum);

        /**On back click, go to
         * MuseumsList, if this activity was called from the list, displaying all the artists element on DB.
         * SingleArtworkActivity, if this activity was called from a single artwork, displaying the caller artwork.
         * */
        backButton=findViewById(R.id.backButtonSingleMuseum);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context=SingleMuseumActivity.this;
                String mensaje="Sent from single museum.";

                Class claseDestino;
                if(intentRecibido.hasExtra("CODE")) {
                    if (intentRecibido.getIntExtra("CODE",0)==23) {
                        claseDestino = GuiasMuseoActivity.class;

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
                            Toast.makeText(SingleMuseumActivity.this,"Error: code not recognised",Toast.LENGTH_LONG).show();
                        }
                    }
                }else{
                    Log.e("Has intent context","not");
                }

            }
        });


        if(intentRecibido.hasExtra("CODE") && intentRecibido.hasExtra("MENSAJE")){
            Integer code =intentRecibido.getIntExtra("CODE",0);

            int PERMISSION_CODE=100;

            if(ContextCompat.checkSelfPermission(SingleMuseumActivity.this,CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(SingleMuseumActivity.this,new String[]{CALL_PHONE},PERMISSION_CODE);
            }

            /**display info.
             * code 23: called by list. Get intent. Database called done yet.
             * code 20: called by single artwork. Get data from cache.
             */

            if(code.equals(23)){
                nameTextView.setText(intentRecibido.getStringExtra("TITLE"));
                descriptionTextView.setText(intentRecibido.getStringExtra("DESCRIPTION"));
                openingHourTextView.setText("Opening hour: \n"+intentRecibido.getStringExtra("OPENINGHOUR"));
                closingHourTextView.setText("Closing hour: \n"+intentRecibido.getStringExtra("CLOSINGHOUR"));
                locationTextView.setText(intentRecibido.getStringExtra("STREET"));
            }

            if(code.equals(20)){
                nameTextView.setText(Cache.getInstance().getCurrentMuseum().getName());
                descriptionTextView.setText(Cache.getInstance().getCurrentMuseum().getDescription());
                openingHourTextView.setText("Opening hour: \n"+Cache.getInstance().getCurrentMuseum().getOpeningHour());
                closingHourTextView.setText("Closing hour: \n"+Cache.getInstance().getCurrentMuseum().getClosingHour());
                locationTextView.setText(Cache.getInstance().getCurrentMuseum().getStreet());
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.single_element_menu,menu);
        Log.i("Creacion de menu", "El menu se ha creado correctamente");
        return super.onCreateOptionsMenu(menu);
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

            Uri pos= Uri.parse("geo: "+ intentRecibido.getStringExtra("MAPS")+"?z=10");
            showMap(pos);
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

        if(item.getItemId()==R.id.callButtonMenu){
            dialPhoneNumber(intentRecibido.getStringExtra("PHONE"));
        }

        if(item.getItemId()==R.id.webSiteButtonMenu){
            String url = intentRecibido.getStringExtra("WEBPAGEURL");
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
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

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}