package com.example.artisophy.services;


import com.example.artisophy.adapters.artworks.ArtworksTypeAdapter;
import com.example.artisophy.dao.elements.ArtworkInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIServiceBuilder {
    private static Retrofit retrofit;

    private static final String BASE_URL = "http://10.0.2.2:9090/Artysophy_-1.0/";

    public static Retrofit getClient(){
        if(retrofit == null){
            //Para poder interceptar las conexiones
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            //Crear el interceptor de OkHttpClient poder registrar el uso de la red
            OkHttpClient client=new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();

            //In the case the deserialization is not done correctly, implement this
            //Gson gson= new GsonBuilder().registerTypeAdapter(ArtworkInterface.class,new ArtworksTypeAdapter()).create();
            //GsonConverterFactory gsonConverterFactory=GsonConverterFactory.create(gson);

            //construccion basica de retrofit
            retrofit= new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }

        return retrofit;
    }
}
