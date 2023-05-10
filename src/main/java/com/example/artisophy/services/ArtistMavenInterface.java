package com.example.artisophy.services;

import com.example.artisophy.dao.respuestas.artist.RespuestaArtistImpl;
import com.example.artisophy.dao.respuestas.artwork.RespuestaArtworkImpl;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ArtistMavenInterface {

    @GET("GetAllArtistServlet")
    Call<RespuestaArtistImpl> getAllArtists();

    @POST("FilterArtistById")
    Call<RespuestaArtistImpl> filterArtistById(@Query("id") String id);
}
