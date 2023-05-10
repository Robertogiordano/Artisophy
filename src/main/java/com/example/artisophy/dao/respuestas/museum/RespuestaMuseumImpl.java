package com.example.artisophy.dao.respuestas.museum;

import com.example.artisophy.dao.elements.Artwork;
import com.example.artisophy.dao.elements.Museum;

import java.util.List;

public class RespuestaMuseumImpl implements RespuestaMuseumInterface {
    private String code;
    private String message;
    private List<Museum> result;

    @Override
    public List<Museum> getResult() {
        return result;
    }


    @Override
    public void setResult(List<Museum> result) {
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
