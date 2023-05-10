package com.example.artisophy.adapters.artists;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artisophy.R;

import com.example.artisophy.activities.SingleArtistActivity;
import com.example.artisophy.dao.elements.ArtistInterface;

import java.util.List;

public class ArtistsRecyclerViewAdapter extends RecyclerView.Adapter<ArtistsRecyclerViewAdapter.ArtistViewHolder> {

    private List<ArtistInterface> ArtistsList;

    public ArtistsRecyclerViewAdapter(List<ArtistInterface> ArtistsList) {
        this.ArtistsList = ArtistsList;
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // This line inflates a layout file called "artist_singleitem_list.xml"
        // and creates a view hierarchy that can be displayed on the screen. The resulting view is stored in the variable "itemView".
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_singleitem_list,parent,false);

        Log.i("onCreteViewHolder","Cargada la vista Artists");

        return new ArtistViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder holder, int position) {
        ArtistInterface currentArtist=ArtistsList.get(position);
        holder.bind(currentArtist);
        Log.i("onBindViewHolder","Cargado Artist "+position+" de nombre "+currentArtist.getName());
    }

    @Override
    public int getItemCount() {
        return ArtistsList.size();
    }

    public class ArtistViewHolder extends RecyclerView.ViewHolder {
        private Button nameButton;
        public ArtistViewHolder(@NonNull View itemView) {
            super(itemView);
            nameButton=itemView.findViewById(R.id.nameArtistRecicleViewElement);
        }

        public void bind(ArtistInterface currentArtist) {

            nameButton.setText(currentArtist.getNameArtist());

            nameButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Context context= view.getContext();
                    Class claseDestino= SingleArtistActivity.class;
                    String mensajeEnviar="Se ha enviado correctamente. Estoy en la singula obra";

                    Intent intent = new Intent(context,claseDestino);
                    intent.putExtra("MENSAJE", mensajeEnviar);
                    intent.putExtra("CODE",23);
                    intent.putExtra("TITLE",currentArtist.getNameArtist());
                    intent.putExtra("BIRTHYEAR",currentArtist.getBirthYear());
                    intent.putExtra("DEATHYEAR",currentArtist.getDeathYear());
                    intent.putExtra("DESCRIPTION",currentArtist.getDescription());
                    intent.putExtra("WIKI",currentArtist.getWiki());

                    intent.putExtra("CONTEXT","ARTISTS_LIST");

                    Log.e("Sono nel contesto",view.getContext().toString());

                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}
