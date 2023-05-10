package com.example.artisophy.dao.respuestas.user;

import com.example.artisophy.dao.elements.User;

import java.util.List;

public class RespuestaUserImpl implements RespuestaUserInterface {
    private String code;
    private String message;
    private List<User> result;

    @Override
    public List<User> getResult() {
        return result;
    }


    @Override
    public void setResult(List<User> result) {
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
