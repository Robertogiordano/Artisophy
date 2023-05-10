package com.example.artisophy.dao.respuestas.artwork;

import com.example.artisophy.dao.elements.Artwork;

import java.util.List;

public class RespuestaArtworkImpl implements RespuestaArtworkInterface{
    private String code;
    private String message;
    private List<Artwork> result;

    @Override
    public List<Artwork> getResult() {
        return result;
    }


    @Override
    public void setResult(List<Artwork> result) {
        this.result = result;
    }


    @Override
    public String getCode() {
        return code;
    }


    @Override
    public void setCode(String code) {
        this.code = code;
    }


    @Override
    public String getMessage() {
        return message;
    }


    @Override
    public void setMessage(String message) {
        this.message = message;
    }
}
