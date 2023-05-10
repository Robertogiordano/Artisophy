package com.example.artisophy.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import com.example.artisophy.dao.respuestas.artist.RespuestaArtistImpl;
import com.example.artisophy.dao.respuestas.artwork.RespuestaArtworkImpl;

public interface ArtsMavenInterface {

    @GET("GetAllArtsServlet")
    Call<RespuestaArtworkImpl> getAllArtworks();

    @POST("FiltrarPorArtsName")
    Call<RespuestaArtworkImpl> filterArtsByNameArt(@Query("name") String name);
}
