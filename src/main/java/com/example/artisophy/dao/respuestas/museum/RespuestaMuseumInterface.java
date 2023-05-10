package com.example.artisophy.dao.respuestas.museum;

import com.example.artisophy.dao.elements.Artwork;
import com.example.artisophy.dao.elements.Museum;

import java.util.List;

public interface RespuestaMuseumInterface {
    List<Museum> getResult();

    void setResult(List<Museum> result);

    String getCode();

    void setCode(String code);

    String getMessage();

    void setMessage(String message);
}
