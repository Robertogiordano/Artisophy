package com.example.artisophy.adapters.museums;

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

import com.example.artisophy.activities.SingleMuseumActivity;
import com.example.artisophy.dao.elements.MuseumInterface;

import java.util.List;

public class MuseumsRecyclerViewAdapter extends RecyclerView.Adapter<MuseumsRecyclerViewAdapter.MuseumViewHolder> {

    private List<MuseumInterface> museumsList;

    public MuseumsRecyclerViewAdapter(List<MuseumInterface> museumsList) {
        this.museumsList = museumsList;
    }

    @NonNull
    @Override
    public MuseumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // This line inflates a layout file called "museum_singleitem_list.xml"
        // and creates a view hierarchy that can be displayed on the screen. The resulting view is stored in the variable "itemView".
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.museum_singleitem_list,parent,false);

        Log.i("onCreteViewHolder","Cargada la vista museums");

        return new MuseumViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MuseumViewHolder holder, int position) {
        MuseumInterface currentMuseum=museumsList.get(position);
        holder.bind(currentMuseum);
        Log.i("onBindViewHolder","Cargado Museum "+position+" de nombre "+currentMuseum.getName());
    }

    @Override
    public int getItemCount() {
        return museumsList.size();
    }

    public class MuseumViewHolder extends RecyclerView.ViewHolder {

        private Button nameButton;

        public MuseumViewHolder(@NonNull View itemView) {
            super(itemView);
            nameButton=itemView.findViewById(R.id.nameMuseumRecicleViewElement);
        }

        public void bind(MuseumInterface currentMuseum) {

            nameButton.setText(currentMuseum.getName());

            nameButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Context context= view.getContext();
                    Class claseDestino= SingleMuseumActivity.class;
                    String mensajeEnviar="Se ha enviado correctamente. Estoy en la singula obra";

                    Intent intent = new Intent(context,claseDestino);
                    intent.putExtra("MENSAJE", mensajeEnviar);
                    intent.putExtra("CODE",23);
                    intent.putExtra("TITLE",currentMuseum.getName());
                    intent.putExtra("STREET",currentMuseum.getStreet());
                    intent.putExtra("OPENINGHOUR",currentMuseum.getOpeningHour());
                    intent.putExtra("CLOSINGHOUR",currentMuseum.getClosingHour());
                    intent.putExtra("PHONE",currentMuseum.getPhone());
                    intent.putExtra("DESCRIPTION",currentMuseum.getDescription());
                    intent.putExtra("PRICE",currentMuseum.getPrice());
                    intent.putExtra("WEBPAGEURL",currentMuseum.getWebpageUrl());
                    intent.putExtra("WIKI",currentMuseum.getWiki());
                    intent.putExtra("MAPS",currentMuseum.getGoogleMaps());

                    intent.putExtra("CONTEXT","MUSEUMS_LIST");


                    Log.e("Sono nel contesto",view.getContext().toString());

                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}
