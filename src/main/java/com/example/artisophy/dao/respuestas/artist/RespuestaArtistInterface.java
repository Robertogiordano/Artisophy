package com.example.artisophy.dao.respuestas.artist;

import com.example.artisophy.dao.elements.Artist;

import java.util.List;

public interface RespuestaArtistInterface {
    List<Artist> getResult();

    void setResult(List<Artist> result);

    String getCode();

    void setCode(String code);

    String getMessage();

    void setMessage(String message);
}
