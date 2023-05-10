package com.example.artisophy.adapters.artworks;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.artisophy.activities.GaleriaDeArteActivity;
import com.example.artisophy.R;
import com.example.artisophy.activities.SingleArtworkActivity;
import com.example.artisophy.dao.elements.Artist;
import com.example.artisophy.dao.elements.ArtworkInterface;
import com.example.artisophy.dao.elements.Museum;
import com.example.artisophy.dao.respuestas.artist.RespuestaArtistImpl;
import com.example.artisophy.dao.respuestas.museum.RespuestaMuseumImpl;
import com.example.artisophy.dao.respuestas.user.RespuestaUserImpl;
import com.example.artisophy.services.APIServiceBuilder;
import com.example.artisophy.services.ArtistMavenInterface;
import com.example.artisophy.services.MuseumMavenInterface;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.Semaphore;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ArtworksRecyclerViewAdapter extends RecyclerView.Adapter<ArtworksRecyclerViewAdapter.ArtworkViewHolder> {

    private List<ArtworkInterface> artworksList;

    public ArtworksRecyclerViewAdapter(List<ArtworkInterface> artworksList) {
        this.artworksList = artworksList;
    }

    @NonNull
    @Override
    public ArtworksRecyclerViewAdapter.ArtworkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // This line inflates a layout file called "artwork_singleitem_list.xml"
        // and creates a view hierarchy that can be displayed on the screen. The resulting view is stored in the variable "itemView".
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.artwork_singleitem_list,parent,false);

        Log.i("onCreteViewHolder","Cargada la vista artworks");

        return new ArtworkViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtworksRecyclerViewAdapter.ArtworkViewHolder holder, int position) {
        ArtworkInterface currentArtwork=artworksList.get(position);
        holder.bind(currentArtwork);
        Log.i("onBindViewHolder","Cargado artwork "+position+" de nombre "+currentArtwork.getName());
    }

    @Override
    public int getItemCount() {
        return artworksList.size();
    }

    public class ArtworkViewHolder extends RecyclerView.ViewHolder {

        private ImageView iconImageView;
        private Button nameButton;

        public ArtworkViewHolder(@NonNull View itemView) {
            super(itemView);

            iconImageView=itemView.findViewById(R.id.iconImageView);
            nameButton=itemView.findViewById(R.id.nameArtworkRecicleViewElement);
        }

        public void bind(ArtworkInterface currentArtwork) {

            String imageUrl = currentArtwork.getImgPath();

            Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.placeholder_image) // immagine di caricamento mostrata prima che l'immagine venga scaricata
                    .error(R.drawable.error_image) // immagine di errore mostrata se si verifica un errore durante il caricamento
                    .into(iconImageView);

            nameButton.setText(currentArtwork.getName());

            nameButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context= view.getContext();
                    Class claseDestino= SingleArtworkActivity.class;
                    String mensajeEnviar="Se ha enviado correctamente. Estoy en la singula obra";

                    Intent intent = new Intent(context,claseDestino);
                    intent.putExtra("MENSAJE", mensajeEnviar);
                    intent.putExtra("CODE",23);

                    intent.putExtra("ID",currentArtwork.getId());
                    intent.putExtra("TITLE",currentArtwork.getName());
                    intent.putExtra("IMGPATH",currentArtwork.getImgPath());
                    intent.putExtra("YEAR",currentArtwork.getYear().toString());
                    intent.putExtra("MUSEUM",currentArtwork.getMuseum_id().toString());
                    intent.putExtra("AUTHOR",currentArtwork.getAuthor_id().toString());
                    intent.putExtra("DESCRIPTION",currentArtwork.getDescription());
                    intent.putExtra("WIKI",currentArtwork.getWiki());

                    intent.putExtra("CONTEXT","MUSEUM_LIST");

                    Log.e("Sono nel contesto",view.getContext().toString());

                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}
