package com.example.artisophy.dao.respuestas.artwork;

import com.example.artisophy.dao.elements.Artwork;

import java.util.List;

public interface RespuestaArtworkInterface {
    List<Artwork> getResult();

    void setResult(List<Artwork> result);

    String getCode();

    void setCode(String code);

    String getMessage();

    void setMessage(String message);
}
