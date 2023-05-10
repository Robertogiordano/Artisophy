package com.example.artisophy.services;

import com.example.artisophy.dao.respuestas.artist.RespuestaArtistImpl;
import com.example.artisophy.dao.respuestas.artwork.RespuestaArtworkImpl;
import com.example.artisophy.dao.respuestas.museum.RespuestaMuseumImpl;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MuseumMavenInterface {

    @GET("GetAllMuseumsServlet")
    Call<RespuestaMuseumImpl> getAllMuseums();

    @POST("FilterMuseumById")
    Call<RespuestaMuseumImpl> filterMuseumById(@Query("id") String id);
}
