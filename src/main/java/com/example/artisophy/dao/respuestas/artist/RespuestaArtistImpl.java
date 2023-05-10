package com.example.artisophy.dao.respuestas.artist;

import com.example.artisophy.dao.elements.Artist;

import java.util.List;

public class RespuestaArtistImpl implements RespuestaArtistInterface {
    private String code;
    private String message;
    private List<Artist> result;

    @Override
    public List<Artist> getResult() {
        return result;
    }


    @Override
    public void setResult(List<Artist> result) {
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
